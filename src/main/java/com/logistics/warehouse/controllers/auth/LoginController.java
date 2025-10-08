
  package com.logistics.warehouse.controllers.auth;
  import com.logistics.warehouse.DTO.auth.LoginRequest;
  import com.logistics.warehouse.DTO.auth.LoginResponse;
  import com.logistics.warehouse.services.auth.LoginService;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RestController;

  @RestController
  @RequestMapping("/auth")
  public class LoginController {
      private final LoginService loginService;

      public LoginController (LoginService loginService){
          this.loginService = loginService;
      }

      @PostMapping("/login")
      public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
          LoginResponse response = loginService.login(request);
          return ResponseEntity.ok(response);
      }
}
