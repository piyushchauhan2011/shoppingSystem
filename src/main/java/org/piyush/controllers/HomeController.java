package org.piyush.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController implements ErrorController {

	private static final String PATH = "/error";

	@RequestMapping("/")
	public String home(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "home";
	}

	@RequestMapping(value = PATH)
	public String error() {
		return "error";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}