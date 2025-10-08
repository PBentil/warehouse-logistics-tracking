package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.Role;
import com.logistics.warehouse.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String token);
    @Query("SELECT u FROM User u WHERE u.id = :id AND u.role = 'WAREHOUSE_MANAGER'")
    Optional<User> findWarehouseManagerById(@Param("id") Long id);
    List<User> findByRole(Role role);
}
