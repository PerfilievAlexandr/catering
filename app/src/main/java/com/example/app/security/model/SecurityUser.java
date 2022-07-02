package com.example.app.security.model;

import com.example.app.security.model.entity.User;
import com.example.app.security.model.enums.EUserStatus;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SecurityUser implements UserDetails {
    private final Integer id;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final String password;
    private final String username;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public static UserDetails constructFromUserDbToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(EUserStatus.ACTIVE),
                user.getStatus().equals(EUserStatus.ACTIVE),
                user.getStatus().equals(EUserStatus.ACTIVE),
                user.getStatus().equals(EUserStatus.ACTIVE),
                user.getRoles().stream().flatMap(role -> role.getName().getSimpleGrantedAuthorities().stream()).collect(Collectors.toSet())
        );
    }
}
