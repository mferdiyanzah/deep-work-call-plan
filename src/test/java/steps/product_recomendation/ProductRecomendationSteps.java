package steps.product_recomendation;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductRecomendationSteps {
  private WebDriver driver;
  private final TestContext testContext;

  public ProductRecomendationSteps() {
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

  @Given("I am on the product recomendation page")
  public void iAmOnTheProductRecomendationPage() {
    driver.get("https://dev.ifocusng.com/product-recommendation/overview");
  }

  @Then("I should see the product recomendation page")
  public void iShouldSeeTheProductRecomendationPage() {
    WebElement productRecomendationPage = driver.findElement(By.cssSelector("span[class='ant-table-column-title']"));
    assertTrue(productRecomendationPage.isDisplayed());
  }

  @When("I click the search button of product recomendation")
  public void iClickTheSearchButtonOfProductRecomendation() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    WebElement searchButton = driver.findElement(By.cssSelector("button[data-testid='search-button']"));
    searchButton.click();
  }

  @Then("I should see the list of product recomendation")
  public void iShouldSeeTheListOfProductRecomendation() {
    List<WebElement> productRecomendationList = driver.findElements(By.cssSelector("td[class='ant-table-cell']"));
    assertTrue(productRecomendationList.size() > 0);
  }

  @When("I click the filter {string} button with {string} value")
  public void iClickTheFilterButton(String filter, String value) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    switch (filter) {
      case "progress":
        // Find and click the Progress select component's selector
        String xpath = "//*[@id=\"ifocus-content\"]/div[3]/div/form/div/div[6]/div/div/div[2]/div/div/div/div";
        WebElement progressSelect = driver.findElement(By.xpath(xpath));
        progressSelect.click();

        switch (value) {
          case "All":
            String allOptionXpath = "//*[@id=\"zoom\"]/div[2]/div/div/div[2]/div/div/div/div[1]";
            WebElement allOption = driver.findElement(By.xpath(allOptionXpath));
            wait.until(ExpectedConditions.elementToBeClickable(allOption));
            allOption.click();
            break;

          case "Upcoming":
            String upcomingOptionXpath = "//*[@id=\"zoom\"]/div[2]/div/div/div[2]/div/div/div/div[3]";
            WebElement upcomingOption = driver.findElement(By.xpath(upcomingOptionXpath));
            wait.until(ExpectedConditions.elementToBeClickable(upcomingOption));
            upcomingOption.click();
            break;

          case "Finished":
            String finishedOptionXpath = "//*[@id=\"zoom\"]/div[2]/div/div/div[2]/div/div/div/div[4]";
            WebElement finishedOption = driver.findElement(By.xpath(finishedOptionXpath));
            wait.until(ExpectedConditions.elementToBeClickable(finishedOption));
            finishedOption.click();
            break;

          default:
            break;
        }
    }
  }

  @Then("I should see the list of product recomendation with {string} filter of {string} value")
  public void iShouldSeeTheListOfProductRecomendationWithFilterOfValue(String filter, String value) {
    // find the filter value
    switch (filter) {
      case "progress":
        switch (value) {
          case "All":
            WebElement allOption = driver.findElement(By.cssSelector(
                "button[class='ant-btn css-g24l0l ant-btn-circle ant-btn-default ant-btn-icon-only text-ifocus-blue']"));
            assertTrue(allOption.isDisplayed());
            break;

          case "Upcoming":
            WebElement upcomingOption = driver.findElement(By.cssSelector(
                "span[class='ant-badge ant-badge-not-a-wrapper css-g24l0l']"));
            assertTrue(upcomingOption.getText().equals("New Request"));
            break;

          case "Finished":
            WebElement finishedOption = driver.findElement(By.cssSelector(
                "span[class='ant-badge ant-badge-not-a-wrapper css-g24l0l']"));
            assertTrue(finishedOption.getText().equals("Active"));
            break;

          default:
            break;
        }

      default:
        break;
    }
  }

  @When("I click the reset filter button of product recomendation")
  public void iClickTheResetFilterButtonOfProductRecomendation() {
    WebElement resetFilterButton = driver.findElement(By.cssSelector("button[data-testid='reset-button']"));
    resetFilterButton.click();
  }

  @Then("I should see the filter reset")
  public void iShouldSeeTheFilterReset() {
    String xpath = "//*[@id=\"ifocus-content\"]/div[3]/div/form/div/div[6]/div/div/div[2]/div/div/div/div";
    WebElement progressSelect = driver.findElement(By.xpath(xpath));
    assertTrue(progressSelect.getText().equals("Ongoing"));
  }

  @When("I delete a product recomendation")
  public void iDeleteAProductRecomendation() {
    WebElement deleteButton = driver.findElement(By.cssSelector(
        "button[class='ant-btn css-g24l0l ant-btn-circle ant-btn-default ant-btn-icon-only text-red-700']"));
    deleteButton.click();
  }

  @Then("I see the product recomendation deleted")
  public void iSeeTheProductRecomendationDeleted() {
    WebElement swal2Popup = driver
        .findElement(By.cssSelector("div[class='swal2-popup swal2-modal swal2-icon-warning swal2-show']"));
    assertTrue(swal2Popup.isDisplayed());
  }

  @When("I click the add product recomendation button")
  public void iClickTheAddProductRecomendationButton() {
    WebElement addProductRecomendationButton = driver.findElement(By.cssSelector("button[data-testid='add-button']"));
    addProductRecomendationButton.click();
  }

  @When("I click the add new product recomendation button")
  public void iClickTheAddNewButton() {
    WebElement addNewButton = driver.findElement(By.cssSelector("button[data-testid='add-new-button']"));
    addNewButton.click();
  }

  @Then("I should see the create product recomendation page")
  public void iShouldSeeTheCreateProductRecomendationPage() {
    WebElement createProductRecomendationPage = driver.findElement(By.cssSelector("h4[class='row jumbotron-title']"));
    assertTrue(createProductRecomendationPage.getText().equals("Create Product Recommendation"));
  }

  @Then("I should see the error message")
  public void iShouldSeeTheErrorMessage() {
    WebElement errorMessage = driver.findElement(By.cssSelector("div[class='ant-form-item-explain-error']"));
    assertTrue(errorMessage.getText().equals("This field is required"));
  }

  @When("I submit the add new product recomendation form")
  public void iSubmitTheAddNewProductRecomendationForm() {
    WebElement submitButton = driver.findElement(By.cssSelector("button[data-testid='submit-button']"));
    submitButton.click();
  }

  @When("I input valid period value of {string} to {string}")
  public void iInputValidPeriodValue(String startDate, String endDate) {
    WebElement startDateInput = driver.findElement(By.cssSelector("input[placeholder='Start date']"));

    // Wait for the element to be clickable
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(startDateInput));

    startDateInput.sendKeys(startDate);

    WebElement endDateInput = driver.findElement(By.cssSelector("input[placeholder='End date']"));
    endDateInput.sendKeys(endDate);

    endDateInput.sendKeys(Keys.ENTER);
  }

  @When("I input valid specialist value of {string}")
  public void iInputValidSpecialistValue(String specialist) {
    WebElement specialistInput = driver.findElement(By.cssSelector("input[id='SPECIALIST']"));
    specialistInput.click();

    switch (specialist) {
      case "Drg":
        WebElement drgOption = driver
            .findElement(By.cssSelector("div[title='Drg']"));
        drgOption.click();
        // wait 5s
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        break;

      default:
        break;
    }
  }

  @When("I input valid product value")
  public void iInputValidProductValue() {
    WebElement productInput = driver.findElement(By.cssSelector("input[id='PRODUCT']"));
    productInput.click();

    WebElement productOption = driver
        .findElement(By.cssSelector("div[title='TENSIVASK']"));
    productOption.click();

    // wait 5s
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @When("I input valid area")
  public void iInputValidArea() {
    WebElement areaInput = driver.findElement(By.cssSelector("input[id='AREA']"));
    areaInput.click();

    WebElement areaOption = driver.findElement(By.cssSelector("div[label='BOGOR']"));
    areaOption.click();
  }

  @Then("I should see confirmation modal of product recomendation")
  public void iShouldSeeConfirmationModalOfProductRecomendation() {
    WebElement confirmationModal = driver
        .findElement(By.cssSelector("div[class='swal2-popup swal2-modal swal2-icon-warning swal2-show']"));
    // wait until the element is displayed
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOf(confirmationModal));
    assertTrue(confirmationModal.isDisplayed());
  }

  @When("I click the confirm button of confirmation modal of product recomendation")
  public void iClickTheConfirmButtonOfConfirmationModalOfProductRecomendation() {
    WebElement confirmButton = driver.findElement(By.cssSelector(
        "button[class='swal2-confirm swal2-styled swal2-default-outline']"));
    confirmButton.click();
    confirmButton.click();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Then("I should see the success message")
  public void iShouldSeeTheSuccessMessage() {
    WebElement successMessage = driver.findElement(By.cssSelector("div[class='swal2-html-container']"));
    System.out.println(successMessage.getText());
    assertTrue(successMessage.getText().equals("Campaign created successfully"));
  }

  @When("I input a long note")
  public void iInputALongNote() {
    // Locate the Quill editor's contenteditable div
    WebElement noteInput = driver.findElement(By.cssSelector("div.ql-editor"));

    // Wait for the element to be visible
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOf(noteInput));

    // Use JavaScript to set the content of the Quill editor
    String longNote = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa";
    ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = arguments[1];", noteInput, longNote);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Then("I should see the note error message")
  public void iShouldSeeTheNoteErrorMessage() {
    // <div class="mt-1 text-right" style="color: red;">400/400</div>
    WebElement errorMessage = driver.findElement(By.cssSelector("div[class='mt-1 text-right']"));
    assertTrue(errorMessage.getText().equals("400/400"));
  }
}
