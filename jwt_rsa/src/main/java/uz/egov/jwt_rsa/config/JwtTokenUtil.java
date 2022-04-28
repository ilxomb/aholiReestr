package uz.egov.jwt_rsa.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import uz.egov.jwt_rsa.model.CertEntity;
import uz.egov.jwt_rsa.repo.CertRepository;
import uz.egov.jwt_rsa.repo.UserRepository;

import java.io.*;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	@Autowired
	private CertRepository repo;



	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		Claims claims = null;
		try {
			// Certificate X.509
			List<CertEntity> certList = repo.findAll();//TODO repository Certificate X.509
			CertEntity certEntity = certList.get(0);

//			FileInputStream fin = new FileInputStream("d:/temp/cn.cer");
			CertificateFactory f = CertificateFactory.getInstance("X.509");
//			X509Certificate certificate = (X509Certificate)f.generateCertificate(fin);

			InputStream in = new ByteArrayInputStream(certEntity.getData());
			X509Certificate certificate = (X509Certificate)f.generateCertificate(in);

			PublicKey publicKey = certificate.getPublicKey();
			claims = Jwts.parser().setSigningKey(publicKey)
					.parseClaimsJws(token)
					.getBody();
		} catch (CertificateException e) {
			e.printStackTrace();
		}
//		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims;
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
