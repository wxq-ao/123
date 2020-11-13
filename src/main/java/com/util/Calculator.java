package com.util;

public class Calculator {

    public static int result = 0;

    public static int add(int x , int y ){

        return result = x + y ;

    }

    public  static int count(int x  ){

        result += x;

        return result ;

    }


    public static int subtract(int x , int y){

        return result = x - y ;

    }

    public static int multiply(int x , int y){

        return result = x * y ;

    }

    public static int divide(int x , int y){

        return result = x / y ;

    }

    public static void clear(){
        result = 0;
    }
}
