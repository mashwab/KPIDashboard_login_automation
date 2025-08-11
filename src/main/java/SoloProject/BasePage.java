package SoloProject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(PageLoadWaitTime.LOADING_TIME));
    }

    public void waitForWebElement1(WebElement locator) {
        try {
            wait.until(ExpectedConditions.visibilityOf(locator));
        } catch (Exception e) {
            System.out.println(locator.toString() + " locator not found");
        }
    }


    public void scrollToElement(By locator) {
        try {
            waitForWebElement(locator);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].scrollIntoView(true)", locator);
        }
        catch (Exception e){
            System.out.println(locator.toString()+" element not found");
        }
    }


    public void waitForWebElement(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println(locator.toString() + " locator not found");
        }
    }


    public WebElement getWebElement(By locator) {
        WebElement webElement = null;
        try {
            waitForWebElement(locator);
            webElement = driver.findElement(locator);
        } catch (Exception e) {
            System.out.println(locator.toString() + " locator not found");
        }
        return webElement;
    }


    public void waitForLoader(By locator){
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }
        catch (Exception e) {
            System.out.println(locator.toString() + " locator not found");
        }
    }


    public void waitForDomStability(WebDriver driver, Duration stableDuration, Duration maxWait) {
        WebDriverWait hardWait = new WebDriverWait(driver, maxWait);
        long stableMillis = stableDuration.toMillis();
        long startTime = System.currentTimeMillis();
        long lastUnstable = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < maxWait.toMillis()) {
            boolean loaderVisible = isLoaderVisible(driver);

            if (loaderVisible) {
                lastUnstable = System.currentTimeMillis(); // Reset stable timer
            }

            if (System.currentTimeMillis() - lastUnstable >= stableMillis) {
                // Loaders have not appeared for the stableDuration
                return;
            }

            try {
                Thread.sleep(500); // poll every 500ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for DOM stability", e);
            }
        }

        throw new TimeoutException("DOM did not stabilize within the max wait time");
    }

    private boolean isLoaderVisible(WebDriver driver) {
        List<WebElement> apolloLoaders = driver.findElements(By.xpath("//div[contains(@class, 'apollo-loader-circle')]"));
        List<WebElement> spinnerLoaders = driver.findElements(By.xpath("//div[contains(@class, 'spinner-border')]"));

        for (WebElement loader : apolloLoaders) {
            if (loader.isDisplayed()) return true;
        }
        for (WebElement loader : spinnerLoaders) {
            if (loader.isDisplayed()) return true;
        }

        return false;
    }


    public WebElement scrollUntilElementFound(By locator, int maxScrolls) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = null;

        for (int i = 0; i < maxScrolls; i++) {
            try {
                element = driver.findElement(locator); // Try to find element
                if (element.isDisplayed()) {
                    // Scroll into view and return
                    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
                    return element;
                }
            } catch (NoSuchElementException e) {
                // Element not yet found — scroll down and try again
                js.executeScript("window.scrollBy(0, 500);");
            }

            // Small pause to allow new elements to load
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {

            }
        }

        throw new NoSuchElementException("Element not found after scrolling " + maxScrolls + " times: " + locator);
    }


}