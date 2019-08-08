package com.dbs.demo.controller;

import com.dbs.demo.service.UserService;
import com.dbs.demo.model.Tweet;
import com.dbs.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/tweets")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/login")
	public String login(Model model) {
		System.out.println("Came inside the login method ");
		List<User> listOfAllUsers = userService.listAll();
		listOfAllUsers.forEach(user -> System.out.println(user));
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		System.out.println("Inside the POST method of login user");
		System.out.println("Username is " + username + " password is " + password);
		if (username.equalsIgnoreCase(password)) {
			model.addAttribute("user", username);
			return "success";
		}
		return "login";
	}

	@GetMapping("/listAll")
	public String listAllEmployees(Model model) {
		List<User> users = this.userService.listAll();
		model.addAttribute("users", users);
		return "list";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult)
			throws IOException {
		if (bindingResult.hasErrors()) {
			System.out.println("Error " + bindingResult.toString());
			return "register";
			// throw new ArithmeticException("Exception occurred");
			// throw new NullPointerException("DOB not null");
		}
		
		Tweet tweet = new Tweet();
		tweet.setMessage("Hello there!");
        user.addTweetSet(tweet);
		this.userService.saveUser(user);
		return "dashboard";
	}

	@ExceptionHandler(IOException.class)
	public String handleException(HttpServletRequest request, Exception ex) {
		return "error";
	}
}