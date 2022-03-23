import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtractPage {

    public static String URLtoCheck = "https://www.placelab.com/extract";
    public String browser;
    public WebDriver driver;

    public ExtractPage() {

    }

    @BeforeTest
    public void openBrowser() {
        this.browser = "Firefox";

        if (this.browser.contains("Chrome")) {
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
        } else if (this.browser.contains("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
        }
        this.driver.navigate().to(URLtoCheck);
    }

    @Test
    public void verifyLetsTalkRedButton() {
        String expectedText = "LET'S TALK";
        String actualText = "";
        String otherOccurrence = "";

        By by = new By.ByCssSelector(".col-lg-6 > div:nth-child(1) > a:nth-child(3) > button:nth-child(1) > div:nth-child(2)");
        By by2 = new By.ByXPath("//a[@class=\"nav-link lets-talk-header\"]");


        actualText = this.driver.findElement(by).getText();
        otherOccurrence = this.driver.findElement(by2).getText();

        Assert.assertEquals(actualText, expectedText);
        Assert.assertEquals(actualText, otherOccurrence);

    }

    @AfterTest
    public void closeBrowser() {
        this.driver.close();
    }

}
