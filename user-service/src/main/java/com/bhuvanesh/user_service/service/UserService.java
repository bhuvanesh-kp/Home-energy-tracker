package com.bhuvanesh.user_service.service;

import com.bhuvanesh.user_service.dto.UserDto;
import com.bhuvanesh.user_service.entity.User;
import com.bhuvanesh.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDto createNewUser(UserDto user){
        //log.info("user created with details {}", user) ;

        User createdUser = User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .address(user.getAddress())
                .email(user.getEmail())
                .userNotification(user.getAlerting())
                .energyAlertingThreshold(user.getAlertingThreshold())
                .build();

        User savedUser = userRepository.saveAndFlush(createdUser);
        return toDto(savedUser);
    }

    public UserDto getUserById(final Long id) {
        //log.info("find user with user id {}", id);
        return userRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    /*public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        return null;
    }*/

    public void updateUser(final Long id, UserDto userDetails) throws IllegalArgumentException{
        User user = userRepository.findById(id).orElseThrow();

        user.setName(userDetails.getName());
        user.setSurname(userDetails.getSurname());
        user.setAddress(userDetails.getAddress());
        user.setEmail(userDetails.getEmail());
        user.setUserNotification(userDetails.getAlerting());
        user.setEnergyAlertingThreshold(userDetails.getAlertingThreshold());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        //log.info("delete user with user id {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found"));
        userRepository.delete(user);
    }

    private UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .address(user.getAddress())
                .email(user.getEmail())
                .alerting(user.getUserNotification())
                .alertingThreshold(user.getEnergyAlertingThreshold())
                .build();
    }

}
