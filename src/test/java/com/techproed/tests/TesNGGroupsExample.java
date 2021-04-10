package com.techproed.tests;


import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

    public class TesNGGroupsExample {

        // bu konuya kendim baktım ama eksik
        @BeforeGroups("security")
        public void setUpSecurity() {
            System.out.println("setUpSecurity()");
        }

        @AfterGroups("security")
        public void tearDownSecurity() {
            System.out.println("tearDownSecurity()\n");
        }

        @BeforeGroups("database")
        public void setUpDatabase() {
            System.out.println("setUpDatabase()");
        }

        @AfterGroups("database")
        public void tearDownDatabase() {
            System.out.println("tearDownDatabase()\n");
        }

        @BeforeGroups(value = "ui")
        public void setUpUI() {
            System.out.println("setUpUI()");
        }

        @AfterGroups(value = "ui")
        public void tearDownUI() {
            System.out.println("tearDownUI()\n");
        }

        @Test(priority = 0, groups = "database")
        public void testInsert() {
            System.out.println("testInsert()");
        }

        @Test(priority = 1, groups = "database")
        public void testUpdate() {
            System.out.println("testUpdate()");
        }

        @Test(priority = 2, groups = "database")
        public void testDelete() {
            System.out.println("testDelete()");
        }

        @Test(priority = 4, groups = "security")
        public void accessHomePage() {
            System.out.println("accessHomePage()");
        }

        @Test(priority = 5, groups = "security")
        public void accessAdminPage() {
            System.out.println("accessAdminPage()");
        }

        @Test(priority = 6, groups = "ui")
        public void openConfirmationDialog() {
            System.out.println("openConfirmationDialog()");
        }

        @Test(priority = 7, groups = "ui")
        public void openFileDialog() {
            System.out.println("openFileDialog()");
        }

    }

