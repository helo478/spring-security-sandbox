package com.example;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -866966116298468527L;
	
	private final String type;
	
	private final String id;
	
	private final String[] privileges;

	public GrantedAuthorityImpl(final String type, final String id, final String[] privileges) {
		this.type = type;
		this.id = id;
		this.privileges = privileges;
	}

	@Override
	public String getAuthority() {
		
		return String.format("%s;%s;%s", type, id, String.join(",", privileges));
	}

}
