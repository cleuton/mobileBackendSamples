package com.obomprogramador.gae;

public class NewsArticle {

  public String newsDate;
  public String newsHeadLine;
	public NewsArticle() {
		super();
	}
	public NewsArticle(String newsDate, String newsHeadLine) {
		super();
		this.newsDate = newsDate;
		this.newsHeadLine = newsHeadLine;
	}
	public String getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}
	public String getNewsHeadLine() {
		return newsHeadLine;
	}
	public void setNewsHeadLine(String newsHeadLine) {
		this.newsHeadLine = newsHeadLine;
	}
	
  
}
