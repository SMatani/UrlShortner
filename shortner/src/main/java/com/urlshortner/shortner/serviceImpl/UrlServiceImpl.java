package com.urlshortner.shortner.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortner.shortner.connection.ConnectionHandler;
import com.urlshortner.shortner.entity.UrlData;
import com.urlshortner.shortner.repository.UrlRepository;
import com.urlshortner.shortner.service.UrlService;

@Service
public class UrlServiceImpl implements UrlService {

	@Autowired
	UrlRepository urlRepository;

	// did not work
	@Override
	public void save(UrlData urlData) {
		// TODO Auto-generated method stub
		urlRepository.save(urlData);
	}

	//Generate short url
	@Override
	public String encode() {
		// TODO Auto-generated method stub
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

		int length = 5;

		StringBuilder endOfUrl = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			endOfUrl.append(AlphaNumericString.charAt(index));
		}
		String startOfUrl = "https://sho.rt/";
		String encodedUrl = startOfUrl + endOfUrl;
		return encodedUrl;
	}

	//Insert url data into database
	public void saveData(String originalUrl, String shortenUrl) {

		Connection con = ConnectionHandler.getConnection();
		String Query = "insert into urldata(originalUrl,shortenUrl) values (?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(Query);
			stmt.setString(1, originalUrl);
			stmt.setString(2, shortenUrl);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Look for originalurl in the database to redirect
	public String findOriginalUrl(String urlToTest) {

		String originalUrl = null;

		Connection con = ConnectionHandler.getConnection();
		String Query = "select originalUrl from UrlData where shortenUrl=?";
		try {
			PreparedStatement stmt = con.prepareStatement(Query);
			stmt.setString(1, urlToTest);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				originalUrl = rs.getString("originalUrl");
			}
			System.out.println(originalUrl);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return originalUrl;
	}

}
