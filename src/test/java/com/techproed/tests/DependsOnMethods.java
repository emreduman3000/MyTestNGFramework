package com.techproed.tests;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class DependsOnMethods {

    @Test(priority = 4-2)//2
    public void a() {System.out.println("a");}  //6

    @Test(priority = 1 , dependsOnMethods = "a")  //7
    public void b() {System.out.println("b");}

    //a
    //b


    @Test//(priority = 0)
    public void c() {System.out.println("c");}  //3

    @Test(dependsOnMethods = "c")//(priority = 0)
    public void d() {System.out.println("d");}    //4
    //c
    //d

    //@Test(dependsOnMethods = "d")
    //public void e() {System.out.println("e");}
    //run e() deyince c d e diye calısmaz



    @Ignore
    @Test(priority = 0)
    public void e() {System.out.println("e");}

      @Test(priority = 0)
      public void z()           //5
      {
          System.out.println("z");
      }

    @Test(priority = -1)
    public void w()         //2
    {
        System.out.println("w");
    }

    @Test(priority = -2)
    public void y()         //1
    {
        System.out.println("y");
    }




}
