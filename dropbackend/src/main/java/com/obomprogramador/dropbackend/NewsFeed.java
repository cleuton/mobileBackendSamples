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

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NewsFeed {
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String FAIL = "fail";
	private String status;
	private String message;
	private List<NewsLine> data;
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	@JsonProperty
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}


	@JsonProperty
	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}


	@JsonProperty
	public List<NewsLine> getData() {
		return data;
	}



	public void setData(List<NewsLine> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writer().writeValueAsString(this);
		}
		catch(Exception ex) {
			logger.error("Error serializing NewsFeed: " + ex.getMessage());
			return null;
		}
	}

}
