package softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.pathfinder.model.entity.UserEntity;
import softuni.pathfinder.model.entity.enums.LevelEnum;
import softuni.pathfinder.model.service.UserServiceModel;
import softuni.pathfinder.repository.UserRepository;
import softuni.pathfinder.service.UserService;
import softuni.pathfinder.util.CurrentUser;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }


    @Override
    public boolean login(UserServiceModel userServiceModel) {
        UserEntity user = userRepository
                .findByUsernameAndPassword(userServiceModel.getUsername(), userServiceModel.getPassword())
                .orElse(null);

        if (user == null) {
            return false;
        }

        loginCurrentUser(user);

        return true;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {

        if (userRepository.findByUsername(userServiceModel.getUsername()).isPresent() ||
                userRepository.findByEmail(userServiceModel.getEmail()).isPresent()) {
            return false;
        }

        UserEntity newUser = modelMapper.map(userServiceModel, UserEntity.class);
        newUser.setLevel(LevelEnum.BEGINNER);
//        newUser.setRoles(Set.of());
        userRepository.save(newUser);

        return true;
    }

    @Override
    public void logout() {
        currentUser.setUsername(null)
                .setFullName(null)
                .setLevel(null)
                .setId(null)
                .setAge(null)
                .setPassword(null)
                .setLoggedIn(false);

        currentUser.clearRoles();
    }

    @Override
    public UserServiceModel findById(Long id) {
        return userRepository.findById(id)
                .map(e -> modelMapper.map(e, UserServiceModel.class))
                .orElse(null);
    }

    private void loginCurrentUser(UserEntity user) {
        currentUser.setUsername(user.getUsername())
                .setFullName(user.getFullName())
                .setAge(user.getAge())
                .setId(user.getId())
                .setRoles(user.getRoles())
                .setLevel(user.getLevel())
                .setLoggedIn(true);
    }
}
