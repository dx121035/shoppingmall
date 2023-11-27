package com.example.shoppingmall.user;

public enum Role {
	
	ROLE_ANONYMOUS("ROLE_ANONYMOUS"),
	ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

	private String role;

	Role(String role) {
		this.role = role;
	}

	public String value() {
		return role;
	}

}
