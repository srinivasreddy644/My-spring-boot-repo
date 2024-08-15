package net.javaguides.springboot.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showProfileForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        UserRegistrationDto userDto = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("user", userDto);
        return "profile";
    }

    @PostMapping
    public String updateUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                    BindingResult result, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "profile";
        }
        userService.update(userDetails.getUsername(), userDto);
        return "redirect:/profile?success";
    }
}
