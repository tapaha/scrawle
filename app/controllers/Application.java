package controllers;

import java.util.List;

import org.codehaus.jackson.node.ObjectNode;
import org.tapaha.util.CharEncoding;
import org.tapaha.util.DateCalculator;

import com.avaje.ebean.SqlRow;

import models.Article;
import play.*;
import play.mvc.*;
import play.mvc.BodyParser.Json;

import play.api.libs.json.*;
import views.html.*;

public class Application extends Controller {
  
  public static Result index() {
	String today_date = DateCalculator.getDate("yyyy-MM-dd");
    return ok(
    	index.render(
    		"Your new application is ready.", 
    		Article.getList(today_date), 
    		today_date,
    		DateCalculator.getYesterdayDate(today_date),
    		"null",
    		DateCalculator.getDate("yyyy-MM-dd")
    	)
    );
  }
  
  public static Result list(String req_date) {
    return ok(
    	index.render(
    		"Your new application is ready.",
    		Article.getList(req_date),
    		req_date,
    		DateCalculator.getYesterdayDate(req_date),
    		DateCalculator.getTomorrowDate(req_date),
    		DateCalculator.getDate("yyyy-MM-dd")
    	)
    );
  }
  
  public static Result view(Integer issue_id) {
	return ok(view.render(issue_id));
  }
  
  @BodyParser.Of(Json.class)
  public static Result viewJSON(Integer issue_id) {
	SqlRow sr = Article.getArticle(issue_id);
	ObjectNode result = play.libs.Json.newObject();
	result.put("status", "ok");
	result.put("title", CharEncoding.decode(sr.getString("title"), "UTF-8"));
	result.put("content", CharEncoding.decode(sr.getString("content"), "UTF-8"));
	result.put("article_no", sr.getInteger("article_no"));
	
	return ok(result);
  }
  
  public static Result changelog() {
	  return ok(changelog.render());
  }
}
