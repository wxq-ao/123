package com.selenium;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestWeb {

    public static WebDriver driver ;
    public static Actions actions;

    @BeforeAll
    public static void init(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions = new Actions(driver);

    }


    @Test
    public void loginTest() throws IOException, InterruptedException {
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome");

        Thread.sleep(10000);
        Set<Cookie> cookies =    driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"),cookies);



    }

    @Test
    public void loginedTest() throws IOException, InterruptedException {
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome");

        Thread.sleep(1000);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference typeReference = new TypeReference<List<HashMap<String,Object>>>() {};
        List<HashMap<String,Object>> maps= (List<HashMap<String, Object>>) mapper.readValue(new File("cookies.yaml"),typeReference);
        System.out.println(maps);

        maps.forEach(cookieMap->{
            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));

        });
        //刷新当前界面
        driver.navigate().refresh();

        actions.click(driver.findElement(By.cssSelector("[node-type=\"addmember\"]")));
        actions.perform();

        //执行actions的鼠标操作，若无此行代码，则所有操作都不执行

        Random random = new Random();
        int rand = random.nextInt(9000);

        driver.findElement(By.cssSelector("#username")).sendKeys("王小庆-测试");
        driver.findElement(By.cssSelector("#memberAdd_acctid")).sendKeys("202011");
        driver.findElement(By.cssSelector("#memberAdd_phone")).sendKeys("12312312312");
        actions.click(driver.findElement(By.cssSelector("[class=\"qui_btn ww_btn js_btn_save\"]")));

        actions.perform();

    }


}
