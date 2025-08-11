package SoloProject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    BasePage base;

    @BeforeMethod
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://pl-kpilib07-stg01-kpi20-eui.qa.kpi.oa.iqvia.com/");
//        driver.manage().window().maximize();

        base = new BasePage(driver, wait);
    }

    @AfterMethod
    public void tearDown() {
//        driver.quit();
    }
}
