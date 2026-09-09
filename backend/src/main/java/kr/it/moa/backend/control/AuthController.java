package kr.it.moa.backend.control;

import kr.it.moa.backend.dto.EmailRequest;
import kr.it.moa.backend.entity.EmailVerification;
import kr.it.moa.backend.service.EmailVerificationService;
import kr.it.moa.backend.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final MailService mailService;
    private final EmailVerificationService emailVerificationService;

    // 이메일 인증 메일 발송
    @PostMapping("/email/request")
    public ResponseEntity<Void> requestEmailLogin(
            @RequestBody EmailRequest request
    ) {
        String email = request.email();

        // 이메일 인증정보 생성 + 토큰 DB 저장
        EmailVerification verification = emailVerificationService.createVerification(email);

        // 토큰 정보를 포함한 URL 생성
        String loginUrl = "http://localhost:8080/api/auth/email/verify?token="+verification.getToken();

        // 로그인 인증 메일 발송
        mailService.sendLoginMail(email, loginUrl);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/email/verify")
    public ResponseEntity<String> verifyEmailLogin(
            @RequestParam String token
    ) {
        EmailVerification verification = emailVerificationService.verify(token);
        return ResponseEntity.ok(verification.getEmail());
    }
}
