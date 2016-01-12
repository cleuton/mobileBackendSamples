/*
Copyright 2015 Cleuton Sampaio de Melo Jr

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.obomprogramador.dropbackend;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.json.JSONObject;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class TestCreateLogin {
	private Logger logger = Logger.getLogger(this.getClass());
	@SuppressWarnings("deprecation")
	HttpClient httpclient = new DefaultHttpClient();
	@Test
	public void testOk() {
		String email = (new Date()).toString();
		
		assertTrue(
				sendRequest("/api/create", "{\"name\":\"fulano\",\"email\":\"" + email + "\", \"password\" : \"###fulano@@@\"}") == 200
		);
		
		assertTrue(
				sendRequest("/api/login", "{\"name\":\"fulano\",\"email\":\"" + email + "\", \"password\" : \"###fulano@@@\"}") == 200
		);
		
		MongoClient client = new MongoClient("localhost");
		MongoDatabase db = null;
		try {
			db = client.getDatabase("backend");
			db.getCollection("login").deleteOne(
					new Document()
					.append("email", email)
			);
		}
		catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testAlreadyExists() {

		assertTrue(
				sendRequest("/api/create", "{\"name\":\"fulano\",\"email\":\"fulano@teste\",\"password\":\"###\"}") == 400
		);		
	}
	
	
	private int sendRequest(String url, String sentity) {
		int resultCode = -1;
	    try {
		      HttpHost target = new HttpHost("localhost", 8080, "http");
		      HttpPost postRequest = new HttpPost(url);
		   
		  		StringEntity input = new StringEntity(sentity);
		  		input.setContentType("application/json");
		  		postRequest.setEntity(input);
		 
		      logger.debug("### executing request to " + target);
		 
		      HttpResponse httpResponse = httpclient.execute(target, postRequest);
		      HttpEntity entity = httpResponse.getEntity();
		 
		      logger.debug("### ----------------------------------------");
		      logger.debug("### " + httpResponse.getStatusLine());
		      Header[] headers = httpResponse.getAllHeaders();
		      for (int i = 0; i < headers.length; i++) {
		        logger.debug("### " + headers[i]);
		      }
		      logger.debug("### ----------------------------------------");
		 
		      if (entity != null) {
		        logger.debug("### " + EntityUtils.toString(entity));
		        resultCode = httpResponse.getStatusLine().getStatusCode();
		      }
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
	    return resultCode;
	}


}
