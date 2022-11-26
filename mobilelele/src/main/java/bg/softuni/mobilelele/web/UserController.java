package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.UserLoginDto;
import bg.softuni.mobilelele.model.dto.UserRegisterDto;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors() || this.userService.login(userLoginDto)) {
            redirectAttributes.addFlashAttribute("userModel", userLoginDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BidingResult.userModel",
                    bindingResult);
            bindingResult.rejectValue("password", "InvalidPasswordError",
                    "Invalid password");
            return "redirect:/users/login";
        }

        return "redirect:/";
    }
}
