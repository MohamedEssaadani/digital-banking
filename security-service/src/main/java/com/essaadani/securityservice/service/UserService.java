package com.essaadani.securityservice.service;

import com.essaadani.securityservice.entities.AppRole;
import com.essaadani.securityservice.entities.AppUser;

import java.util.List;

public interface UserService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> usersList();

}
