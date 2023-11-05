package service.demo.store.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.demo.store.authservice.entity.AuthUser;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findByUserName(String userName);
}
