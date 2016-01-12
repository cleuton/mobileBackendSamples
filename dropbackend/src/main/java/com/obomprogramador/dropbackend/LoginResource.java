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

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;

@Path("/create")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
	private String dbUrl;
	private DrbDao dao;
	
	public LoginResource(String dbUrl) {
		this.dbUrl = dbUrl;
		this.dao = new DrbDao(dbUrl);
	}

	@POST
	@Timed
	public Response insereLogin(LoginCredential login) {
		int httpStatus = 200;
		String saida = "";
		if(dao.insertLogin(login)) {
			saida = "{\"status\" : \"success\", \"message\" : \"new user added\"}";
		}
		else {
			httpStatus = 400;
			saida = "{\"status\" : \"fail\", \"message\" : \"user already exists\"}";
		}
		return Response.status(httpStatus).entity(saida).build();

	}

}
