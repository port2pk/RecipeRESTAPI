package com.recipe.jwt.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.recipe.exception.SignatureClaimException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {
	
	Logger log = LoggerFactory.getLogger(JWTUtil.class);

    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        if(null == claims) {
			throw new SignatureClaimException("Check your token");
		}
        return claimsResolver.apply(claims);
    }

	private Claims extractAllClaims(String token) {
		Claims claims=null;
		try {
			claims= Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();	
		} catch (ExpiredJwtException e) {
			log.info(" Token expired ");
		} catch (SignatureClaimException e) {
			log.error(e.getMsg());
		} catch (Exception e) {
			log.info(" Some other exception in JWT parsing ");
		}
		return claims;
	}

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        log.info("generateToken");
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
    	log.info("createToken");

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
    	log.info("validateToken");
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
