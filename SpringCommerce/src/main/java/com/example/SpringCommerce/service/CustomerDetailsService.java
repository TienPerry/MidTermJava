package com.example.SpringCommerce.service;

import com.example.SpringCommerce.models.CustomerDetails;
import com.example.SpringCommerce.models.User;
import com.example.SpringCommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@Transactional
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByEmail(email);
        user.orElseThrow(()-> new UsernameNotFoundException("User to"));
        return user.map(CustomerDetails::new).get();
    }
}
