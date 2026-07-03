package com.example.SOCApplication.Filter;

import com.example.SOCApplication.Config.SecurityConfig;
import com.example.SOCApplication.ServiceImpl.CustomUserDetailService;
import com.example.SOCApplication.Util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor

//JWT authentication filter that intercepts, request and validates the token
//It sets the authenticated user in the security Context
public class JWTAuthFilter extends OncePerRequestFilter {

    //Service for handling user details operations
    private final CustomUserDetailService customUserDetailService;

    //JWT Utility class to generate tokens
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try{
           String authorization = request.getHeader("Authorization");
           System.out.println(authorization);
           String token=null;
           String username = null;
           System.out.println("Ok now checking the authorization");
           System.out.println(authorization);
           if(authorization!=null && authorization.startsWith("Bearer ")) {
               token = authorization.substring(7);
               System.out.println("Grabbed the token");
               username = jwtUtil.extractUsername(token);

               System.out.println("username extracted");
               if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                   UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

                   System.out.println("userdetails extracted");

                   if (jwtUtil.validateToken(token, username, userDetails)) {

                       System.out.println("Now validating");
                       UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                       SecurityContextHolder.getContext().setAuthentication(authToken);
                   }
               }
           }
       }catch(ExpiredJwtException e){
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return ;
       }

        filterChain.doFilter(request,response);
    }


}
