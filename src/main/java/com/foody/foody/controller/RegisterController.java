package com.foody.foody.controller;

import com.foody.foody.bean.Food;
import com.foody.foody.bean.User;
import com.foody.foody.repository.AdsRepository;
import com.foody.foody.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RegisterController {

    private final UserRepository userDao;

    private final PasswordEncoder passwordEncoder;

    private final AdsRepository adsDao;

    public RegisterController(UserRepository userDao, PasswordEncoder passwordEncoder, AdsRepository adsDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.adsDao = adsDao;
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
        User user1 = userDao.findByEmail(user.getEmail());
        System.out.println(user1.getId());
        model.addAttribute("user", user);
       List <Food> food = adsDao.getFoodByUser(user1);

        model.addAttribute("ads", food);
        return new ModelAndView("/profile");
    }

    @GetMapping("/ads/create")
    public ModelAndView createGet () {
        return new ModelAndView("/create");
    }

    @PostMapping("/ads/create")
    public ModelAndView createPost (@RequestParam("hidden") String hidden, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String[] strung = hidden.split("<");
        User user1 = userDao.findByEmail(user.getEmail());


        Food food = new Food(
                user1, // for now we'll hardcode the user id
                Long.parseLong(strung[0]),
                strung[2],
                strung[1]
        );
        adsDao.save(food);
        model.addAttribute("ads", food);
        return new ModelAndView("redirect:/profile");
    }
}
