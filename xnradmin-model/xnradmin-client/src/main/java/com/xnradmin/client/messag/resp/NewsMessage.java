package com.xnradmin.client.messag.resp;
import java.util.List;

public class NewsMessage extends BaseMessageResp
{
  private int ArticleCount;
  private List<Article> Articles;

  public int getArticleCount()
  {
    return this.ArticleCount;
  }

  public void setArticleCount(int articleCount) {
    this.ArticleCount = articleCount;
  }

  public List<Article> getArticles() {
    return this.Articles;
  }

  public void setArticles(List<Article> articles) {
    this.Articles = articles;
  }
}