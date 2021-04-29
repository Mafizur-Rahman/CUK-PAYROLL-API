package admin.payroll.models;

import java.util.List;

import admin.payroll.entity.PmUserRightsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDto {
	private boolean authStatus;
	private String loginId;
	private String message;
	private List<PmUserRightsEntity> grantedRoles;
	
	
}
