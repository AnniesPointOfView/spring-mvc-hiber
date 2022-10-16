package hiber.controller;

import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/new")
    public String addUser(@ModelAttribute("user") User user) {
        return "form";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                           RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        userService.createUpdateUser(user);

        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edidtUserForm(@PathVariable(value = "id", required = true) int id, Model model,
                                RedirectAttributes attributes) {
        User user = userService.getUserById(id);

        if (null == user) {
            return "redirect:/";
        }

        model.addAttribute("user", userService.getUserById(id));
        return "form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id", required = true, defaultValue = "") int id,
                             RedirectAttributes attributes) {
        User user = userService.getUserById(id);
        userService.removeUser(id);

        return "redirect:/";
    }
}