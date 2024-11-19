package testsuite;
/*
userSholdLoginSuccessfullyWithValidCredentials()

* Enter “tomsmith” for the username
* Enter “SuperSecretPassword!” for the
password
* Click on the ‘Login’ button
* Verify the text “Secure Area”
* Click on the ‘Logout’ button
* Verify the text ‘You logged out of the secure area!’

2. verifyTheUsernameErrorMessage()

* Enter “tomsmith1” for the username
* Enter “SuperSecretPassword!” for the
password
* Click on the ‘Login’ button
* Verify the error message “Your username is invalid!”

3. verifyThePasswordErrorMessage()

* Enter “tomsmith” for the username
* Enter “SuperSecretPassword” for the
password
* Click on the ‘Login’ button
* Verify the error message “Your password is invalid!”
 */

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("fa-sign-in")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


        String actualSecureAreaText = driver.findElement(By.tagName("h2")).getText();
        driver.findElement(By.cssSelector("a[href='/logout']")).click();
        WebElement logoutMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(logoutMessage.getText().contains("You logged out of the secure area!"));

    }
    @Test
    public void verifyTheUsernameErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("fa-sign-in")).click();
        WebElement errorMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(errorMessage.getText().contains("Your username is invalid!"));
    }
    @Test
    public void verifyThePasswordErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.className("fa-sign-in")).click();
        WebElement errorMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(errorMessage.getText().contains("Your password is invalid!"));

    }
    @After
    public void tearDown() {
        closeBrowser();

    }



    }



