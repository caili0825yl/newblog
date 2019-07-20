package com.player.blog.POJO;

import com.player.blog.POJO.VO.ArticleVO;

import java.util.List;

public class ArticlePage {
    private List<ArticleVO> list;
   private Boolean first;
   private Boolean last;
   private int pages;
   public ArticlePage(List<ArticleVO> list, Boolean first, Boolean last,int pages){
        this.list=list;
        this.first=first;
        this.last=last;
        this.pages=pages;
    }

    public List<ArticleVO> getList() {
        return list;
    }

    public void setList(List<ArticleVO> list) {
        this.list = list;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
