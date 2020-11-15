package com.selenium;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static com.selenium.ContentClassicTest.needLogin;

/*
* page,程序中的每个界面，单独的一个page
*
*
* */
public class MainPage {

    WebDriver driver;
    WebDriverWait driverWait;

    public ContentPage goToContent(){
        click(By.linkText("添加成员"));
        return new ContentPage(driver);
    }

    public MainPage() throws IOException, InterruptedException {

        beforeAll();
    }


    public  void beforeAll() throws IOException, InterruptedException {

        File file = new File("cookies.yaml");


        driver = new ChromeDriver();
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverWait = new WebDriverWait(driver,5);

        Thread.sleep(1000);
        //复用cookies进行登录
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {};
        List<HashMap<String, Object>> maps = (List<HashMap<String, Object>>) mapper.readValue(file, typeReference);
        System.out.println(maps);

        maps.forEach(cookieMap -> {
            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));

        });
        //刷新当前界面
        driver.navigate().refresh();

    }

    public void click(By by){
        driver.findElement(by).click();

    }

    public void sendKeys(By by,String content){
        driver.findElement(by).sendKeys(content);

    }
}
