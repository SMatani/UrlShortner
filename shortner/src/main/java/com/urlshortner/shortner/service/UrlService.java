package com.urlshortner.shortner.service;

import com.urlshortner.shortner.entity.UrlData;

public interface UrlService {
	
	void save(UrlData urlData);

	String encode();

}
