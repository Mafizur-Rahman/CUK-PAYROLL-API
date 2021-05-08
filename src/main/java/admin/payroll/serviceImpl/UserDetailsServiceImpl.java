package admin.payroll.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import admin.payroll.entity.PmUsersEntity;
import admin.payroll.repo.PmUserRepo;
import admin.payroll.repo.PmUserRightRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PmUserRepo pmUserRepo;

	@Autowired
	private PmUserRightRepo pmUserRightRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		PmUsersEntity user = this.pmUserRepo.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + " is not found."));

		List<SimpleGrantedAuthority> roles = this.pmUserRightRepo.getRolesByUserId(username).stream()
				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

		return new User(user.getUserName(), user.getPassword(), roles);
	}

}
