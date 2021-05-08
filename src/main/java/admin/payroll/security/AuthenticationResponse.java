package admin.payroll.security;

import java.util.List;

import admin.payroll.entity.PmUserRightsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Sachin Singh
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	private String token;
	private Boolean authenticated;
	private String message;
	private List<PmUserRightsEntity> grantedRoles;
	
	public AuthenticationResponse(Boolean authenticated, String message) {
		this.authenticated = authenticated;
		this.message = message;
	}
}
