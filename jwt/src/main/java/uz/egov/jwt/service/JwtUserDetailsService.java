package uz.egov.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.egov.jwt.model.UserEntity;
import uz.egov.jwt.repo.UserRepository;

import java.util.ArrayList;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = repo.findByUsername(username);
		if (user != null) {
			return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
//			return new User(user.getUsername(), user.getPassword(), buildSimpleGrantedAuthorities(user.getRoles()));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
//		if ("javainuse".equals(username)) {
//			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else if ("my_user".equals(username)) {
//			BCrypt bCrypt =
//			BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder(10);
//			String pwd = bCryptPasswordEncoder.encode("parol");
//			System.out.println("pwd:" + pwd);
//			return new User("my_user", pwd, new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
	}
//	private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
//		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		for (Role role : roles) {
//			authorities.add(new SimpleGrantedAuthority(role.getName()));
//		}
//		return authorities;
//	}

}