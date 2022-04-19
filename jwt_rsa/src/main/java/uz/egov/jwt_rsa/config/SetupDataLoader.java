package uz.egov.jwt_rsa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uz.egov.jwt_rsa.model.CertEntity;
import uz.egov.jwt_rsa.model.Role;
import uz.egov.jwt_rsa.model.UserEntity;
import uz.egov.jwt_rsa.repo.CertRepository;
import uz.egov.jwt_rsa.repo.RoleRepository;
import uz.egov.jwt_rsa.repo.UserRepository;

import java.util.List;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CertRepository certRepository;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}

		// Create user roles
		var userRole = createRoleIfNotFound(Role.ROLE_USER);
		var adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);

		// Create users
		createUserIfNotFound("user", userRole);
		createUserIfNotFound("admin", adminRole);

		createCertIfNotFound();

		alreadySetup = true;
	}

	@Transactional
	private final Role createRoleIfNotFound(final String name) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role = roleRepository.save(role);
		}
		return role;
	}

	@Transactional
	private final UserEntity createUserIfNotFound(final String name, final Role role) {
		UserEntity user = userRepository.findByUsername(name);
		if (user == null) {
			user = new UserEntity(name, "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
			user.setRoles(Set.of(role));
			user = userRepository.save(user);
		}
		return user;
	}

	@Transactional
	private final CertEntity createCertIfNotFound() {
		List<CertEntity> certList = certRepository.findAll();
		CertEntity certEntity = null;
		if (certList == null || certList.size() == 0) {
			certEntity = new CertEntity();
			certEntity.setFile_name("cn.cer");
			certEntity.setType("cer");
			String crt = "";
			{
				crt = "-----BEGIN CERTIFICATE-----\n" +
						"MIIDFDCCAfygAwIBAgIEYl1REjANBgkqhkiG9w0BAQsFADBMMQswCQYDVQQGEwJ1\n" +
						"ejELMAkGA1UECAwCc3QxCjAIBgNVBAcMAWwxCjAIBgNVBAoMAW8xCzAJBgNVBAsM\n" +
						"Am91MQswCQYDVQQDDAJjbjAeFw0yMjA0MTgxMTUyNTBaFw0yMzA0MTgxMTUyNTBa\n" +
						"MEwxCzAJBgNVBAYTAnV6MQswCQYDVQQIDAJzdDEKMAgGA1UEBwwBbDEKMAgGA1UE\n" +
						"CgwBbzELMAkGA1UECwwCb3UxCzAJBgNVBAMMAmNuMIIBIjANBgkqhkiG9w0BAQEF\n" +
						"AAOCAQ8AMIIBCgKCAQEAzT8CAZ+eQuITC21Guwt9eps+z0L2ZM640h9SHYZitJ4m\n" +
						"DkR0yxlD3ReuMWz1D4FhTDHmSXm3981kHurZdVME9rVBhvwdfujg1Upc250G40y9\n" +
						"4s5+RQkO94OQtsmD08SNd//gCtNbCFpFE/5B8FoTtoosqGc/pCWaScO9FQe57Ggy\n" +
						"XowTiTeWEdG594s6ol9XqcVt0EUlz1TDr99k1rkiOkxKy2EqkxBy3Q1jLX97xeAC\n" +
						"XejqcYP0suFlWCHq9TCouq9vq1TYMbYzxMGpuseSewclCcaGKIFNLuaQyCu6TeLm\n" +
						"nDR8BsY9BjqDQKvTNZ37v7ZAh/VJfRnFnPD7cnaRzQIDAQABMA0GCSqGSIb3DQEB\n" +
						"CwUAA4IBAQAwsbXiW+rD80uzLWmz6w/pPweANTP3CFNWTAijhIC+Rjnvu4nHK8iO\n" +
						"OfOHEK0mUlqw9Qz25tDYO9cZN8rGMzfNy89f3O/Lz3IB2IVe056xF6q0cOh/7wDJ\n" +
						"+TkjQClOnY/FDLrXd3cLkj08iLwWd0b/EybJE/vnAjAV5mRlhTZUekGicQAEeScG\n" +
						"yKdyknsfBH3Q/yoi133soQBEQVRHlMDMNUeNla3P9bDMchKCkF0urZ7JWRokjwMy\n" +
						"s/zMRXvmhJFycTfiJ1oxOb4O827Lho4lLkNH4yf91g2MsULg2dHI2kdBW4kw9UkT\n" +
						"2S3SBuMu81CdxuQvsD8dWFlOKxiJ+cFH\n" +
						"-----END CERTIFICATE-----\n";
			}
			certEntity.setData(crt.getBytes());
			certEntity = certRepository.save(certEntity);
		} else {
			certEntity = certList.get(0);
		}
		return certEntity;
	}
}