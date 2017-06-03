package com.demo.job;

import com.demo.pageview.IndexPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.Random;

/**
 * Created by monley_Lu on 2017/6/3.
 */
public class ReadNews {

    private IndexPage indexPage;
    public ReadNews(AndroidDriver<AndroidElement> driver) {
        setIndexPage(new IndexPage(driver));
    }

    public boolean readNews(){

        try{

            //wait main page load ready
            if (!indexPage.waitPageLoadReady(30)){
                System.out.println("main page load fail in 30 sec");
                return  Boolean.FALSE;
            }

            System.out.println("main page load ready,start read news");

            int sumNews = indexPage.getNewsCount();
            System.out.println("current page total news: " + sumNews);

            int newToRead= new Random().nextInt(sumNews);
            System.out.println("the new index to be read: " + newToRead);

            String newTitle = indexPage.getTitle(newToRead);
            System.out.println("new title is :" + newTitle);

            indexPage.readNew(newToRead);

            return  true;
        }catch (Exception e){
            System.out.println("something unexpect happen");
            System.out.println(e);
            return  false;
        }



    }

    public IndexPage getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(IndexPage indexPage) {
        this.indexPage = indexPage;
    }
}
