package tests;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URI;

public class GoogleTitleTest {

    private RemoteWebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) throws Exception {
        String gridUrl = System.getenv().getOrDefault("GRID_URL", "http://13.235.140.25:4444/wd/hub");

        MutableCapabilities caps;
        switch (browser.toLowerCase()) {
            case "firefox":
                caps = new org.openqa.selenium.firefox.FirefoxOptions();
                break;
            case "chrome":
            default:
                caps = new org.openqa.selenium.chrome.ChromeOptions();
                break;
        }

        // Example: run headless inside containers (optional)
        if (caps instanceof org.openqa.selenium.chrome.ChromeOptions chrome) {
            chrome.addArguments("--headless=new");
        } else if (caps instanceof org.openqa.selenium.firefox.FirefoxOptions ff) {
            ff.addArguments("-headless");
        }

        driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), (Capabilities) caps);
    }

    @Test
    public void googleTitleShouldContainGoogle() {
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("google"), "Title was: " + title);
    }

    @Test
    public void googleTitleShouldContainGoogl1e() {
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("google"), "Title was: " + title);
    }
    @Test
    public void googleTitleShouldContainGoogle2() {
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("google"), "Title was: " + title);
    }
    @Test
    public void googleTitleShouldContainGoogl3e() {
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("google"), "Title was: " + title);
    }
    @Test
    public void googleTitleShouldContainGoogle4() {
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("google"), "Title was: " + title);
    }
    @Test
    public void googleTitleShouldContainGoogle5() {
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("google"), "Title was: " + title);
    }
    @Test
    public void googleTitleShouldContainGoogle6() {
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("google"), "Title was: " + title);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
