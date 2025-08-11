package SoloProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class PortalPage extends BasePage {
    public PortalPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isBusinessUnitTextDisplayed() {
        waitForDomStability(driver, Duration.ofMillis(1500), Duration.ofSeconds(30));
        WebElement element = getWebElement(By.xpath("//span[contains(text(), 'Business')]"));
        return element.isDisplayed();
    }
}