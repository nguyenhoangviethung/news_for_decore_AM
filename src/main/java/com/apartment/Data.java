package com.apartment;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Data {
    String title;
    String content = "";
    String linkImage;
    String summary;
    String linkArticle;
    static List<String> getArticle(String link) throws Exception {
        List<String> res = new ArrayList<String>();
        Document doc = Jsoup.connect(link).get();
        Elements articles = doc.select("h3");
        for(Element article : articles){
            String linkA = "https://baomoi.com" + article.select("a").attr("href");
            res.add(linkA);
        }
        return res;
    }

    void setSummary(Document doc){
        Element temp = doc.selectFirst("meta[name=description]");
        if(temp != null){
            this.summary = temp.attr("content");
        }
        else this.summary = null;
    }
    void setTitle(Document doc){
        Element temp = doc.selectFirst("h1");
        if(temp !=null){ 
            this.title = temp.text();
        }
        else this.title = null;

    }
    void setContent(Document doc) {
        Elements ps = doc.select("p").select(".text");
        if (ps != null && !ps.isEmpty()) {
            for (Element e : ps) {
                this.content += e.text() + "\n";
            }
        } else {
            this.content = null;    
        }
    }
    void setLinkImage(Document doc) {
        Element metaTag = doc.selectFirst("meta[property=og:image]");
        if (metaTag != null) {
            this.linkImage = metaTag.attr("content");
            if (this.linkImage.isEmpty()) {
            }
        } else {
            this.linkImage = null;
        }
    }
    
    boolean checkNullOrEmpty() {
        if (title == null || title.isEmpty()) {
            return true;  
        }
        if (content == null || content.isEmpty()) {
            return true;  
        }
        if (linkImage == null || linkImage.isEmpty()) {
            return true;  
        }
        if (summary == null || summary.isEmpty()) {
            return true;  
        }
        return false;  
    }
    
    
}
