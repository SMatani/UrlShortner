package com.urlshortner.shortner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.urlshortner.shortner.connection.ConnectionHandler;
import com.urlshortner.shortner.serviceImpl.UrlServiceImpl;

@SpringBootTest
class ShortnerApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	UrlServiceImpl urlserviceImpl;
	
	@Test
	public void testencodedStringisNotNull(){
		String uri="http://learneveryday676567.com";
		urlserviceImpl.encode();
		assertNotNull(testStringisEncodedorNot(uri));
	}
	
	@Test
	public String testStringisEncodedorNot(String uri){
		String shortenUrl = null;

		Connection con = ConnectionHandler.getConnection();
		String Query = "select shortenUrl from UrlData where originalUrl=?";
		try {
			PreparedStatement stmt = con.prepareStatement(Query);
			stmt.setString(1, uri);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				shortenUrl = rs.getString("shortenUrl");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
        assertNotNull(shortenUrl);
        
		return shortenUrl;
	}
	
}
