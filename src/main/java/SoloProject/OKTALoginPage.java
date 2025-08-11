package SoloProject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OKTALoginPage extends BasePage{
    public OKTALoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public OKTALoginPage enterOKTAUsername(String username){
        getWebElement(By.id("okta-signin-username")).sendKeys(username);
        return this;
    }

    public OKTALoginPage enterOKTAPassword(String password){
        getWebElement(By.id("okta-signin-password")).sendKeys(password);
        return this;
    }

    public OKTALoginPage checkRememberMeCheckbox(){
        getWebElement(By.xpath("//div[@class='custom-checkbox']/label[@for='input42']")).click();
        return this;
    }

    public PHPage clickSignInButtonForSuccessfulLogin(){
        getWebElement(By.id("okta-signin-submit")).click();
        return new PHPage(driver, wait);
    }

    public OKTALoginPage clickSignInButtonForFailedLogin(){
        getWebElement(By.id("okta-signin-submit")).click();
        return this;
    }

    public boolean hasErrorTextDisplayed(){
        return getWebElement(By.xpath("//p[contains(text(), 'Unable')]")).isDisplayed();
    }
}