package com.example.SOCApplication.ServiceImpl;
import com.example.SOCApplication.Entity.RefreshToken;
import com.example.SOCApplication.Repository.RefreshTokenRepository;
import com.example.SOCApplication.Service.RefreshTokenService;
import com.example.SOCApplication.Util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

        private final JWTUtil jwtUtil;

        private final RefreshTokenRepository refreshTokenRepository;

        private final CustomUserDetailService customUserDetailService;

    @Override
    public ResponseEntity<Map> RefreshAccessToken(String refreshToken) {

        Map<String,String> response = new HashMap<>();
        System.out.println("Refresh Token Service Impl Started");
        RefreshToken refreshToken1 = refreshTokenRepository.findByToken(refreshToken);
        System.out.println("Ok refresh token is also checked!");
        String username = null;
        String token = null;
        if(refreshToken1 ==null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Refresh Token");
        }
        else{
            System.out.println("Refresh token exists");
            username = refreshToken1.getUsername();
            token = refreshToken1.getToken();

            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
            if(jwtUtil.validateToken(token,username,userDetails)){
                String newAccessToken = jwtUtil.GenerateAccessToken(username);
                response.put("AccessToken",newAccessToken);
                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(401).build();
    }

}
