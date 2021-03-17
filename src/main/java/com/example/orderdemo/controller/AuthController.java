package com.example.orderdemo.controller;

import com.example.orderdemo.dto.JwtResponse;
import com.example.orderdemo.dto.LoginRequest;
import com.example.orderdemo.dto.MessageResponse;
import com.example.orderdemo.dto.SignupRequest;
import com.example.orderdemo.entity.ERole;
import com.example.orderdemo.entity.Role;
import com.example.orderdemo.entity.User;
import com.example.orderdemo.repository.RoleRepo;
import com.example.orderdemo.repository.UserRepository;
import com.example.orderdemo.security.JWTUils;
import com.example.orderdemo.security.UserDetailImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JWTUils jwtUils;

        @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUils.generJwtToken(authentication);
        UserDetailImp userDetailImp = (UserDetailImp) authentication.getPrincipal();
        List<String> role = userDetailImp.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetailImp.getId(), userDetailImp.getUsername(), userDetailImp.getEmail(), role));
    }

    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity<?> responseEntity(@Validated @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User da ton tai"));
        }
        if (userRepository.existsByUsername(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email da ton tai"));
        }
        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));
        Set<String> erole = signupRequest.getRole();
        Set<Role> roleSet = new HashSet<>();
        if (erole == null) {
            Role userRole = roleRepo.findByRoleName(ERole.ROLE_USER).orElseThrow(() ->
                    new RuntimeException("Role is not found"));
            roleSet.add(userRole);
        } else {
            erole.forEach(r -> {
                switch (r) {
                    case "admin":
                        Optional<Role> adminRole = roleRepo.findByRoleName(ERole.ROLE_ADMIN);
                        if (!adminRole.isPresent()) {
                            throw new RuntimeException("Role is not found");
                        }
                        roleSet.add(adminRole.get());
                        break;
                    case "mod":
                          Optional<Role> modRole = roleRepo.findByRoleName(ERole.ROLE_MODERATOR);
                        System.out.println(modRole.toString());
                        if (!modRole.isPresent()) {
                            throw new RuntimeException("Role is not found");
                        }
                        roleSet.add(modRole.get());
                        break;
                    default:
                        Role userRole = roleRepo.findByRoleName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Role is not found"));
                        roleSet.add(userRole);
                }
            });
        }
        user.setRoleSet(roleSet);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("user signup success"));
    }
}



