package SoloProject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PHPage extends BasePage{

    public PHPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean hasWelcomeHeaderTextDisplayed(){
        return getWebElement(By.xpath("//h3[contains(text(), 'Welcome')]")).isDisplayed();
    }
}
