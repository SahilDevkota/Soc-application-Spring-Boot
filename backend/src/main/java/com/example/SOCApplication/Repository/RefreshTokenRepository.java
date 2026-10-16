package com.example.SOCApplication.Repository;

import com.example.SOCApplication.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {

    RefreshToken findByToken(String token);
}
