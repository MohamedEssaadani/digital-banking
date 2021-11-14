package com.essaadani.securityservice.sec.web;

import com.essaadani.securityservice.sec.entities.AppRole;
import com.essaadani.securityservice.sec.entities.AppUser;
import com.essaadani.securityservice.sec.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping("/users")
    public List<AppUser> usersList(){
        return userService.usersList();
    }

    @PostMapping("/users")
    public AppUser saveUser(@RequestBody AppUser appUser){
        return userService.addNewUser(appUser);
    }

    @PostMapping("/roles")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return userService.addNewRole(appRole);
    }

    @PostMapping("/users/addRole")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        userService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }

    @GetMapping("/profile")
    public AppUser profile(Principal principal){
        return userService.getUserByUsername(principal.getName());
    }
}

@Data
class RoleUserForm{
    private String username;
    private String roleName;
}
