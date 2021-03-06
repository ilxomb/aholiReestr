package uz.egov.error;

import com.fasterxml.jackson.core.JsonParseException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.PropertyValueException;
import org.postgresql.util.PSQLException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.xml.sax.SAXParseException;


import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<String>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        ErrorMessage errorMessage = new ErrorMessage(errors);
        errorMessage.setStatus(status.value());
        errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT);

        return new ResponseEntity<Object>(errorMessage, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String unsupported = "Unsupported content type: " + ex.getContentType();
        String supported = "Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes());
        ErrorMessage errorMessage = new ErrorMessage(unsupported, supported);
        errorMessage.setStatus(status.value());
        errorMessage.setErrorCode(ErrorCodes.UNSUPPORTED_MEDIATYPE);

        return new ResponseEntity<Object>(errorMessage, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        ErrorMessage errorMessage;
        if (mostSpecificCause != null) {
            String message = mostSpecificCause.getMessage();
            errorMessage = new ErrorMessage(message);
            if (mostSpecificCause.getClass().getPackage().getName().startsWith("com.fasterxml.jackson"))
                errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT_JSON);
            else if (mostSpecificCause instanceof JsonParseException) {
                errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT_JSON);
            } else if (mostSpecificCause instanceof SAXParseException) {
                errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT_XML);
            } else {
                errorMessage.setErrorCode(ErrorCodes.INVALID_URL);
            }
        } else {
            errorMessage = new ErrorMessage(ex.getMessage());
            errorMessage.setErrorCode(ErrorCodes.INVALID_URL);
        }
        errorMessage.setStatus(status.value());

        return new ResponseEntity<Object>(errorMessage, headers, status);
    }

    @ExceptionHandler(value = {
            NullPointerException.class,
            ObjectNotFoundException.class,
            EntityNotFoundException.class,
            EntityExistsException.class,
            DataIntegrityViolationException.class,
            ConstraintViolationException.class,
            IllegalArgumentException.class,
            javax.validation.ConstraintViolationException.class,
            SAXParseException.class,
            JsonParseException.class})
    public ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        ErrorMessage errorMessage = null;
        if (ex instanceof NullPointerException) {
            status = HttpStatus.NOT_FOUND;
            errorMessage = new ErrorMessage("????????????: ???????????????? NULL ?? ?????????????? ???????????????? ?????????????????????? NOT NULL: " + ex.getMessage());
            errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT);
        } else if (ex instanceof ObjectNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            errorMessage = new ErrorMessage(ex.getMessage());
            errorMessage.setErrorCode(ErrorCodes.NOT_FOUND);
        } else if (ex instanceof EntityNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            errorMessage = new ErrorMessage(ex.getMessage());
            errorMessage.setErrorCode(ErrorCodes.NOT_FOUND);
        } else if (ex instanceof EntityExistsException) {
            status = HttpStatus.CONFLICT;
            errorMessage = new ErrorMessage(ex.getMessage());
            errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT);
        } else if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
            ErrorCodes code;
            if (ex instanceof IllegalArgumentException) {
                // need to extract what kind of problem occurred and set the error code accordingly
                String err = ((IllegalArgumentException) ex).getMessage();
                if (err.toLowerCase().contains("invalid input")) {
                    code = ErrorCodes.INVALID_INPUT;
                } else if (err.toLowerCase().contains("parse") || err.toLowerCase().contains("parsing")) {
                    code = ErrorCodes.PARSE_ERROR;
                } else if (err.toLowerCase().contains("valid")) {
                    code = ErrorCodes.INVALID_STRUCTURE;
                } else if (err.toLowerCase().contains("export")) {
                    code = ErrorCodes.INTERNAL_ERROR;
                } else if (err.toLowerCase().contains("support")) {
                    code = ErrorCodes.UNSUPPORTED_ENCODING;
                } else {
                    code = ErrorCodes.INVALID_INPUT;
                }
                errorMessage = new ErrorMessage(ex.getMessage());
                errorMessage.setErrorCode(code);
            } else {
                //logger.info("Bad Request {}", ex.getMessage());
                errorMessage = new ErrorMessage(ex.getMessage());
                errorMessage.setErrorCode(ErrorCodes.NOT_ALLOWED);
            }
        } else if (ex instanceof DataIntegrityViolationException) {
            Throwable cause = ((DataIntegrityViolationException) ex).getCause();
            if (cause != null) {
                List<String> errors = new ArrayList<String>();
                if (cause instanceof ConstraintViolationException) {
                    Throwable mostSpecificCause = ((ConstraintViolationException) cause).getCause();
                    if (mostSpecificCause != null) {
                        // String exceptionName = mostSpecificCause.getClass().getName();
                        String message = mostSpecificCause.getMessage();
                        // errors.add(exceptionName);
                        errors.add(message);
                    } else {
                        errors.add(ex.getMessage());
                    }
                    errors.add("Violated constraint: " + ((ConstraintViolationException) cause).getMessage());
                    status = HttpStatus.CONFLICT;
                } else if (cause instanceof PropertyValueException) {
                    Throwable mostSpecificCause = ((PropertyValueException) cause).getCause();
                    if (mostSpecificCause != null) {
                        //   String exceptionName = mostSpecificCause.getClass().getName();
                        String message = mostSpecificCause.getMessage();
                        //   errors.add(exceptionName);
                        errors.add(message);
                    } else {
                        errors.add(ex.getMessage());
                    }
                    errors.add(((PropertyValueException) ex).getEntityName());
                    errors.add(((PropertyValueException) ex).getPropertyName());
                } else {
                    Throwable mostSpecificCause = ((DataIntegrityViolationException) ex).getMostSpecificCause();
                    if (mostSpecificCause != null) {
                        //   String exceptionName = mostSpecificCause.getClass().getName();
                        String message = mostSpecificCause.getMessage();
                        errorMessage = new ErrorMessage(message);
                    } else {
                        errorMessage = new ErrorMessage(ex.getMessage());
                    }
                }
                //errorMessage = new ErrorMessage(errors);
            } else { // only put the message
                errorMessage = new ErrorMessage(ex.getMessage());
            }
            errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT);
            status = HttpStatus.CONFLICT;
        } else if (ex instanceof ConstraintViolationException) {
            Throwable mostSpecificCause = ((ConstraintViolationException) ex).getCause();
            List<String> errors = new ArrayList<String>();
            if (mostSpecificCause != null) {
                if (mostSpecificCause instanceof PSQLException) {
                    String detail = ((PSQLException) mostSpecificCause).getServerErrorMessage().getDetail();
                    errors.add(detail);
                } else {
                    //String exceptionName = mostSpecificCause.getClass().getName();
                    String message = mostSpecificCause.getMessage();
                    //  errors.add(exceptionName);
                    errors.add(message);
                }

            } else {
                errors.add(ex.getMessage());
            }
            errors.add("Violated constraint: " + ((ConstraintViolationException) ex).getMessage());
            errorMessage = new ErrorMessage(errors);
            errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT);
            status = HttpStatus.CONFLICT;
        } else if (ex instanceof javax.validation.ConstraintViolationException) {
            Set<ConstraintViolation<?>> constaintViolations = ((javax.validation.ConstraintViolationException) ex).getConstraintViolations();
            List<String> errors = new ArrayList<String>(constaintViolations.size());
            for (Iterator iterator = constaintViolations.iterator(); iterator
                    .hasNext(); ) {
                ConstraintViolation<?> constraintViolation = (ConstraintViolation<?>) iterator
                        .next();
                String error = constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage();
                errors.add(error);
            }
            errorMessage = new ErrorMessage(errors);
            errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT);
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof SAXParseException) {
            Throwable mostSpecificCause = ((SAXParseException) ex).getCause();
            if (mostSpecificCause != null) {
                status = HttpStatus.BAD_REQUEST;
                errorMessage = new ErrorMessage(mostSpecificCause.getMessage());
                errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT_XML);
            } else {
                status = HttpStatus.BAD_REQUEST;
                errorMessage = new ErrorMessage(ex.getMessage());
                errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT_XML);
            }
        } else if (ex instanceof JsonParseException) {
            Throwable mostSpecificCause = ((SAXParseException) ex).getCause();
            if (mostSpecificCause != null) {
                status = HttpStatus.BAD_REQUEST;
                errorMessage = new ErrorMessage(mostSpecificCause.getMessage());
                errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT_JSON);
            } else {
                status = HttpStatus.BAD_REQUEST;
                errorMessage = new ErrorMessage(ex.getMessage());
                errorMessage.setErrorCode(ErrorCodes.INVALID_INPUT_JSON);
            }
        } else {
            //logger.warn("Unknown exception type: " + ex.getClass().getName());
            StringWriter result = new StringWriter();
            PrintWriter printWriter = new PrintWriter(result);
            ex.printStackTrace(printWriter);
//            logger.error("Internal Server Error. Stack Trace: " + result.toString());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            ErrorMessage err = new ErrorMessage(ex.getClass().getName(), ex.getMessage());
            err.setStatus(status.value());
            err.setErrorCode(ErrorCodes.INTERNAL_ERROR);
            return handleExceptionInternal(ex, err, headers, status, request);
        }

        errorMessage.setStatus(status.value());
//        logger.error("Error: {}", errorMessage);
        return handleExceptionInternal(ex, errorMessage, headers, status, request);
    }

}