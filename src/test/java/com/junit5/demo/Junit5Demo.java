package com.junit5.demo;

import com.util.Calculator;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Junit5Demo {

    @Test
    public void addTest(){

        int result = Calculator.add(1,2);
        int result2 = Calculator.add(2,2);
        assertAll("msg:多个组合断言assertAll",
                ()-> assertEquals(3,result),
                ()-> assertEquals(4,result2)


        );
    }

    @Test
    public void subtractTest(){

        int result = Calculator.subtract(5,2);
        System.out.println(result);
        assertEquals(3,result);

    }

    @Test
    public void multiplyTest(){

        int result = Calculator.multiply(5,2);
        System.out.println(result);
        assertEquals(10,result);

    }

    @Test
    public void divideest(){

        int result = Calculator.divide(5,2);
        System.out.println(result);
        assertEquals(2,result);

    }

    @Test
    public void countTest(){

        Calculator.count(1);
        Calculator.count(3);
        Calculator.count(4);
        int result = Calculator.count(1);
        System.out.println(result);
        assertEquals(9,result);

    }

    @Test
    @BeforeEach
    public void clearTest(){
        Calculator.clear();

    }



}
