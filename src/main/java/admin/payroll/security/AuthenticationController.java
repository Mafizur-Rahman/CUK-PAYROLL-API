package admin.payroll.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import admin.payroll.entity.PmUserRightsEntity;
import admin.payroll.repo.PmUserRightRepo;

/**
 * Exposes REST Points for Authentication.
 * 
 * @author Sachin Singh
 *
 */

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private PmUserRightRepo pmUserRightRepository; 
	
	/**
	 * Authencates a user on behalf of username and password and generates a JWT
	 * access token
	 * 
	 * @param authenticationRequest simple object username and password
	 * @param bindingResult         validation status of authentication request
	 * @return Generated JWT Token as Resposne
	 */
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest,
			BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			try {
				Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
				this.authenticationManager.authenticate(authentication);
				
				// at this point user is successfully authenticated
				String token = this.jwtTokenUtil.generateJwtToken(authenticationRequest.getUsername());
				List<PmUserRightsEntity> userRights = this.pmUserRightRepository.getPmUserRightById(authenticationRequest.getUsername());
				return new ResponseEntity<>(new AuthenticationResponse(token, true, "Authentication Successfull", userRights), HttpStatus.OK);
			
			} catch (BadCredentialsException e) {
				return new ResponseEntity<>(new AuthenticationResponse(false, "Invalid Username or Password"), HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(new AuthenticationResponse(false, "Authentication Request Not Valid"), HttpStatus.BAD_REQUEST);
	}
}
