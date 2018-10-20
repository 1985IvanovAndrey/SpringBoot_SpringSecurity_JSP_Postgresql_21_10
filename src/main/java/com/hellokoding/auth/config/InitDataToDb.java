package com.hellokoding.auth.config;

import com.hellokoding.auth.model.Role;
import com.hellokoding.auth.model.UserInfo;
import com.hellokoding.auth.repository.RoleRepository;
import com.hellokoding.auth.repository.UserRepository;
import com.hellokoding.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InitDataToDb {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    private void init() {

        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("admin");
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("user");
        roleRepository.save(role2);


        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("Bossman");
        userInfo.setPassword(bCryptPasswordEncoder.encode("12345678"));
        Set<Role> roleList = new HashSet<>();
        roleList.add(role1);
        userInfo.setRoles(roleList);
        userRepository.save(userInfo);

    }
}