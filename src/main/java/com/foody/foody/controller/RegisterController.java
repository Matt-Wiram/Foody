package com.foody.foody.controller;

import com.foody.foody.bean.User;
import com.foody.foody.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    private final UserRepository userDao;

    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public ModelAndView registerGet() {
        return new ModelAndView("/register");
    }

    @PostMapping("/register")
    protected ModelAndView registerPost(User user, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("confirm_password") String passwordConfirmation) {


        // validate input
        boolean inputHasErrors = username.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || (! password.equals(passwordConfirmation));

        if (inputHasErrors) {

            return new ModelAndView("redirect:/register");
        }

        // create and save a new user

        String pw = passwordEncoder.encode(user.getPassword());
        System.out.println(user.getPassword());
        user.setPassword(pw);
        userDao.save(user);



        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/login")
    public ModelAndView loginGet () {
        return new ModelAndView("/login");
    }

    @PostMapping("/login")
    public ModelAndView loginPost() {

        return new ModelAndView("redirect:/profile");
    }

    @GetMapping("/profile")
    public ModelAndView profileGet(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getEmail());
        model.addAttribute("user", user);
        return new ModelAndView("/profile");
    }
}
