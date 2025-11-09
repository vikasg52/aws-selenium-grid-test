package tests;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;

public class GoogleTitleTest {

    private static final ThreadLocal<RemoteWebDriver> TL_DRIVER = new ThreadLocal<>();

    private static RemoteWebDriver driver() {
        return TL_DRIVER.get();
    }

    @Parameters({"browser", "gridUrl"})
    @BeforeMethod
    public void setUp(
            @Optional("chrome") String browser,
            @Optional("http://localhost:4444/wd/hub") String gridUrlFromXml
    ) throws Exception {

        // Priority: -DGRID_URL (mvn), then $GRID_URL (env), then testng.xml param, then localhost
        String gridUrl = System.getProperty(
                "GRID_URL",
                System.getenv().getOrDefault("GRID_URL", gridUrlFromXml)
        );

        MutableCapabilities caps = switch (browser.toLowerCase()) {
            case "firefox" -> new FirefoxOptions().addArguments("-headless");     // remove if you want visible
            case "chrome"  -> new ChromeOptions().addArguments("--headless=new"); // remove if you want visible
            default        -> new ChromeOptions().addArguments("--headless=new");
        };

        // Useful stability flags for containers
        if (caps instanceof ChromeOptions ch) {
            ch.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        }

        RemoteWebDriver drv = new RemoteWebDriver(new URL(gridUrl), (Capabilities) caps);
        drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        drv.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        TL_DRIVER.set(drv);
    }

    @Test
    public void googleTitleShouldContainGoogle() {
        driver().get("https://www.google.com/");
        String title = driver().getTitle();
        Assert.assertTrue(title.toLowerCase().contains("google"), "Title was: " + title);
    }

    // Keep or remove these copies as you like for parallelism
    @Test public void googleTitleShouldContainGoogl1e() { googleTitleShouldContainGoogle(); }
    @Test public void googleTitleShouldContainGoogle2() { googleTitleShouldContainGoogle(); }
    @Test public void googleTitleShouldContainGoogl3e() { googleTitleShouldContainGoogle(); }
    @Test public void googleTitleShouldContainGoogle4() { googleTitleShouldContainGoogle(); }
    @Test public void googleTitleShouldContainGoogle5() { googleTitleShouldContainGoogle(); }
    @Test public void googleTitleShouldContainGoogle6() { googleTitleShouldContainGoogle(); }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        RemoteWebDriver d = driver();
        if (d != null) {
            d.quit();
            TL_DRIVER.remove();
        }
    }
}
