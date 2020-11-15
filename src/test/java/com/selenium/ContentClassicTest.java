package com.selenium;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContentClassicTest {

    public static WebDriver driver ;
    public static WebDriverWait driverWait;




    public static void needLogin() throws IOException, InterruptedException {
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome");

        Thread.sleep(10000);
        Set<Cookie> cookies =    driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"),cookies);
        System.exit(0);



    }

    @BeforeAll
    public static void beforeAll() throws IOException, InterruptedException {

        File file = new File("cookies.yaml");

        if(file.exists()) {
            driver = new ChromeDriver();
            driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driverWait = new WebDriverWait(driver,5);

            Thread.sleep(1000);
            //复用cookies进行登录
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {
            };
            List<HashMap<String, Object>> maps = (List<HashMap<String, Object>>) mapper.readValue(file, typeReference);
            System.out.println(maps);

            maps.forEach(cookieMap -> {
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));

            });
            //刷新当前界面
            driver.navigate().refresh();

        }else {
            needLogin();

        }


    }

    @Test
    public void contactAdd(){
        /*
        * 定位页面元素时：优先使用name -> id -> css -> xpath
        * linkText例外。
        * */
//        driver.findElement(By.cssSelector("[node-type=\"addmember\"]")).click();
        click(By.linkText("添加成员"));

        /*针对样板代码进行改造。封装click 和 sendkeys方法

        driver.findElement(By.linkText("添加成员")).click();
        driver.findElement(By.name("username")).sendKeys("王小庆-测试");
        driver.findElement(By.name("acctid")).sendKeys("202011-02");
        driver.findElement(By.name("mobile")).sendKeys("12312312313");
        driver.findElement(By.linkText("保存")).click();
        */
        sendKeys(By.name("username"),"王小庆-测试");
        sendKeys(By.name("acctid"),"202011-02");
        sendKeys(By.name("mobile"),"12312312313");


    }


    @Test
    public void search(){


    }

    @Test
    public void dept() throws InterruptedException {
        click(By.id("menu_contacts"));
        sendKeys(By.id("memberSearchInput"),"部门ID3");
        //获取css元素下的所有文本信息
        Thread.sleep(1000);
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();

        //断言content中是否存在 提示内容
//        assertTrue(content.contains("当前部门无任何成员"));
        assertTrue(content.contains("当前部门无任何成员"));

    }



    public void click(By by){
        driver.findElement(by).click();

    }

    public void sendKeys(By by,String content){
        driver.findElement(by).sendKeys(content);

    }

 /*   @AfterAll
    public static void quit() throws InterruptedException {
        Thread.sleep(7000);
        driver.quit();

    }*/


}
