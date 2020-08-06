package com.api.template.service;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.api.template.enums.GenderName;
import com.api.template.enums.RoleName;
import com.api.template.enums.UserStatus;
import com.api.template.exception.AppException;
import com.api.template.helpers.Validation;
import com.api.template.model.Admin;
import com.api.template.model.Role;
import com.api.template.model.User;
import com.api.template.repository.AdminRepository;
import com.api.template.repository.RoleRepository;
import com.api.template.repository.UserRepository;
import com.api.template.request.JwtAuthenticationResponse;
import com.api.template.request.LoginRequest;
import com.api.template.request.UserRequest;
import com.api.template.security.JwtTokenProvider;
import com.api.template.security.UserPrincipal;

@Service
public class UserServices {
	
	@Autowired
    RoleRepository roleRepositories;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AppException appException;
    
    @Autowired
    private Validation validation;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @Autowired
    private EmailServices emailServices;
    
    @Autowired
    private AdminRepository adminRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    public UserServices(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${app.name}")
    private String APP_NAME;
    
    public JwtAuthenticationResponse login(LoginRequest req) throws Exception {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User currentUser = userRepository.findById(userPrincipal.getId()).get();
        
        if (currentUser.getUserStatus().equals(UserStatus.DENIED))
            throw appException.userDenied();
        else if (currentUser.getUserStatus().equals(UserStatus.PENDING))
            throw appException.userPending();
        else if (currentUser.getUserStatus().equals(UserStatus.DEACTIVATED))
            throw appException.userDeactivated();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt, currentUser);
    }
    
    public Admin currentAdmin(UserPrincipal currentUser) throws Exception{
        return adminRepository.findById(currentUser.getId()).get();
    }
    
    public User currentUser(UserPrincipal currentUser) throws Exception{
        return userRepository.findById(currentUser.getId()).get();
    }
    
    public void welcome(String email, String name){
        Context context = new Context();
        context.setVariable("name", name);
        Map<String, Object> mail = new HashMap<>();
        mail.put("TO", email);
        mail.put("SUBJECT", "Welcome to %s".format(APP_NAME));
        mail.put("BODY", templateEngine.process("welcome",context));
        emailServices.setPreparator(mail);
    }
    
    //CRUD
    public Iterable<User> getUser() throws Exception{
        return userRepository.findAll();
    }
    
    public User getOneUser(Long id) throws Exception{
        return userRepository.findById(id).get();
    }
    
    public Map<String, Object> addUser(UserRequest userRequest) throws Exception {
        Collection<Role> roles = Arrays.asList(roleRepository.findByAuthority(RoleName.ROLE_PARTICIPANT));
        GenderName gender = GenderName.UNDISCLOSED;
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setMiddleName(userRequest.getMiddleName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setGender(gender);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUserStatus(UserStatus.APPROVED);
        userRepository.save(user);
        
        Map<String, Object> map = new HashMap<>();
        map.put("Message", "Success");
        return map;
    }

	public Map<String, Object> deleteUser(Long id) {
		userRepository.deleteById(id);
		Map<String, Object> map = new HashMap<>();
        map.put("Message", "Success");
        return map;
		
	}
	
	public User updateUser(Long id, UserRequest userRequest) throws Exception {
		try {
		User users = userRepository.findById(id).get();
		
		if (users == null)  {
			throw new RuntimeException("user not found!");
		}
		 
        users.setFirstName(userRequest.getFirstName());
        users.setMiddleName(userRequest.getMiddleName());
        users.setLastName(userRequest.getLastName());
        users.setEmail(userRequest.getEmail());  
        userRepository.save(users);
        
        return users;
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
    }
	
	
	
	
   
 }
