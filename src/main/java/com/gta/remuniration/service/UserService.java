package com.gta.remuniration.service;


import com.gta.remuniration.entity.Etat;
import com.gta.remuniration.entity.Role;
import com.gta.remuniration.entity.User;
import com.gta.remuniration.entity.user_role;
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

import java.util.ArrayList;
import java.util.List;


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
    @Autowired
    private user_roleService user_roleService;



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

    @Transactional(readOnly = true)
    public User  findById(Integer id) {
        if (id == null) {
            throw new NullValueException("id");
        }
        User user;
        return user  = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

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
            List<user_role> user_roles  ;
            user_roles = user_roleService.findbyUserId(userDto.getId());
            List<String>roles = new ArrayList<String>();
            for (user_role role : user_roles)
            {
                roles.add(role.getRole().getNom_Role());
            }

          String token= jwtTokenProvider.createToken(login,roles);
            userDto.setToken(jwtTokenProvider.createToken(login, roles));

        } catch (NotFoundException e) {
            throw new NotFoundException(User.class, LOGIN, login);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException();
        }
        return userDto;
    }
    /*****************************/
   /* @Transactional(readOnly = false)
    public List<SelectionDTO> getUsersMinifiedList() {
        return repository.findByRoleAndActive(USER, true)
                .stream()
                .map(user -> SelectionDTO.builder().code(user.getId()).label(user.getFirstName() + " " + user.getLastName()).build())
                .collect(Collectors.toList());
    }*/


    @Transactional(readOnly = false)
    public User createUser(User userDTO) {
        if (repository.findByLogin(userDTO.getLogin()).isPresent()) {
            throw new CredentialAlreadyExistsException("Login");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setLogin(userDTO.getLogin().toLowerCase());

        return repository.save(userDTO);
    }


    @Transactional(readOnly = false)
    public User updateUser(Integer id, User userDTO) {
        if (id == null) {
            throw new NullValueException("id");
        }
        if (userDTO == null) {
            throw new NullValueException("user");
        }
        User userToUpdate = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, userDTO.getId()));

        if (!userToUpdate.getLogin().equals(userDTO.getLogin()) && repository.findByLogin(userDTO.getLogin()).isPresent()) {
            throw new PropertyAlreadyUsedException(LOGIN);
        }

        userToUpdate.setLogin(userDTO.getLogin().toLowerCase());
       // userToUpdate.setRole(userDTO.getRole());
        return (repository.save(userToUpdate));
    }


    @Transactional(readOnly = false)
    public User setActive(Integer id, boolean isActive) {
        if (id == null) {
            throw new NullValueException("id");
        }
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

        user.setActive(isActive);
        return (repository.save(user));
    }


    @Transactional(readOnly = false)
    public User changePassword(String login, String currentPassword, String newPassword) {
        if (login == null) {
            throw new InsufficientRightException();
        }
        if (currentPassword == null) {
            throw new NullValueException("currentPassword");
        }
        if (newPassword == null) {
            throw new NullValueException("newPassword");
        }
        User user = repository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException(User.class, login, login));
        if (passwordEncoder.matches(currentPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            User savedUser = repository.save(user);
            return (savedUser);
        } else {
            throw new WrongPasswordException();
        }

    }


  /*  @Transactional
    public ValidationEmailDTO resetPassword(Long id, String newPassword) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

        boolean checkEmailSend = mailService.send(MailType.RESET_PASSWORD, user.getEmail(), new String[]{newPassword});
        if (!checkEmailSend) {
            throw new SendEmailException();
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
        return ValidationEmailDTO.builder().code(200).message("Veuillez vérifier votre boite email").build();
    }


    @Transactional
    @SuppressWarnings("squid:S1612")
    public ValidationEmailDTO resetPassword(String email) {
        User utilisateur = repository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException());

        String newPassword = generateRandomPassword();
        boolean checkEmailSend = mailService.send(MailType.RESET_PASSWORD, email, new String[]{newPassword});
        if (!checkEmailSend) {
            throw new SendEmailException();
        }
        utilisateur.setPassword(passwordEncoder.encode(newPassword));
        repository.save(utilisateur);
        return ValidationEmailDTO.builder().code(200).message("Veuillez vérifier votre boite email").build();
    }*/

}

