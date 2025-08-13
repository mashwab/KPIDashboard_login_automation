package SoloProject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    BasePage base;
    protected final Properties property = new Properties();
    String propertyFilePath = System.getProperty("user.dir")+ File.separator + "src" + File.separator + "test" + File.separator + "Resources" + File.separator + "config.properties";


    @BeforeMethod
    public void openBrowser() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(propertyFilePath);
        } catch (FileNotFoundException e) {
            System.out.println(propertyFilePath + " file not found");
        }
        try {
            property.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String browserName = property.getProperty("browserName");
        String url = property.getProperty("baseURL");

        if(browserName.equalsIgnoreCase("FIREFOX")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("CHROME")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            System.out.println("No such browser has been found");
        }


        driver.get(url);
        driver.manage().window().maximize();

        base = new BasePage(driver, wait);
    }

    @AfterMethod
    public void tearDown() {
//        driver.quit();
    }
}
