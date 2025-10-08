package com.logistics.warehouse.services.admin;

import com.logistics.warehouse.DTO.admin.UserResponse;
import com.logistics.warehouse.DTO.admin.userUpdate;
import com.logistics.warehouse.models.Role;
import com.logistics.warehouse.models.User;
import com.logistics.warehouse.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final EmailService emailService;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserResponse> getWarehouseManagers() {
        return userRepository.findByRole(Role.WAREHOUSE_MANAGER)
                .stream()
                .map(u -> new UserResponse(
                        u.getId(),
                        u.getFullName(),
                        u.getEmail(),
                        u.getPhone(),
                        u.getRole().name()
                ))
                .collect(Collectors.toList());
    }


    public User createUser(User user) {
        String rawPassword = UUID.randomUUID().toString().substring(0, 8);
        user.setPassword(passwordEncoder.encode(rawPassword));

        User savedUser = userRepository.save(user);

        emailService.sendPasswordEmail(savedUser.getEmail(), rawPassword);


        return savedUser;
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, userUpdate userData) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    if (userData.getFullName() != null) {
                        existingUser.setFullName(userData.getFullName());
                    }
                    if (userData.getEmail() != null) {
                        existingUser.setEmail(userData.getEmail());
                    }
                    if (userData.getPhone() != null) {
                        existingUser.setPhone(userData.getPhone());
                    }
                    if (userData.getRole() != null) {
                        existingUser.setRole(Role.valueOf(userData.getRole()));
                    }

                    if (userData.getActive() != null) {
                        existingUser.setActive(userData.getActive());
                    }

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }


}
