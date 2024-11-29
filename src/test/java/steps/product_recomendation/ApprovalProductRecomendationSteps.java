package steps.product_recomendation;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApprovalProductRecomendationSteps {
  private WebDriver driver;
  private final TestContext testContext;

  public ApprovalProductRecomendationSteps() {
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

  @Given("I am on the approval product recomendation page")
  public void iAmOnTheApprovalProductRecomendationPage() {
    driver.get("https://dev.ifocusng.com/product-recommendation/approval");
  }

  @Then("I should see the approval product recomendation page")
  public void iShouldSeeTheApprovalProductRecomendationPage() {
    WebElement approvalProductRecomendationPage = driver.findElement(By.cssSelector("h4[class='row jumbotron-title']"));
    assertTrue(approvalProductRecomendationPage.getText().equals("Approval"));
  }

  @When("I click the search button of approval product recomendation page")
  public void iClickTheSearchButtonOfApprovalProductRecomendationPage() {
    WebElement searchButton = driver.findElement(By.cssSelector("button[data-testid='search-button']"));
    searchButton.click();

    // wait for 5 seconds
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Then("I should see unauthenticated message")
  public void iShouldSeeUnauthenticatedMessage() {
    WebElement unauthenticatedMessage = driver.findElement(By.cssSelector("div[class='swal2-html-container']"));
    assertTrue(unauthenticatedMessage.getText().equals("You are not authorized"));
  }

  @Then("I should see the list of approval product recomendation")
  public void iShouldSeeTheListOfApprovalProductRecomendation() {
    try {
      WebElement approvalProductRecomendationList = driver
          .findElement(By.cssSelector("div[class='ant-empty-description']"));
      assertFalse(approvalProductRecomendationList.isDisplayed());
    } catch (NoSuchElementException e) {
      // Element does not exist
      assertTrue(true);
    }
  }

  @When("I click one of the list of approval product recomendation")
  public void iClickOneOfTheListOfApprovalProductRecomendation() {
    // <td class="ant-table-cell">TENSIVASK</td>
    List<WebElement> approvalProductRecomendationList = driver
        .findElements(By.cssSelector("td[class='ant-table-cell']"));
    approvalProductRecomendationList.get(0).click();
  }

  @Then("I should see the modal of approval product recomendation")
  public void iShouldSeeTheModalOfApprovalProductRecomendation() {
    WebElement approvalProductRecomendationDetail = driver
        .findElement(By.cssSelector("h3[class='ant-typography css-g24l0l']"));
    assertTrue(approvalProductRecomendationDetail.getText().equals("Product Recommendation Approval Detail"));
  }

  @When("I click the approve button of approval product recomendation")
  public void iClickTheApproveButtonOfApprovalProductRecomendation() {
    WebElement approveButton = driver.findElement(By.cssSelector("button[data-testid='approve-button']"));
    approveButton.click();
  }

  @Then("I should see the approval product recomendation warning message")
  public void iShouldSeeTheApprovalProductRecomendationWarningMessage() {
    WebElement approvalProductRecomendationWarningMessage = driver
        .findElement(By.cssSelector("div[class='swal2-html-container']"));
    assertTrue(approvalProductRecomendationWarningMessage.getText().equals("Are you sure to approve ?"));
  }

  @When("I click the yes button of approval product recomendation")
  public void iClickTheYesButtonOfApprovalProductRecomendation() {
    WebElement yesButton = driver
        .findElement(By.cssSelector("button[class='swal2-confirm swal2-styled swal2-default-outline']"));
    yesButton.click();

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Then("I should see the approval product recomendation success message")
  public void iShouldSeeTheApprovalProductRecomendationSuccessMessage() {
    WebElement approvalProductRecomendationSuccessMessage = driver
        .findElement(By.cssSelector("div[class='swal2-html-container']"));
    System.out.println(approvalProductRecomendationSuccessMessage.getText());
    assertTrue(approvalProductRecomendationSuccessMessage.getText().equals("Campaign status updated successfully"));
  }
}
