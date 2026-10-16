package com.example.SOCApplication.Enum;

import lombok.RequiredArgsConstructor;

import java.util.Set;

//Defines user roles and their permission levels used for role-based access controls
@RequiredArgsConstructor
public enum Roles {
    ADMIN(Set.of(Permissions.FULL_CONTROL)),
    ANALYST(Set.of(Permissions.READ,Permissions.WRITE,Permissions.EXECUTE)),
    VIEWER(Set.of(Permissions.READ));

    private final Set<Permissions> permissions;

    public Set<Permissions> getPermissions(){
        return permissions;
    }

}

