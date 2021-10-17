package softuni.pathfinder.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.pathfinder.model.binding.UserLoginBindingModel;
import softuni.pathfinder.model.binding.UserRegisterBindingModel;
import softuni.pathfinder.model.service.UserServiceModel;
import softuni.pathfinder.model.view.UserViewModel;
import softuni.pathfinder.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }


    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        boolean isSuccessful = userService.registerUser(userServiceModel);

        return isSuccessful ? "redirect:login" : "redirect:register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@Valid UserLoginBindingModel userLoginBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        UserServiceModel userServiceModel = modelMapper.map(userLoginBindingModel, UserServiceModel.class);

        boolean isSuccessful = userService.login(userServiceModel);

        return isSuccessful ? "redirect:/" : "redirect:login";

    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model) {
        model.addAttribute("user", modelMapper.map(userService.findById(id),
                UserViewModel.class));
        return "profile";
    }
}

