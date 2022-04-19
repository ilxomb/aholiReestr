import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTgenWithPrivateKey {
    public static void main(String[] args) throws Exception {

        System.out.println("load keys");
        PublicKey publicKey = null;
        PrivateKey privateKey = null;
        String password = "1111";
        try {
            // JKS java key store
//            File file = new File("d:/temp/file.jks");
//            InputStream is = new FileInputStream(file);
//            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
//            password = "1111";
//            keystore.load(is, password.toCharArray());
//            KeyStore.PrivateKeyEntry keyEnt =
//                    (KeyStore.PrivateKeyEntry) keystore.getEntry("cn",new KeyStore.PasswordProtection(password.toCharArray()));
//            privateKey = keyEnt.getPrivateKey();
//

            // PFX key store
            File file_pfx = new File("d:/temp/cn.pfx");
            InputStream is_pfx = new FileInputStream(file_pfx);
            KeyStore keystore_pfx = KeyStore.getInstance(KeyStore.getDefaultType());
            password = "1111";
            keystore_pfx.load(is_pfx, password.toCharArray());
            KeyStore.PrivateKeyEntry keyEnt_pfx =
                    (KeyStore.PrivateKeyEntry) keystore_pfx.getEntry("cn",new KeyStore.PasswordProtection(password.toCharArray()));
            privateKey = keyEnt_pfx.getPrivateKey();

            // Certificate X.509
            FileInputStream fin = new FileInputStream("d:/temp/cn.cer");
            CertificateFactory f = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate)f.generateCertificate(fin);
            publicKey = certificate.getPublicKey();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("publicKey");
        byte[] pubBytes = publicKey.getEncoded();
        SubjectPublicKeyInfo spkInfo = SubjectPublicKeyInfo.getInstance(pubBytes);
        ASN1Primitive primitive = spkInfo.parsePublicKey();
        byte[] publicKeyPKCS1 = primitive.getEncoded();
        PemObject pemObject = new PemObject("RSA PUBLIC KEY", publicKeyPKCS1);
        StringWriter stringWriter = new StringWriter();
        PemWriter pemWriter = new PemWriter(stringWriter);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        String pemString = stringWriter.toString();
        System.out.println("");
        System.out.println(pemString);
        System.out.println("");


        String token = generateToken(privateKey);
        System.out.println("Generated Token:\n" + token);

        System.out.println();
        System.out.println("verifyToken");
        verifyToken(token, publicKey);

    }

    public static String generateToken(PrivateKey privateKey) {
        String token = null;
        try {
            String subject = "user";
            long JWT_TOKEN_VALIDITY = 1*60*1000;


            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            long expMillis = nowMillis + JWT_TOKEN_VALIDITY;
            Date exp = new Date(expMillis);
            Map<String, Object> claims = new HashMap<String, Object>();
            token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(now)
                    .setExpiration(exp)
                    .signWith(SignatureAlgorithm.RS512, privateKey)
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    private static Claims verifyToken(String token, PublicKey publicKey) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
            System.out.println("sub: " + claims.get("sub"));
            System.out.println("lat: " + claims.getIssuedAt());
            System.out.println("exp: " + claims.getExpiration());
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}
