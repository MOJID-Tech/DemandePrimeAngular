package com.gta.remuniration.service;


import com.gta.remuniration.entity.Etat;
import com.gta.remuniration.entity.User;
import com.gta.remuniration.exception.*;
import com.gta.remuniration.repository.UserRepository;
import com.gta.remuniration.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {


    private static final String LOGIN = "login";

    private static final String EMAIL = "email";

    @Autowired
    private PasswordEncoder passwordEncoder;

   @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;



    @Transactional(readOnly = true)
    public Page<User> findAll(int pageIndex, int size) {

        Pageable pageable = (Pageable) PageRequest.of(pageIndex,  size, Sort.Direction.DESC, "login");
            return repository.findAll(pageable);

        }


    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        if (login == null) {
            throw new NullValueException(login);
        }
        User user = repository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException(User.class, login, login));
        return user;
    }

    @Transactional
    public User authenticate(String login , String password ) {

        if (login== null) {
            throw new NullValueException("login");
        }
        if (password == null) {
            throw new NullValueException("password");
        }

       User userDto = findByLogin(login);

        try { authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login,password));
            if (!userDto.isActive()) {
                throw new UserDeactivatedException();
            }
            if (userDto.getUser_Role().contains("ADMIN")) {
                throw new InsufficientRightException();
            }

        } catch (NotFoundException e) {
            throw new NotFoundException(User.class, LOGIN, login);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException();
        }
        return userDto;
    }
}
