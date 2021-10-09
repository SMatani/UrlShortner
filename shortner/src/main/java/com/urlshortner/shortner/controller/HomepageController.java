package com.urlshortner.shortner.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.urlshortner.shortner.entity.UrlData;
import com.urlshortner.shortner.serviceImpl.UrlServiceImpl;

@Controller
public class HomepageController {
	
	@Autowired
	UrlServiceImpl urlServiceImpl;
	
	UrlData urlData;
	
    Logger logger = LoggerFactory.getLogger(HomepageController.class);


	@GetMapping("/api")
	public String loadHomepage()
	{
		return "homepage";
	}
	
	@PostMapping("/api")
	public String saveAndEncodeUrl(@RequestParam("urlToEncode") String urlToEncode,Model model)
	{
		logger.info("ENTERED saveAndEncodeUrl");
		String encodedUrl=urlServiceImpl.encode();
	
		logger.info("SAVING URL DATA......");

		urlServiceImpl.save(new UrlData(urlToEncode,encodedUrl));
		
		urlServiceImpl.saveData(urlToEncode,encodedUrl);
		
		logger.info("SAVED URL DATA");


		model.addAttribute("yourUrl",urlToEncode);
		model.addAttribute("shortUrl",encodedUrl);
		
		return "shortUrl";
	}
	
	@PostMapping("/api/shortUrl")
	public RedirectView  testUrl(@RequestParam("urlToTest") String urlToTest,Model model) throws URISyntaxException, MalformedURLException
	{
		logger.info("ENTERED testUrl");

		logger.info("Looking for testUrl in database...");

		String originalUrl=urlServiceImpl.findOriginalUrl(urlToTest);
		
		logger.info("URL Found");

		URI uri = new URI(originalUrl); 
		URL url = uri. toURL();
		
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl(originalUrl);
	    return redirectView;
	}
}
