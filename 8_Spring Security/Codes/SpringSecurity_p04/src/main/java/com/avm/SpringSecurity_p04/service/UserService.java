package com.avm.SpringSecurity_p04.service;


import com.avm.SpringSecurity_p04.AppUser;
import com.avm.SpringSecurity_p04.request.UserRequest;
import com.avm.SpringSecurity_p04.repo.RoleRepository;
import com.avm.SpringSecurity_p04.repo.UserRepository;
import com.avm.SpringSecurity_p04.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUsers(List<UserRequest> userRequests){
        for(UserRequest req:userRequests){
            AppUser user=new AppUser();

            user.setUsername(req.getUsername());
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            user.setEnabled(true);


            Set<Role> roleSet=new HashSet<>();
            for (String roleName:req.getRoles()){
                Role role=roleRepository.findByName(roleName)
                        .orElseGet(()->{
                            Role newRole=new Role();
                            newRole.setName(roleName);

                            return roleRepository.save(newRole);
                        });

                roleSet.add(role);
            }
            user.setRoles(roleSet);
            userRepository.save(user);

        }
    }
}
