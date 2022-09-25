package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.UserLoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/user/login")
    public String login(UserLoginDto userLoginDto) {

        System.out.println(userLoginDto);
        return "redirect:/";
    }
}
