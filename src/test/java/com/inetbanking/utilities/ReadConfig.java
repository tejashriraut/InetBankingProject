package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties pro;
    public ReadConfig()
    {
        File src = new File("./Configurations/config.properties");
        try
        {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        }
        catch (Exception e) {

            System.out.println("Exception is :" + e.getMessage());
        }
    }

    public String getApplicationUrl()
    {
        return pro.getProperty("baseUrl");
    }

    public String getApplicationUserName()
    {
        return pro.getProperty("username");
    }
    public String getApplicationPassword()
    {
        return pro.getProperty("password");
    }
    public String getFireFoxPath()
    {
        return pro.getProperty("fireFoxPath");
    }

    public String getChromePath()
    {
        return pro.getProperty("chromePath");
    }
}
