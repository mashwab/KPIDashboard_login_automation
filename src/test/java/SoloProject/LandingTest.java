package SoloProject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingTest extends BaseTest{
    LandingPage landingPage;
    AuthenticationPage authenticationPage;

    @Test
    public void verifySuccessfulLoginWithUsernameAndPassword(){
        authenticationPage = new LandingPage(driver, wait)
                .enterUsername("qa.prodops")
                .enterPassword("Iqvia@2025!Iqvia2025?!!")
                .clickLoginButtonForSuccessfulLogin();

        Assert.assertTrue(authenticationPage.isTwoFactorHeaderDisplayed());
    }

    @Test
    public void verifyFailedLogInWithUsernameAndPassword(){
        landingPage = new LandingPage(driver, wait)
                .enterUsername("qa.prodops")
                .enterPassword("Iqvia@2025!Iqvia2025")
                .clickLoginButtonForFailedLogin();

        Assert.assertTrue(landingPage.hasErrorMessageDisplayed());
    }
}