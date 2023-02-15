package com.test.demo.crawler;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawler {
	public static void main(String[] args) throws IOException {
		String URL = "https://cjsrhd94.tistory.com/140";

		Connection conn = Jsoup.connect(URL);

		Document html =  conn.get();

		Elements elements = html.select(".profile li");

		System.out.println(elements.toString());
	}
}
