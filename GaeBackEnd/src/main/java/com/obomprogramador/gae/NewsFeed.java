package com.obomprogramador.gae;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entities;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;


import com.google.appengine.api.users.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

@Api(name = "gaebackend",
	backendRoot = "http://localhost:8888/_ah/spi", // I am using http, but it should be HTTPS
    version = "v1",
    scopes = {Constants.EMAIL_SCOPE}
	,
    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID}
)
public class NewsFeed {

  private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

  static {
	  
		Query q = new Query(Entities.KIND_METADATA_KIND);
		Filter nameFilter =
		  new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, 
		  					  Query.FilterOperator.EQUAL,
		  					 Entities.createKindKey("newsLine"));
		q.setFilter(nameFilter);
		int contagem = datastore.prepare(q).countEntities(FetchOptions.Builder.withLimit(1));
		if (contagem == 0) {
			// Lets add some news:
			NewsArticle na = new NewsArticle("02/01/2016 7:21", "Breaking news #1");
			Entity entity = new Entity("newsLine");
			entity.setProperty("newsDate", na.getNewsDate());
			entity.setProperty("newsHeadLine", na.getNewsHeadLine());
			datastore.put(entity);
		}
  }

  // Parameter "User" requires that the client is authenticated
  public List<NewsArticle> listArticles(User user) {
	  Query qNews = new Query("newsLine");
	  PreparedQuery pq = datastore.prepare(qNews);
	  List<NewsArticle> articles = new ArrayList<NewsArticle>();
	  for (Entity result : pq.asIterable()) {
		  NewsArticle na = new NewsArticle((String) result.getProperty("newsDate"), 
				                           (String) result.getProperty("newsHeadLine"));
		  articles.add(na);
		}

	  return articles;
  }

}
