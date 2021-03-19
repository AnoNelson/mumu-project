package gov.rw.brd.service;


import gov.rw.brd.domain.User;
import gov.rw.brd.exceptions.GlobalException;
import gov.rw.brd.security.SecurityConstraint;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 1/21/2020.
 */
@Component
public class JwtTokenProvider {

    // Generate The Token
    public String generateToken(Authentication authentication){

         User user = (User)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(now.getTime()+ SecurityConstraint.EXPIRATION_TIME);
        String userId  = Long.toString(user.getUser_id());
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",userId);
        claims.put("userName",user.getUsername());
        claims.put("role",user.getRole());
        claims.put("email",user.getEmail());

        return Jwts.builder().setSubject(userId).setClaims(claims).setIssuedAt(now).setExpiration(expirationDate)
                             .signWith(SignatureAlgorithm.HS512, SecurityConstraint.SECRET).compact();
    }

    //validate the token
    public Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SecurityConstraint.SECRET).parseClaimsJws(token);return true;
        }catch (SignatureException ec){
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException ec){
            System.out.println("Invalid JWT token");
            throw new GlobalException("invalid username and password");
        }catch (ExpiredJwtException ec){

        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT token");
        }catch (IllegalArgumentException ec){
            System.out.println("JWT claims empty");
        }
        return false;
    }

    //Get the user Id
    public long getUserIdFromJwt(String token) {
      Claims claims = Jwts.parser().setSigningKey(SecurityConstraint.SECRET).parseClaimsJws(token).getBody();
        return Long.parseLong((String)claims.get("id"));
    }
    }

