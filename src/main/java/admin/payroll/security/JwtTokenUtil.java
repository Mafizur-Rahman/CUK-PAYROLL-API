package admin.payroll.security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author Sachin Singh
 *
 */

@Component
public class JwtTokenUtil  {
	
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.setSigningKey(JWTConstants.SIGNING_KEY)
				.parseClaimsJws(token).getBody();
	}
	
	
	public String generateJwtToken(String username) {
		Date issuedAt = new Date(System.currentTimeMillis());
		Date tokenExpiryDate = new Date(System.currentTimeMillis() + 1000 * JWTConstants.ACCESS_TOKEN_VALIDITY_SECONDS);
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuer("Disgen International Pvt. Ltd.")
				.setIssuedAt(issuedAt)
				.setExpiration(tokenExpiryDate)
				.signWith(SignatureAlgorithm.HS256, JWTConstants.SIGNING_KEY)
				.compact();
	}
	
	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token, String username) {
		final String tokenUsername = extractUsername(token);
		return username.equals(tokenUsername) && !isTokenExpired(token);
	}
}






