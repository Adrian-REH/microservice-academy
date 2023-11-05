package service.demo.store.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import service.demo.store.authservice.dto.AuthUserDto;
import service.demo.store.authservice.dto.TokenDto;
import service.demo.store.authservice.entity.AuthUser;
import service.demo.store.authservice.repository.AuthUserRepository;
import service.demo.store.authservice.security.JwtProvider;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthUserService {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    public AuthUser save(AuthUserDto userDto){

        Optional<AuthUser> user = authUserRepository.findByUserName(userDto.getUserName());

        if (user.isPresent())
            return null;

        String password = passwordEncoder.encode(userDto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .id(UUID.randomUUID().toString())
                .userName(userDto.getUserName())
                .password(password)
                .build();

        return authUserRepository.save(authUser);

    }

    public TokenDto login(AuthUserDto dto){
        Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());

        if (user.isEmpty())
            return null;

        if (passwordEncoder.matches(dto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }

    public TokenDto validate(String token){
        if (!jwtProvider.validate(token))
            return null;

        String username = jwtProvider.getUserNameFromToken(token);

        if (authUserRepository.findByUserName(username).isEmpty())
            return null;

        return new TokenDto(token);
    }

}
