package service.demo.store.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.demo.store.authservice.dto.AuthUserDto;
import service.demo.store.authservice.dto.TokenDto;
import service.demo.store.authservice.entity.AuthUser;
import service.demo.store.authservice.service.AuthUserService;

@RestController
@RequestMapping("/auth")
public class AuthUserController {
    @Autowired
    AuthUserService authUserService;



    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto){

        TokenDto tokenDto = authUserService.login(dto);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate/{token}")
    public ResponseEntity<TokenDto> validate(@PathVariable String token){
        TokenDto tokenDto = authUserService.validate(token);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(tokenDto);

    }



    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody AuthUserDto dto){

        AuthUser authUser = authUserService.save(dto);
        if (authUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authUser);

    }
}
