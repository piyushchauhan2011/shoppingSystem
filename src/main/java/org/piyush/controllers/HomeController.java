package org.piyush.controllers;

import org.piyush.models.CustomError;
import org.piyush.models.Home;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController implements ErrorController {

	private static final String PATH = "/error";

	@RequestMapping("/")
	public Home home(Model model) {
		Home home = new Home("API for Shopping System", "success");
		return home;
	}

	@RequestMapping(value = PATH)
	public CustomError error() {
		CustomError e = new CustomError("Bad request");
		return e;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}