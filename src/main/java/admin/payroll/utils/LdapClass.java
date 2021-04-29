package admin.payroll.utils;

import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.springframework.stereotype.Component;

import lombok.experimental.UtilityClass;

@Component
public class LdapClass {

	public static boolean authenticate(String username, String password) {
		Hashtable authEnv = new Hashtable(11);
		String base = "ou=People,dc=gtre,dc=org";
		String dn = "uid=" + username + "," + base;
		authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		authEnv.put(Context.PROVIDER_URL, "ldap://172.66.5.20:389");
		authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		authEnv.put(Context.SECURITY_PRINCIPAL, dn);
		authEnv.put(Context.SECURITY_CREDENTIALS, password);

		int shift = 0;

		try {
			DirContext authContext = new InitialDirContext(authEnv);
			System.out.println(authContext);
		} catch (AuthenticationException authEx) {
			shift = 1;
			System.out.println("Authentication failed!");
		} catch (NamingException e) {
			return false;
		}
		if (shift == 1) {
			System.out.println("enter correct login/password ");
			return false;
		} else {
			return true;
		}
	}

}
