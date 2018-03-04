package com.example;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -866966116298468527L;
	
	private String id;
	
	private String type;
	
	private String[] privileges;

	public GrantedAuthorityImpl(String id, String type, String[] privileges) {
		this.id = id;
		this.type = type;
		this.privileges = privileges;
	}

	@Override
	public String getAuthority() {
		
		return String.format("%s;%s;%s", type, id, String.join(",", privileges));
	}

}
