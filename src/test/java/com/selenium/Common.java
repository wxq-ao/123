package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Common {

    WebDriver driver;

    public WebDriver click(By by){
        driver.findElement(by).click();
        return driver;

    }

    public WebDriver sendKeys(By by, String content){
        driver.findElement(by).sendKeys(content);
        return driver;
    }
}
