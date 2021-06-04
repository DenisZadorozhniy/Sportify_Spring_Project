package com.sportify.Sportify.service;

import com.sportify.Sportify.model.Role;
import com.sportify.Sportify.model.User;
import com.sportify.Sportify.model.UserDTO;
import com.sportify.Sportify.repository.RoleRepository;
import com.sportify.Sportify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }

    public List<User> allUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public boolean saveUser(UserDTO userDTO) {

        User userFromDB = userRepository.findByUsername(userDTO.getUsername());

        if (userFromDB != null) {
            return false;
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEMail(userDTO.getEMail());
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);

        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
