package com.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class AiceTest {

    static ChromeDriver driver;

    @BeforeAll
    public static  void init(){
//        System.setProperty("webdriver.chrome.driver","/Users/wangxiaoqing/Documents/tools/selenium/chromedriver");

        driver = new ChromeDriver();
    }

    @Test
    public  void login(){
        driver.get("https://ceshiren.com/");
        driver.findElement(By.xpath("//*[@id=\"ember5\"]/header/div/div/div[2]/span/button[2]/span")).click();
    }

    @AfterAll
    public static void tuichu(){
        driver.quit();
    }
}
