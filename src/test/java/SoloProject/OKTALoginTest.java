package SoloProject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OKTALoginTest extends BaseTest{
    OKTALoginPage oktaLoginPage;
    PHPage phPage;

    @Test
    public void verifyRememberMeCheckboxIsChecked(){
        oktaLoginPage = new LandingPage(driver, wait)
                .clickLoginWithOKTAButton()
                .enterOKTAUsername("m.i.mashwab@gmail.com")
                .enterOKTAPassword("Abcd1234!")
                .checkRememberMeCheckbox();
    }

    @Test
    public void verifySuccessfulOKTASignIn(){
        phPage = new LandingPage(driver, wait)
                .clickLoginWithOKTAButton()
                .enterOKTAUsername("m.i.mashwab@gmail.com")
                .enterOKTAPassword("Abcd1234!")
                .clickSignInButtonForSuccessfulLogin();

        Assert.assertTrue(phPage.hasWelcomeHeaderTextDisplayed());
    }

    @Test
    public void verifyFailedOKTASignIn(){
        oktaLoginPage = new LandingPage(driver, wait)
                .clickLoginWithOKTAButton()
                .enterOKTAUsername("m.i.mashwab@gmail.com")
                .enterOKTAPassword("Abcd123")
                .clickSignInButtonForFailedLogin();

        Assert.assertTrue(oktaLoginPage.hasErrorTextDisplayed());
    }
}