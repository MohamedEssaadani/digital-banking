package com.essaadani.securityservice.sec.service;

import com.essaadani.securityservice.sec.entities.AppRole;
import com.essaadani.securityservice.sec.entities.AppUser;

import java.util.List;

public interface UserService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username, String roleName);
    List<AppUser> usersList();
    AppUser getUserByUsername(String username);

}
