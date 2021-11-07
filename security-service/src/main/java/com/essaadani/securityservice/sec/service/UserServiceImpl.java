package com.essaadani.securityservice.sec.service;

import com.essaadani.securityservice.sec.entities.AppRole;
import com.essaadani.securityservice.sec.entities.AppUser;
import com.essaadani.securityservice.sec.repositories.AppRoleRepository;
import com.essaadani.securityservice.sec.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(AppUser appUser) {
        // Encoder le mot de passe
        String password = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(password));

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
    public List<AppUser> usersList() {
        // get all users
        return appUserRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);

        if(appUser == null)
            throw new UsernameNotFoundException("User not found in the database");

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        appUser
                .getAppRoles()
                .forEach(appRole -> {
                    authorities.add(new SimpleGrantedAuthority(appRole.getRoleName()));
                });

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
}
