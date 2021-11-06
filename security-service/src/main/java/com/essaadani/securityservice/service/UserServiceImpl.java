package com.essaadani.securityservice.service;

import com.essaadani.securityservice.entities.AppRole;
import com.essaadani.securityservice.entities.AppUser;
import com.essaadani.securityservice.repositories.AppRoleRepository;
import com.essaadani.securityservice.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    @Override
    public AppUser addNewUser(AppUser appUser) {
        // add new user
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        // add new role
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        // get user by username
        AppUser appUser = appUserRepository.findByUsername(username);

        // get role by roleName
        AppRole appRole = appRoleRepository.findByRoleName(roleName);

        // add role to user appRoles collection
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        // get user by username
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> usersList() {
        // get all users
        return appUserRepository.findAll();
    }
}
