package br.com.handrei.security.jwt;

import br.com.handrei.domain.entity.ApiUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.key}")
    private String key;

    public String getToken(ApiUser user) {
        long expString = Long.valueOf(expiration);
        LocalDateTime expTime = LocalDateTime.now().plusMinutes(expString);
        Date date = Date.from(expTime
                .atZone(ZoneId.systemDefault())
                .toInstant()
        );

        return Jwts
                .builder()
                .setSubject(user.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date date = claims.getExpiration();
            LocalDateTime expTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return LocalDateTime.now().isBefore(expTime);
        } catch (Exception exception) {
            return false;
        }
    }

    public String getLogin(String token) throws ExpiredJwtException {
        String subject = getClaims(token).getSubject();

        return subject;
    }
}
