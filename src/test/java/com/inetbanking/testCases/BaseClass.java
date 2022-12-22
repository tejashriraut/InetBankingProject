package com.inetbanking.testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    ReadConfig readConfig = new ReadConfig();
    public String baseUrl = readConfig.getApplicationUrl();
    public String userName = readConfig.getApplicationUserName();
    public String password = readConfig.getApplicationPassword();

    public static WebDriver driver;

    public  static Logger logger;
    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");

    @Parameters("browser")
    @BeforeClass
    public void setUp(String br)
    {
        logger = Logger.getLogger("ebanking");
        PropertyConfigurator.configure("Log4j.properties");

        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("MyReport");
        extent.attachReporter(spark);

        if (br.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
            driver = new ChromeDriver();
        }
        else if (br.equals("firefox")) {

            System.setProperty("webdriver.gecko.driver", readConfig.getFireFoxPath());
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseUrl);

    }



    @AfterSuite
    public void tearDown()
    {
        extent.flush();
        driver.quit();
    }

    public static String capturingScreenshots(WebDriver driver) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFilePath = new File(System.getProperty("user.dir") + "/Screenshots/screenshot" + System.currentTimeMillis()+ ".png");
        String absolutePathLocation = destinationFilePath.getAbsolutePath();
        FileUtils.copyFile(srcFile,destinationFilePath);
        return absolutePathLocation;
    }
}
