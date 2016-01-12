package com.obomprogramador.dropbackend;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NewsLine {
	@JsonProperty
	public String newsDate;
	@JsonProperty
	public String newsHeadLine;
	@Override
	public String toString() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writer().writeValueAsString(this);
		}
		catch(Exception ex) {
			return null;
		}
	}

}
