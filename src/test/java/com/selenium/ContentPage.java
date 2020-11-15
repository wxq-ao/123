package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
*通讯录界面
*
* */
public class ContentPage {

    private WebDriver driver;

    public ContentPage(WebDriver driver) {
        driver = driver;
    }

    /*
    * 添加成员
    * */
    public ContentPage memberAdd(String username, String acctid, String mobile){

        return this;

    }

    //添加部门
    public ContentPage deptAdd(String deptName){

        return this;

    }

    //搜索部门
    public ContentPage searchDept(String deptName) throws InterruptedException {
//        click(By.id("menu_contacts"));

        sendKeys(By.id("memberSearchInput"),deptName);
        //获取css元素下的所有文本信息
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();

        //断言content中是否存在 提示内容

        return this;

    }

    public String getPartyInfo(){
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
        return content;
    }



    public void click(By by){
        driver.findElement(by).click();

    }

    public void sendKeys(By by,String content){
        driver.findElement(by).sendKeys(content);

    }



}
