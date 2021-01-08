package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.wp.lab.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserServiceImpl userService;

    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        User user = null;

        try{

            user = this.userService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/courses";

        }catch (InvalidUserCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }

    }

}
