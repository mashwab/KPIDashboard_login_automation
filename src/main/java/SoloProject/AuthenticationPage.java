package SoloProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationPage extends BasePage{
    public AuthenticationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isTwoFactorHeaderDisplayed(){
        return getWebElement(By.xpath("//h1[contains(text(), 'Two-Factor')]")).isDisplayed();
    }

    public AuthenticationPage enter2FACode(String password){
        getWebElement(By.id("TwoFactorCode")).sendKeys(password);
        return this;
    }

    public PortalPage clickVerifyButtonForSuccessfulLogin(){
        getWebElement(By.xpath("//button[@type='submit']")).click();
        return new PortalPage(driver, wait);
    }

    public LandingPage clickReturnToLandingPage(){
        getWebElement(By.xpath("//a[text()='Return to Login page']")).click();
        return new LandingPage(driver, wait);
    }

    public AuthenticationPage clickResendButton(){
        getWebElement(By.xpath("//a[contains(text(), 'Resend verification')]")).click();
        return this;
    }

    public boolean hasVerificationCodeSentTextDisplayed(){
        return getWebElement(By.xpath("//div[contains(text(), 'Verification')]")).isDisplayed();
    }

    public AuthenticationPage clickVerifyButtonForFailedLogin(){
        getWebElement(By.xpath("//button[@type='submit']")).click();
        return this;
    }

    public boolean hasVerificationCodeIsIncorrectTextDisplayed(){
        return getWebElement(By.xpath("//li[contains(text(),'Verification code is incorrect')]")).isDisplayed();
    }
}
