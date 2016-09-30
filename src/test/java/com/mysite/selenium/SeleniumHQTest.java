package com.mysite.selenium;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;


public class SeleniumHQTest {

    public static final String USERNAME = "your saucelab username";
    public static final String ACCESS_KEY = "your access Key";
    public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
    @Test
    public void goToWebPage()
    {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows XP");
        caps.setCapability("version", "43.0");

        try {
            WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
            driver.get("https://minc.fbo.micropaas.io/");
            //System.out.printf(driver.getTitle());
            assertTrue(driver.getTitle().equals("FBO Home Page"));
            driver.findElement(By.cssSelector("a[href*='Notices']")).click();
            assertTrue(driver.getTitle().equals("FBO Unauthorized"));
            //System.out.printf("2"+driver.getTitle());
            //System.out.printf(driver.findElement(By.cssSelector("div.usa-grid-full > h1:first-child")).getText());
            assertTrue(driver.findElement(By.cssSelector("div.usa-grid-full > h1:first-child")).getText().equals("Unauthorized Access"));
            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
