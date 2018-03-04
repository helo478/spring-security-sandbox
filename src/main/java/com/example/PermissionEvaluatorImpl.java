package com.example;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class PermissionEvaluatorImpl implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object requiredPrivilege) {

		if ((authentication == null) || (targetId == null) || (targetType == null) || (requiredPrivilege == null)
				|| !(requiredPrivilege instanceof String)) { return false; }
		return hasPrivilege(authentication, targetId.toString().toUpperCase(), targetType.toUpperCase(),
				requiredPrivilege.toString().toUpperCase());
	}

	private boolean hasPrivilege(Authentication auth, Serializable targetId, String targetType,
			String requiredPrivilege) {

		for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
			
			final String[] tokens = grantedAuth.getAuthority().split(";");
			
			if (tokens.length < 3) { continue; }
			
			final String grantedType = tokens[0];
			final String grantedId = tokens[1];
			final String[] grantedPrivileges = tokens[2].split(",");
			
			if (targetType.equals(grantedType) && targetId.equals(grantedId)) {
				
				for (String grantedPrivilege : grantedPrivileges) {
					
					if (requiredPrivilege.equals(grantedPrivilege)) { return true; }
				}
			}
		}

		return false;
	}

}
