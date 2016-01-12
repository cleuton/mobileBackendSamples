package com.obomprogramador.dropbackend;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DrbDao {
	private MongoClient client;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public DrbDao(String dbUrl) {
		this.client = new MongoClient(dbUrl);
	}
	
	class BooleanFind {
		boolean foundSomething;
	}
	public boolean getLogin(final LoginCredential login) {
		final BooleanFind bf = new BooleanFind(); 
		MongoDatabase db = null;
		try {
			db = this.client.getDatabase("backend");
			FindIterable<Document> iterable =  db.getCollection("login").find(
			        new Document("email", login.getEmail())
			        ).limit(1);
			iterable.forEach(new Block<Document>() {
			    @Override
			    public void apply(final Document document) {
			    	if (document.getString("password").equals(login.getPassword())) {
			    		bf.foundSomething = true;	
			    	}
			    	else {
			    		bf.foundSomething = false;
			    	}
			    }
			});
		}
		catch (Exception ex) {
			logger.error("$$$ Exception getLogin: " + ex.getMessage());
			bf.foundSomething = false;
		}

		return bf.foundSomething;
	}
	public List<NewsLine> getNewsFeed() {
		final List<NewsLine> newsList = new ArrayList<NewsLine>();
		MongoDatabase db = null;
		try {
			db = this.client.getDatabase("backend");
			FindIterable<Document> iterable =  db.getCollection("news").find();
			iterable.forEach(new Block<Document>() {
			    @Override
			    public void apply(final Document document) {
			    	NewsLine nl = new NewsLine();
			    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			    	try {
			    		DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			    		nl.newsDate =  df2.format(df.parse(document.getString("newsDate")));
					} catch (ParseException e) {
						logger.error("%%% Error converting date: " + document.getString("newsDate"));
					}  
			    	nl.newsHeadLine = document.getString("newsHeadLine");
			    	newsList.add(nl);
			    }
			});
		}
		catch (Exception ex) {
			logger.error("$$$ Exception getNews: " + ex.getMessage());
		}

		return newsList;
	}
	
	public boolean insertLogin(LoginCredential login) {
		boolean resultCode = false;
		if (!this.checkLogin(login)) {
			MongoDatabase db = null;
			try {
				db = this.client.getDatabase("backend");
				db.getCollection("login").insertOne(
						new Document()
						.append("name", login.getName())
						.append("email", login.getEmail())
						.append("password", login.getPassword())
				);
				resultCode = true;
			}
			catch (Exception ex) {
				logger.error("$$$ Exception getLogin: " + ex.getMessage());
			}			
		}

		return resultCode;

	}
	private boolean checkLogin(final LoginCredential login) {
		final BooleanFind bf = new BooleanFind(); 
		MongoDatabase db = null;
		try {
			db = this.client.getDatabase("backend");
			FindIterable<Document> iterable =  db.getCollection("login").find(
			        new Document("email", login.getEmail())
			        ).limit(1);
			iterable.forEach(new Block<Document>() {
			    @Override
			    public void apply(final Document document) {
			    	bf.foundSomething = true;
			    }
			});
		}
		catch (Exception ex) {
			logger.error("$$$ Exception getLogin: " + ex.getMessage());
			bf.foundSomething = false;
		}

		return bf.foundSomething;

	}
}
