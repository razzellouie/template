package com.api.template.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.api.template.enums.GenderName;
import com.api.template.enums.RoleName;
import com.api.template.enums.UserStatus;
import com.api.template.model.Admin;
import com.api.template.model.Role;
import com.api.template.repository.AdminRepository;
import com.api.template.repository.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataPopulator implements InitializingBean {

    @Autowired
    RoleRepository roleRepositories;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception { 
        if (roleRepositories.count() == 0) {
            initRoles();
            initUser(); 
        }
    }

    private void initRoles() {
        List<Role> roles = new ArrayList<>();
        for (RoleName r : RoleName.values()) {
            if (roleRepositories.findByAuthority(r) == null)
                roles.add(new Role(r));
        }
        roleRepositories.saveAll(roles);
    }

    private void initUser() throws Exception {
        Collection<Role> roles = Arrays.asList(roleRepository.findByAuthority(RoleName.ROLE_ADMIN));
        GenderName gender = GenderName.UNDISCLOSED;
        Admin admin = new Admin();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@template.com");
        admin.setGender(gender);
        admin.setRoles(roles);
        admin.setPassword(passwordEncoder.encode("Password@123"));
        admin.setUserStatus(UserStatus.APPROVED);
        adminRepository.save(admin);
    }
}