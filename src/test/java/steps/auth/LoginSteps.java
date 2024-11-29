package steps.auth;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
  private WebDriver driver;
  private final TestContext testContext;

  public LoginSteps() {
    testContext = new TestContext();
  }

  @Before
  public void setup() throws Exception {
    driver = testContext.getDriver();
  }

  @After
  public void tearDown() {
    testContext.quitDriver();
  }

  @Given("I am on the ifocus page")
  public void iAmOnTheLoginPage() {
    driver.get("https://dev.ifocusng.com/");
  }

  @Then("I should see the login page")
  public void iShouldSeeTheLoginPage() {
    // <button type="submit" class="btn btn-block rounded text-light
    // btn_login">LOGIN</button>
    WebElement loginButton = driver
        .findElement(By.cssSelector("button[type='submit'][class='btn btn-block rounded text-light btn_login']"));
    assertTrue(loginButton.isDisplayed());
  }

  @When("I input the credentials")
  public void iInputTheCredentials() {
    WebElement emailInput = driver.findElement(By.cssSelector(".form-group input[type='text']"));
    emailInput.sendKeys(System.getenv("EMAIL"));

    WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
    passwordInput.sendKeys(System.getenv("PASSWORD"));
  }

  // <h4 class="row jumbotron-title" style="margin-left: 0px;">Dashboard</h4>
  @Then("I should see the dashboard")
  public void iShouldSeeTheDashboard() {
    WebElement dashboardTitle = driver.findElement(By.cssSelector("h4.row.jumbotron-title"));
    assertTrue(dashboardTitle.isDisplayed());
  }

  @When("I click the login button")
  public void iClickTheLoginButton() {
    WebElement loginButton = driver
        .findElement(By.cssSelector("button[type='submit'][class='btn btn-block rounded text-light btn_login']"));
    loginButton.click();
  }
}
