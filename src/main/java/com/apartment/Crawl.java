package com.apartment;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Crawl {
    List<Data> getData(List<String> links) throws IOException {
        List<Data> data = new ArrayList<Data>();
        for(String link : links){
            Data item = new Data();
            Document doc = Jsoup.connect(link).ignoreHttpErrors(false).get();
            item.linkArticle = link;
            item.setTitle(doc);
            item.setSummary(doc);
            item.setContent(doc);
            item.setLinkImage(doc);
            data.add(item);
        }
        return data;
    }

    static void crawl(List<Data> data) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(data);

        FileWriter fw = new FileWriter("src\\main\\tmp\\data.json", Charset.forName("utf-8"));
        fw.write(json);
        fw.close();
    }
}
