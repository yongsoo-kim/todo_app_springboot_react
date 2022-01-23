package com.example.demo.security;

import com.example.demo.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenProvider {

    private static final String SECRET_KEY = "NMA8JPctFuna59f5";

    public String create(UserEntity userEntity) {

        Date expiryDate = Date.from(
            Instant.now()
                .plus(1, ChronoUnit.HOURS));

        // JWT Token 생성
        return Jwts.builder()
            //hearder에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            // payload에 들어갈 내용
            .setSubject(userEntity.getId())
            .setIssuer("demo app")
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .compact();
    }

    public String validateAndGetUserId(String token) {
        // parseClaimsJws메서드가 Base64로 디코딩 및 파싱
        // 헤더와 페이로드를 setSigningKey로 넘오론 시크릿을 이용해 서명한 후 token의 서명과 비교
        // 위조되지 않았다면 페이로드(Claims) 리턴, 위조되었다면 예외를 날림.
        // 그중 우리는 userId가 필요하므로 getBody를 부른다.
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }

}
