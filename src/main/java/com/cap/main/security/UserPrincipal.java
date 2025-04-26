package com.cap.main.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cap.main.entity.User;

public class UserPrincipal implements UserDetails {

    private final User user;

	public UserPrincipal(User user) {
		this.user = user;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assign only ROLE_USER to all authenticated users
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Using email as username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Account never expires
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Account never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials never expire
    }

    @Override
    public boolean isEnabled() {
        return user.isOtpVerified(); // Only enabled if OTP verified
    }

    public User getUser() {
        return user;
    }
}