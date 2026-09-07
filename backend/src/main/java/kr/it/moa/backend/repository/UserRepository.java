package kr.it.moa.backend.repository;

import kr.it.moa.backend.entity.OAuth2Type;
import kr.it.moa.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByOauth2TypeAndOauth2Type(OAuth2Type oauth2Type, String oauth2Id);
}
