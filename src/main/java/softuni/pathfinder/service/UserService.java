package softuni.pathfinder.service;

import softuni.pathfinder.model.entity.UserEntity;
import softuni.pathfinder.model.service.UserServiceModel;

import java.util.Optional;

public interface UserService {
    boolean login(UserServiceModel userServiceModel);

    boolean registerUser(UserServiceModel userServiceModel);

    void logout();

     UserServiceModel findById(Long id);
}
