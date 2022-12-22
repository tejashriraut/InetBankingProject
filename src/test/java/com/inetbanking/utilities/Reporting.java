package com.inetbanking.utilities;
//Listener class used to generate extend report
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking.testCases.BaseClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Reporting extends BaseClass implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest logger;
    public void onStart(ITestContext testContext)
    {
        String timeStamp = new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss").format(new Date()); //time stamp
        String repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/target" +repName); //specify location
        try {
            sparkReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Host Name", "localhost");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("user", "Tejashri");
        sparkReporter.config().setDocumentTitle("InetTestBanking Test Project"); //title of reports
        sparkReporter.config().setReportName("Functional Test Automation Test Reports"); //name of the reports
        sparkReporter.config().setTheme(Theme.DARK);
        System.out.println("On start_________");
    }

    public void onTestSuccess(ITestResult iTestResult)
    {
        logger = extentReports.createTest(iTestResult.getName()); //Create the new entry in the report
        logger.log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.GREEN)); // sent the passed information
        System.out.println("On test success_________");
    }



    public  void onTestFailure(ITestResult iTestResult)
    {
        logger = extentReports.createTest(iTestResult.getName()); //Create the new entry in the reports
        logger.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.RED)); //send passed information for test fail
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" +iTestResult.getName()+ ".png";
        File f = new File(screenshotPath);
        if (f.exists())
        {
            try {
                logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("On test fail_________");
    }

    public void onTestSkipped(ITestResult iTestResult)
    {
        logger = extentReports.createTest(iTestResult.getName());
        logger.log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.ORANGE));
        System.out.println("On test skipped_________");
    }

    public  void onFinished(ITestResult testContext)
    {
        extentReports.flush();
        System.out.println("On test finished_________");
    }


}