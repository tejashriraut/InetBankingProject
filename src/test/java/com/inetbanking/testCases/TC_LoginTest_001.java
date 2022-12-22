package com.inetbanking.testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.inetbanking.pageObjects.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTest_001 extends BaseClass {

    @Test
    public void loginTest() throws IOException {
        ExtentTest test = extent.createTest("verify user is able to login successfully").assignAuthor("Tejashri")
                .assignCategory("Functional testing");
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(userName);
        logger.info("Username is entered");

        lp.setPassword(password);
        logger.info("Password is entered");
        lp.clickSubmit();
        test.info("login is successfully");
        if(driver.getTitle().equals("Guru99 Bank Manager "))
        {
//            Assert.assertTrue(true);
//            logger.info("login test passed");
            test.pass("Verified title successfully");
        }
        else
        {
//            Assert.fail();
//            logger.info("login test failed");
            test.fail("verified title is failed");
            //logger.error("verified title is failed");
            test.addScreenCaptureFromPath(capturingScreenshots(driver));
        }
        logger.info("script end_________________________");
   }

   @Test
    public void testCase_02() throws IOException {
        ExtentTest test = extent.createTest("navigate to the link").assignAuthor("Tejashri")
                .assignCategory("Functional testing");

        test.info("navigating to about page");
        try {
            driver.findElement(By.linkText("about")).click();
            test.pass("user is on about page");
        }
        catch (Exception e)
        {

            test.fail("unexpected error in application :"+e.getMessage());
            test.addScreenCaptureFromPath(capturingScreenshots(driver));
        }
    }
}
