package com.urlshortner.shortner.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UrlData {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int urlId;
 

	public String originalUrl;
	 public String shortenUrl;
	 
	   public UrlData(String originalUrl, String shortenUrl) {
			super();
			this.originalUrl = originalUrl;
			this.shortenUrl = shortenUrl;
		}
	 
	 public int getId() {
		return urlId;
	}
	public void setId(int urlId) {
		this.urlId = urlId;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getShortenUrl() {
		return shortenUrl;
	}
	public void setShortenUrl(String shortenUrl) {
		this.shortenUrl = shortenUrl;
	}
	
	public UrlData(int urlId, String originalUrl, String shortenUrl) {
		super();
		this.urlId = urlId;
		this.originalUrl = originalUrl;
		this.shortenUrl = shortenUrl;
	}

}
