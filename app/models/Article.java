package models;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;

/**
 * Company entity managed by Ebean
 */
@Entity 
public class Article extends Model {

    @Id
    public Integer issue_id;
    @Column
    public Integer article_no;
    @Column
    public String title;
    @Column
    public String content;
    @Column
    public String nickname;
    @Column
    public Integer attach_cnt;
    @Column
    public Integer recommend_cnt;
    @Column
    public Integer view_cnt;
    @Column
    public String regtime;
    @Column
    public String crawled_time;
    
    /**
     * Generic query helper for entity Computer with id Long
     */
    public static Finder<Integer,Article> find = new Finder<Integer,Article>(Integer.class, Article.class); 
    
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Article> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("company")
                .findPagingList(pageSize)
                .getPage(page);
    }
    
    public static List<SqlRow> getList(String req_date) {
    	List<SqlRow> list = null;
    	
    	String sql = "SELECT COUNT(DISTINCT article_no) as CNT FROM SLR_ISSUE_ARTICLE";
    	
    	SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
    	
    	SqlRow sr = sqlQuery.findUnique();
    	
    	if(sr.getInteger("CNT") > 0) {
    		sql = "SELECT * FROM SLR_ISSUE_ARTICLE WHERE DATE_FORMAT(regtime, '%Y-%m-%d') = :date GROUP BY article_no ORDER BY regtime desc";
    		sqlQuery = Ebean.createSqlQuery(sql);
    		sqlQuery.setParameter("date", req_date);
    		
        	list = sqlQuery.findList();
    	}
    	return list;
    }
    
    public static SqlRow getArticle(Integer issue_id) {
    	List<SqlRow> list = null;
    	List<SqlRow> list_article = null;
    	SqlRow sr = null;
    	SqlRow ret = null;
    	
    	String sql = "SELECT * FROM SLR_ISSUE_ARTICLE WHERE issue_id = :issue_id";
    	
    	SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
    	sqlQuery.setParameter("issue_id", issue_id);
    	
    	list = sqlQuery.findList();
    	
    	if(list.get(0).getInteger("attach_cnt") > 0) {
    		sql = "SELECT a.*, b.path as path FROM SLR_ISSUE_ARTICLE as a, SLR_ISSUE_ARTICLE_ATTACHMENT as b WHERE a.issue_id = :issue_id and a.issue_id = b.issue_id";
    		sqlQuery = Ebean.createSqlQuery(sql);
    		sqlQuery.setParameter("issue_id", issue_id);
    		
        	list_article = sqlQuery.findList();
        	
        	ret = list_article.get(0);
    	} else {
    		ret = list.get(0);
    	}

    	return ret;
    }
}

