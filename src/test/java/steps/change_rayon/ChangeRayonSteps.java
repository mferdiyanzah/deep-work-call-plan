package steps.change_rayon;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChangeRayonSteps {
  private WebDriver driver;
  private final TestContext testContext;

  public ChangeRayonSteps() {
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

  @Given("I am on the change rayon page")
  public void iAmOnTheChangeRayonPage() {
    driver.get("https://dev.ifocusng.com/system/ChangeRayonCode");
  }

  // <h4 class="row jumbotron-title" style="margin-left: 0px;">Change Rayon
  // Code</h4>
  @Then("I should see the change rayon page")
  public void iShouldSeeTheChangeRayonPage() {
    WebElement changeRayonTitle = driver.findElement(By.cssSelector("h4.row.jumbotron-title"));
    assertTrue(changeRayonTitle.isDisplayed());
  }

  @Then("I should see the rayon code input")
  public void iShouldSeeTheRayonCodeInput() {
    WebElement rayonCodeInput = driver.findElement(By.id("rayonCode"));
    assertTrue(rayonCodeInput.isDisplayed());
  }

  @When("I input the rayon code {string}")
  public void iInputTheRayonCode(String rayonCode) {
    WebElement rayonCodeInput = driver.findElement(By.id("rayonCode"));
    rayonCodeInput.sendKeys(rayonCode);
  }

  @When("I click the change rayon code session button")
  public void iClickTheChangeRayonCodeSessionButton() {
    WebElement changeRayonCodeSessionButton = driver
        .findElement(By.cssSelector("button[id-test='changeRayonCodeSession']"));
    changeRayonCodeSessionButton.click();
  }

  @Then("I should see the change rayon code success message")
  public void iShouldSeeTheChangeRayonCodeSuccessMessage() {
    WebElement changeRayonCodeSuccessMessage = driver
        .findElement(By.cssSelector("div.swal2-popup.swal2-modal.swal2-icon-success.swal2-show"));
    assertTrue(changeRayonCodeSuccessMessage.isDisplayed());
  }
}
