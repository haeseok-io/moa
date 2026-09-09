package kr.it.moa.backend.service;

import kr.it.moa.backend.entity.EmailVerification;
import kr.it.moa.backend.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final EmailVerificationRepository emailVerificationRepository;

    // 이메일 인증 정보 생성
    public EmailVerification createVerification(String email) {
        String token = UUID.randomUUID().toString();

        EmailVerification verification = EmailVerification.builder()
                .email(email)
                .token(token)
                .expireDate(LocalDateTime.now().plusMinutes(10))
                .build();

        return emailVerificationRepository.save(verification);
    }

    // 이메일 인증 링크 클릭 로직
    public EmailVerification verify(String token) {
        EmailVerification verification = emailVerificationRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 인증 토큰입니다."));

        // 사용된 토큰 체크
        if( verification.isUsed() ) {
            throw new IllegalArgumentException("이미 사용된 인증 토큰입니다.");
        }

        // 만료된 토큰 체크
        if( verification.getExpireDate().isBefore(LocalDateTime.now()) ) {
            throw new IllegalArgumentException("만료된 인증 토큰 입니다.");
        }

        verification.use();

        return verification;
    }
}
