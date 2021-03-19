package gov.rw.brd.security;


import gov.rw.brd.domain.User;
import gov.rw.brd.exceptions.JwtAuthenticationEntryPoint;
import gov.rw.brd.service.CustomerUserDetailsService;
import gov.rw.brd.service.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by Nick on 1/22/2020.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CustomerUserDetailsService   customerUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

           String jwt = getJwtFromRequest(request);
         if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)){
             Long userId = jwtTokenProvider.getUserIdFromJwt(jwt);
             User userDetails = customerUserDetailsService.loadUserById(userId);
             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                     userDetails,null, Collections.emptyList());
             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(authentication);
         }else{

         }
        filterChain.doFilter(request,response);
    }

    private String getJwtFromRequest(HttpServletRequest request){
        String bearToken = request.getHeader(SecurityConstraint.HEADER_STRING);
        if(StringUtils.hasText(bearToken) && bearToken.startsWith(SecurityConstraint.TOKEN_PREFIX)){
            return bearToken.substring(7,bearToken.length());
        }
        return null;
    }
}
