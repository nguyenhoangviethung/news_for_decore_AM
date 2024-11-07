package com.apartment;

import java.util.List;
import static spark.Spark.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;


public class Main{
    public static void main(String[] args) throws Exception {
        List<String> links = Data.getArticle("https://baomoi.com/tag/chung-c%C6%B0.epi");
        Crawl crawl_obj = new Crawl();
        Crawl.crawl(crawl_obj.getData(links));
        System.out.println("Hello world!");

        port(8080);

        get("/news-for-decorate/get-json", (req, res) -> {
            res.type("app/json");
            String json =  new String(Files.readAllBytes(Paths.get("src\\main\\tmp\\data.json")), StandardCharsets.UTF_8);
            return json;
        });

    }
}
