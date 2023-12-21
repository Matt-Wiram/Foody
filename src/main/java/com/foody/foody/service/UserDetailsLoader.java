package com.foody.foody.service;


import com.foody.foody.bean.User;
import com.foody.foody.bean.UserWithRoles;
import com.foody.foody.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UserDetailsLoader implements UserDetailsService {




        @Autowired
        private UserRepository users;

    public UserDetailsLoader(UserRepository users) {
            this.users = users;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
            User user = users.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("No user found for " + username);
            }
            System.out.println(user.getPassword());
            return new UserWithRoles(user);
        }
    }
