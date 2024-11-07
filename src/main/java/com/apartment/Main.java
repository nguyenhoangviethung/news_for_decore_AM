package com.apartment;

import java.util.List;

public class Main{
    public static void main(String[] args) throws Exception {
        List<String> links = Data.getArticle("https://baomoi.com/tag/chung-c%C6%B0.epi");
        // // for(String link : links){
            
        // // }
        // Document doc = Jsoup.connect(links.get(0)).get();
        //     Data article = new Data();
        //     article.setLinkImage(doc);
        //     System.out.println(article.linkImage);
        Crawl ehehe = new Crawl();
        Crawl.crawl(ehehe.getData(links));
        System.out.println("Hello world!");
    }
}