package com.project.dbwt;

import java.io.IOException;
import java.net.URL;

import com.project.dbwt.model.NewsAggregatorModel;
import com.project.dbwt.repository.NewsAggregatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import org.springframework.context.annotation.Bean;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class NewsAggregatorApplication {



    public static void main(String[] args) throws IllegalArgumentException, FeedException, IOException {
        SpringApplication.run(NewsAggregatorApplication.class, args);

    }

    //URL feedsource = new URL("https://timesofindia.indiatimes.com/rssfeeds/4719148.cms");
    //URL feedsource = new URL("http://jvm-bloggers.com/pl/rss.xml");
    //https://zapier.com/blog/feeds/latest/
    //http://www.espncricinfo.com/rss/content/story/feeds/2.xml
    //https://www.blog.google/rss/
    //https://www.perspektive-wiedereinstieg.de/Navigation/DE/Service/RSS/RSS.xml;jsessionid=923DC04707CF17E8B274E8493EE8055A?nn=158628
    //

    @Bean
    public CommandLineRunner demo(NewsAggregatorRepository newsAggregatorRepository) {
        return (args) -> {
            // save a couple of customers
            URL feedsource = new URL("http://jvm-bloggers.com/pl/rss.xml");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedsource));
            List res = feed.getEntries();
            //System.out.println("--------------------Titles--------------");

            for(Object o : res){
                System.out.println("----111111------");
                NewsAggregatorModel newsAggregatorModel = new NewsAggregatorModel();

                newsAggregatorModel.setTitle(((SyndEntryImpl) o).getTitle());
                newsAggregatorModel.setAuthor(((SyndEntryImpl) o).getAuthor());
                newsAggregatorModel.setLink(((SyndEntryImpl) o).getLink());
                newsAggregatorModel.setPublishedDate(((SyndEntryImpl) o).getPublishedDate());
                newsAggregatorModel.setUpdatedDate(((SyndEntryImpl) o).getUpdatedDate());
                newsAggregatorRepository.save(newsAggregatorModel);
            }
            /*System.out.println("--------------------Links--------------");
            for(Object o : res){

                System.out.println(((SyndEntryImpl) o).getLink());
            }
            System.out.println("--------------------Authors--------------");
            for(Object o : res){

                System.out.println(((SyndEntryImpl) o).getAuthor());
            }
            System.out.println("--------------------Published Date--------------");
            for(Object o : res){

                System.out.println(((SyndEntryImpl) o).getPublishedDate());
            }
            System.out.println("--------------------Updated Date--------------");
            for(Object o : res){

                System.out.println(((SyndEntryImpl) o).getUpdatedDate());
            }*/
        };
    }


        //
}



