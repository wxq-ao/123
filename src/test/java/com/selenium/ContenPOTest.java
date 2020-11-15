package com.selenium;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContenPOTest {


    @Test
    public void memberAddTest() throws IOException, InterruptedException {
        /*
        * 打开界面
        * 复用session登录
        *
        * */

        MainPage mainPage = new MainPage();
        //mainPage.goToContent().memberAdd("wxq","","");


        ContentPage contentPage = mainPage.goToContent();
        contentPage.searchDept("部门ID3");
        String content =contentPage.getPartyInfo();
        assertTrue(content.contains("当前部门无任何成员"));




    }
}
