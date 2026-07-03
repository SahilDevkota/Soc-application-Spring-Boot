package com.example.SOCApplication.ServiceImpl;

import com.example.SOCApplication.CustomDetailsService.CustomUserDetails;
import com.example.SOCApplication.Entity.User;
import com.example.SOCApplication.Enum.Roles;
import com.example.SOCApplication.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {



    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username + " not found"));
        return new CustomUserDetails(user.getUsername(),user.getPassword(), user.getRole());
    }
}
