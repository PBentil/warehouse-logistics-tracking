package com.logistics.warehouse.controllers.auth;

import com.logistics.warehouse.DTO.auth.ForgetPasswordRequest;
import com.logistics.warehouse.DTO.auth.ResetPasswordRequest;
import com.logistics.warehouse.services.auth.ForgotPasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ForgotPasswordController {
    private final ForgotPasswordService forgotPasswordService;

    public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgetPasswordRequest request) {
        forgotPasswordService.sendResetLink(request.getEmail());
        return ResponseEntity.ok("Password reset link sent ✅");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String token,
            @RequestBody ResetPasswordRequest request) {
        forgotPasswordService.resetPassword(token, request.getNewPassword());
        return ResponseEntity.ok("Password reset successful ✅");
    }

}
