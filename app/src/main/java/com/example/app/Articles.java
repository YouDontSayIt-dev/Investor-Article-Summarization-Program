package com.example.app;

public class Articles {

    private String Article_Name;
    private String posPercent;
    private String negPercent;
    private String feedback;

    public String getArticle_Name() {
        return Article_Name;
    }

    public void setArticle_Name(String article_Name) {
        Article_Name = article_Name;
    }

    public String getPosPercent() {
        return posPercent;
    }

    public void setPosPercent(String posPercent) {
        this.posPercent = posPercent;
    }

    public String getNegPercent() {
        return negPercent;
    }

    public void setNegPercent(String negPercent) {
        this.negPercent = negPercent;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Articles() {

    }

}
