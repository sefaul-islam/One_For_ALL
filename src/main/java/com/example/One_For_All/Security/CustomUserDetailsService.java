package com.example.One_For_All.Security;

import com.example.One_For_All.Users.Repos.UserRepository;
import com.example.One_For_All.Users.model.Entities.Users;
import com.example.One_For_All.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

     private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users =  userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException());

        return new CustomUserDetails(users);

    }
}
