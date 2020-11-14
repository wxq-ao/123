package com.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.util.concurrent.TimeUnit;

public class WebTest {

    public static WebDriver driver;
    public static Actions actions;


    @BeforeAll
    public static void init(){
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    /*
    * 鼠标事件：单击、双击、右击
    * */
    @Test
    public void clickTest(){
        driver.get("http://sahitest.com/demo/clicks.htm");
        //鼠标单击
        actions.click(driver.findElement(By.cssSelector("[value=\"click me\"]")));
//        actions.perform();

        //鼠标双击
        actions.doubleClick(driver.findElement(By.cssSelector("[value=\"dbl click me\"]")));

        //数据右击
        actions.contextClick(driver.findElement(By.cssSelector("[value=\"right click me\"]")));

        actions.perform();


    }

    /*
    * 鼠标移动至某个元素上
    * */
    @Test
    public void moveTest(){
        driver.get("https://cn.bing.com/");
        actions.moveToElement(driver.findElement(By.id("sb_form_go")));

        actions.perform();

    }

    /*
    * 将元素拖拽至另一个元素上
    * */
    @Test
    public void drag(){
        driver.get("http://sahitest.com/demo/dragDropMooTools.htm");
        actions.dragAndDrop(driver.findElement(By.id("dragger")),driver.findElement(By.xpath("//*[@class=\"item\"][last()]"))).perform();
    }


    /*
    * 键盘事件
    * 将元素中的值 复制 到另一个元素中
    *
    * */
    @Test
    public void keyBoard(){
        driver.get("http://sahitest.com/demo/label.htm");
        driver.findElements(By.xpath("//*[@type=\"textbox\"]")).get(0).sendKeys("wxq");

        actions.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).perform();
        actions.keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).perform();
        actions.keyDown(driver.findElements(By.xpath("//*[@type=\"textbox\"]")).get(1),
                Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).perform();



    }

    /*
    * 界面滑动到底部
    * 百度搜索，滑动到最下面，点击下一页
    * */
    @Test
    public void scrollTest() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        driver.findElement(By.id("kw")).sendKeys("测试");


        TouchActions actions = new TouchActions(driver);

        actions.click(driver.findElement(By.id("su")));
//        driver.findElement(By.id("su")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");


        Thread.sleep(3000);

    }




    @AfterAll
    public static void quit(){
        try {
            Thread.sleep(3000);
            driver.quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }






}
