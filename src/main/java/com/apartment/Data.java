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
        this.summary = doc.selectFirst("meta[name=description]").attr("content");
    }
    void setTitle(Document doc){
        this.title = doc.selectFirst("h1").text();
    }
    void setContent(Document doc){
        Elements ps = doc.select("p").select(".text");
        for(Element e : ps){
            this.content += e.text() + "\n";
        }
    }
    void setLinkImage(Document doc){
        this.linkImage = doc.selectFirst("meta[property=og:image]").attr("content");
    }
}
