package com.logistics.warehouse.services.supplier;

import com.logistics.warehouse.DTO.supplier.SupplierRequest;
import com.logistics.warehouse.DTO.supplier.SupplierResponse;
import com.logistics.warehouse.models.Role;
import com.logistics.warehouse.models.Supplier;
import com.logistics.warehouse.models.User;
import com.logistics.warehouse.repositories.SupplierRespository;
import com.logistics.warehouse.repositories.UserRepository;
import com.logistics.warehouse.services.admin.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    private final SupplierRespository supplierRespository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public SupplierService(SupplierRespository supplierRespository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           EmailService emailService) {
        this.supplierRespository = supplierRespository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public SupplierResponse addSupplier(SupplierRequest request) {
        supplierRespository.findByName(request.getName())
                .ifPresent(s -> { throw new RuntimeException("Supplier with this name already exists"); });

        supplierRespository.findByContactInfo(request.getContactInfo())
                .ifPresent(s -> { throw new RuntimeException("Supplier with this contact info already exists"); });

        // Generate random password
        String rawPassword = UUID.randomUUID().toString().substring(0, 8);


        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getEmail());
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(Role.SUPPLIER);

        User savedUser = userRepository.save(user);

        emailService.sendPasswordEmail(savedUser.getEmail(), rawPassword);

        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setAddress(request.getAddress());
        supplier.setContactInfo(request.getContactInfo());
        supplier.setUser(savedUser);

        Supplier saved = supplierRespository.save(supplier);

        return mapToResponse(saved);
    }

    public List<SupplierResponse> getAllSuppliers() {
        return supplierRespository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public SupplierResponse getSupplier(Long id) {
        Supplier supplier = supplierRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        return mapToResponse(supplier);
    }

    public SupplierResponse updateSupplier(Long id, SupplierRequest request) {
        Supplier supplier = supplierRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setName(request.getName());
        supplier.setAddress(request.getAddress());
        supplier.setContactInfo(request.getContactInfo());

        Supplier updated = supplierRespository.save(supplier);
        return mapToResponse(updated);
    }

    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        if (supplier.getUser() != null) {
            userRepository.delete(supplier.getUser());
        }

        supplierRespository.deleteById(id);
    }

    private SupplierResponse mapToResponse(Supplier supplier) {
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getSupplierId());
        response.setName(supplier.getName());
        response.setAddress(supplier.getAddress());
        response.setContactInfo(supplier.getContactInfo());

        if (supplier.getUser() != null) {
            response.setEmail(supplier.getUser().getEmail());
        }

        return response;
    }
}
