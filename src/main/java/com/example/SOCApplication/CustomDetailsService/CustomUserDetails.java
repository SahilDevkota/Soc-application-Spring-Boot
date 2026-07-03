package com.example.SOCApplication.CustomDetailsService;

import com.example.SOCApplication.Enum.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor

//Custom implementation of user details used by Spring Security
public class CustomUserDetails implements UserDetails {

    //Username used for authentication
    private String username;

    //Encrypted password
    private String password;

    //Role Assigned to user
    @Enumerated(EnumType.STRING)
    private Roles role;

    //Return's user roles and permissions as granted authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

        Set<SimpleGrantedAuthority> collectPermission = role.getPermissions()
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toSet());

        authorities.addAll(collectPermission);
        System.out.println(authorities);
        return authorities;
    }

    //Returns the user's passwords
    @Override
    public @Nullable String getPassword() {
        return password;
    }

    //Returns the user's username
    @Override
    public String getUsername() {
        return username;
    }

    //Checks whether user's account is Expired or not
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    //Checks whether user's account is locked or not
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    //Checks if the credential is expired or not
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    //Checks whether the account is enabled or not
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
