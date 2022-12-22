package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginDDT_002 extends BaseClass {

    @Test(dataProviderClass = XLUtils.class,dataProvider = "testData")
    public void loginDDT(String user, String pwd) throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(user);
        logger.info("username is provided");
        lp.setPassword(pwd);
        logger.info("password is provided");
        lp.clickSubmit();
        Thread.sleep(3000);

        if(isAlertPresent() == true)
        {
            driver.switchTo().alert().accept(); //close alert
            driver.switchTo().defaultContent();
            logger.warn("Login failed");
        }
        else
        {
            Assert.assertTrue(true);
            logger.info("login passed");
            lp.clickLogout();
            Thread.sleep(3000);
            driver.switchTo().alert().accept(); //close logout alert
            driver.switchTo().defaultContent();

        }

    }

    public boolean isAlertPresent()
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException e)
        {
            return false;
        }
    }

}