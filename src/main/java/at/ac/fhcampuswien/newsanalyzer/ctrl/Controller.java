package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

	public static final String APIKEY = "18e30d99886c4adb81c7000d01d6af90";  //TODO add your api key
	//public static final String APIKEY = "8b865417560b4a34ae0b98af6be0ab3f";  //TODO add your api key

	public void process(String choice, String fromDate) {
		System.out.println("Start process");

		//TODO implement Error handling

		//TODO load the news based on the parameters

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)                      	//API Key is needed
				.setQ(choice)                           	//"keyword"
				//.setSourceCountry(Country.at)          	//relevant country
				.setFrom(fromDate)                        	//from which date on
				.setEndPoint(Endpoint.TOP_HEADLINES)    	//"how many articles to retrieve"
				.createNewsApi();

		//TODO implement methods for analysis
		NewsResponse newsResponse = newsApi.getNews();
		if (newsResponse != null) {
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
			//Number of articles
			System.out.println("--------------Analytics--------------");
			System.out.println("Number of articles: " + articles.size());
			System.out.println("Provides most articles: " + analiseProvider(articles));
			System.out.println("Author with shortest name: " + shortestAuthorName(articles));
			System.out.println("Articles sorted alphabetically: ");
			List<Article> sortedArticleList = sortArticles(articles);
			sortedArticleList.stream().forEach(article -> System.out.println(article.toString()));
		}

		System.out.println("End process");
	}


	public Object getData() {

		return null;
	}

	public String analiseProvider(List<Article> articleList) {
		Map<String, Integer> providerNames = new HashMap<>();
		for (Article article : articleList) {
			Integer value = providerNames.get(article.getSource().getName());
			if (value == null) {
				value = 0;
			}
			value++;
			providerNames.put(article.getSource().getName(), value);
		}
		int value = 0;
		String mostCommonName = null;
		for (String name : providerNames.keySet()) {
			int test = providerNames.get(name);
			if (test > value){
				value = test;
				mostCommonName = name;
			}
		}
		return mostCommonName;
	}

	public String shortestAuthorName(List<Article> articleList){
		int maxLength = 100;
		String shortestName = null;
		for (Article article  :articleList) {
			if(article.getAuthor() != null) {
				int length = article.getAuthor().length();
				if (length < maxLength) {
					maxLength = length;
					shortestName = article.getAuthor();
				}
			}
		}
		return shortestName;
	}

	public List<Article> sortArticles(List<Article> articleList){
		List<Article> sortedList = new ArrayList<>();
		return sortedList;
	}
}
