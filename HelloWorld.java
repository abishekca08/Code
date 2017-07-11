package com.redeye.controller;

import java.awt.AWTException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class HelloWorld {
	@RequestMapping(method = RequestMethod.GET)public String PrintHello(ModelMap model) throws InterruptedException, AWTException{
		model.addAttribute("message", "Hello User");
		HueAccess obj = new HueAccess();
		obj.access();
		return "welcome";
	}
}