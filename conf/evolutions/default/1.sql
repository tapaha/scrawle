# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table article (
  issue_id                  integer auto_increment not null,
  article_no                integer,
  title                     varchar(255),
  content                   varchar(255),
  nickname                  varchar(255),
  attach_cnt                integer,
  recommend_cnt             integer,
  view_cnt                  integer,
  regtime                   varchar(255),
  crawled_time              varchar(255),
  constraint pk_article primary key (issue_id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table article;

SET FOREIGN_KEY_CHECKS=1;

