package com.catering.app.security.model;

import com.catering.app.model.enums.user.EUserStatus;
import com.catering.app.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    @Getter
    private final Integer id;
    private final String username;
    private final String password;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;
    private final Set<? extends GrantedAuthority> grantedAuthorities;

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

    public static UserDetails constructFromUserDbToUserDetails(UserEntity userEntity) {
        return new UserDetailsImpl(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getStatus().equals(EUserStatus.ACTIVE),
                userEntity.getStatus().equals(EUserStatus.ACTIVE),
                userEntity.getStatus().equals(EUserStatus.ACTIVE),
                userEntity.getStatus().equals(EUserStatus.ACTIVE),
                userEntity.getRoleEntities().stream().flatMap(role -> role.getName().getSimpleGrantedAuthorities().stream()).collect(Collectors.toSet())
        );
    }
}
