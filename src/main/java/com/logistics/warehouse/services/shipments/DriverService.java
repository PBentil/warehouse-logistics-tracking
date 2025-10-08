package com.logistics.warehouse.services.shipments;


import com.logistics.warehouse.models.Role;
import com.logistics.warehouse.models.User;
import com.logistics.warehouse.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final UserRepository userRepository;

    public DriverService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllDrivers() {
        return userRepository.findByRole(Role.DRIVER);
    }
}
