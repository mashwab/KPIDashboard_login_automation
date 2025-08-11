package SoloProject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public LandingPage enterUsername(String username) {
        getWebElement(By.xpath("//label[text()='Username']/following::input[@name='UserName']")).sendKeys(username);
        return this;
    }

    public LandingPage enterPassword(String password) {
        getWebElement(By.id("password")).sendKeys(password);
        return this;
    }

    public AuthenticationPage clickLoginButtonForSuccessfulLogin() {  //used next navigated page as return type
        getWebElement(By.xpath("//ul/li/button[text()='Log in']")).click();
        return new AuthenticationPage(driver, wait); //created object for navigating to next page
    }

    public OKTALoginPage clickLoginWithOKTAButton() {
        scrollUntilElementFound(By.xpath("//button[@title='Log in using your Okta - Mash account']"), 10);
        getWebElement(By.xpath("//button[@title='Log in using your Okta - Mash account']")).click();
        return new OKTALoginPage(driver, wait);
    }

    public boolean hasFooterDisplayed(){
        scrollUntilElementFound(By.xpath("//p[contains(text(), 'Access is Restricted')]"), 10);
        return getWebElement(By.xpath("//p[contains(text(), 'Access is Restricted')]")).isDisplayed();
    }

    public LandingPage clickLoginButtonForFailedLogin() {  //used next navigated page as return type
        getWebElement(By.xpath("//ul/li/button[text()='Log in']")).click();
        return this;
    }

    public boolean hasErrorMessageDisplayed(){
        return getWebElement(By.xpath("//li[contains(text(),'Invalid login attempt')]")).isDisplayed();
    }
}
