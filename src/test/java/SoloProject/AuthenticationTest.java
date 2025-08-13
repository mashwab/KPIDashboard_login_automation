package SoloProject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthenticationTest extends BaseTest{
    PortalPage portalPage;
    LandingPage landingPage;
    AuthenticationPage authenticationPage;

    @Test
    public void verifySuccessfulLoginWith2FACode(){
        portalPage  = new LandingPage(driver, wait)
                .enterUsername("qa.prodops")
                .enterPassword("Iqvia@2025!Iqvia2025?!!")
                .clickLoginButtonForSuccessfulLogin()
                .enter2FACode("1234")
                .clickVerifyButtonForSuccessfulLogin();

        Assert.assertTrue(portalPage.isBusinessUnitTextDisplayed());
    }

    @Test
    public void verifyReturnToLoginButton(){
        landingPage = new LandingPage(driver, wait)
                .enterUsername("qa.prodops")
                .enterPassword("Iqvia@2025!Iqvia2025?!!")
                .clickLoginButtonForSuccessfulLogin()
                .clickReturnToLandingPage();

        Assert.assertTrue(landingPage.hasFooterDisplayed());
    }

    @Test
    public void verifyResendVerificationCodeButton(){
        authenticationPage = new LandingPage(driver, wait)
                .enterUsername("qa.prodops")
                .enterPassword("Iqvia@2025!Iqvia2025?!!")
                .clickLoginButtonForSuccessfulLogin()
                .clickResendButton();

        Assert.assertTrue(authenticationPage.hasVerificationCodeSentTextDisplayed());
    }

    @Test
    public void verifyFailedLoginWith2FACode(){
        authenticationPage  = new LandingPage(driver, wait)
                .enterUsername("qa.prodops")
                .enterPassword("Iqvia@2025!Iqvia2025?!!")
                .clickLoginButtonForSuccessfulLogin()
                .enter2FACode("123")
                .clickVerifyButtonForFailedLogin();

        Assert.assertTrue(authenticationPage.hasVerificationCodeIsIncorrectTextDisplayed());
    }
}