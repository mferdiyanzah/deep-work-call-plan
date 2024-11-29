package steps.call_recomendation;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class CallRecomendationSteps {
  private WebDriver driver;
  private final TestContext testContext;
  private String selectedDoctor;

  public CallRecomendationSteps() {
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

  @Given("I am on the call recomendation page")
  public void iAmOnTheCallRecomendationPage() {
    driver.get("https://dev.ifocusng.com/call-management/recommendation");
  }

  @When("I input the {string} week of {string} year")
  public void iInputTheWeek(String week, String year) {
    // Wait for the element to be clickable
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    WebElement dateInput = wait.until(
        ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='period-picker']")));

    // Create the week string in the format "2024-47th" (for example)
    String weekString = String.format("%s-%sth", year, week);

    // Use JavaScript to set the attribute value
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript(
        "arguments[0].setAttribute('value', arguments[1]); " +
        // Also dispatch events to ensure React picks up the change
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true })); " +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
        dateInput,
        weekString);
  }

  @Then("I should see the {string} week of {string} year")
  public void iShouldSeeTheWeek(String week, String year) {
    WebElement datePicker = driver.findElement(By.cssSelector("[data-testid='period-picker']"));
    assertTrue(datePicker.getAttribute("value").equals(year + "W" + week + "th"));
  }

  @Then("I should see the call recomendation page")
  public void iShouldSeeTheCallRecomendationPage() {
    WebElement title = driver.findElement(By.cssSelector("h4.row.jumbotron-title"));
    assertTrue(title.isDisplayed());
    assertTrue(title.getText().equals("Call Recommendation"));
  }

  @Then("I should see datepicker")
  public void iShouldSeeDatepicker() {
    WebElement datePicker = driver.findElement(By.cssSelector("[data-testid='period-picker']"));
    assertTrue(datePicker.isDisplayed());

    // Wait for and verify the date picker dropdown is visible
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement datePickerDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.className("ant-picker-dropdown")));
    assertTrue(datePickerDropdown.isDisplayed());
  }

  @When("I click the simulate call button")
  public void iClickTheSimulateCallButton() {
    WebElement simulateCallButton = driver.findElement(By.cssSelector("[data-testid='generate-submit-button']"));
    simulateCallButton.click();
    simulateCallButton.click();
  }

  @Then("I should see the docter card")
  public void iShouldSeeDocterCard() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    // First wait for any loading spinner to disappear (if it exists)
    try {
      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ant-spin-spinning")));
    } catch (Exception e) {
      System.out.println("No loading spinner found or already disappeared");
    }

    // Check if elements exist in DOM, even if not visible
    List<WebElement> cards = driver.findElements(By.cssSelector("[data-testid='draggable-item-card']"));
    System.out.println("Found " + cards.size() + " cards in DOM (might not be visible)");

    try {
      // Try to find visible cards
      List<WebElement> visibleCards = wait.until(
          ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[data-testid='draggable-item-card']")));
      assertTrue("No visible doctor cards were found", visibleCards.size() > 0);
    } catch (Exception e) {
      // If cards aren't visible, let's print the page source to help debug
      System.out.println("Page source: " + driver.getPageSource());
      throw e;
    }
  }

  @When("I click the reset button")
  public void iClickResetButton() {
    WebElement resetButton = driver.findElement(By.cssSelector("[data-testid='reset-button']"));
    resetButton.click();
  }

  @Then("I should see the empty datepicker and doctor card")
  public void iShouldSeeTheEmptyDatepickerAndDoctorCard() {
    WebElement datePicker = driver.findElement(By.cssSelector("[data-testid='period-picker']"));
    assertTrue(datePicker.getAttribute("value").equals(""));

    List<WebElement> cards = driver.findElements(By.cssSelector("[data-testid='draggable-item-card']"));
    assertTrue(cards.size() == 0);
  }

  @Then("I should see the input error message")
  public void iShouldSeeTheErrorMessage() {
    WebElement errorMessage = driver.findElement(By.cssSelector("div[class='ant-form-item-explain-error']"));
    assertTrue(errorMessage.isDisplayed());
  }

  @When("I input invalid date")
  public void iInputInvalidDate() {
    iInputTheWeek("90", "yeear");
    iClickTheSimulateCallButton();
  }

  @Then("I should see the warning message")
  public void iShouldSeeTheWarningMessage() {
    WebElement warningMessage = driver.findElement(By.cssSelector("div[class='swal2-html-container']"));
    assertTrue(warningMessage.isDisplayed());
  }

  @When("I click the doctor card")
  public void iClickTheDoctorCard() {
    // <span data-testid="customer-name" class="inline items-center break-all
    // text-start text-xs font-semibold capitalize sm:text-sm md:text-base
    // lg:text-lg">ELY******* </span>
    List<WebElement> doctorCards = driver.findElements(By.cssSelector("[data-testid='customer-name']"));

    WebElement doctorCard = doctorCards.get(0);
    selectedDoctor = doctorCard.getText();
    doctorCard.click();
  }

  @Then("I should see the tooltip of the doctor card")
  public void iShouldSeeTheTooltipOfTheDoctorCard() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Wait for and verify the popover is displayed
    WebElement popover = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='popover-detail']")));
    assertTrue(popover.isDisplayed());

    // Verify the doctor name matches the selected doctor
    WebElement popoverName = popover.findElement(By.cssSelector("[data-testid='popover-name']"));
    assertTrue(popoverName.getText().equals(selectedDoctor));

    // Verify other essential elements are present
    List<String> requiredElements = List.of(
        "popover-specialist",
        "popover-person-code",
        "popover-class-status",
        "popover-customer-name");

    for (String element : requiredElements) {
      WebElement popoverElement = popover.findElement(By.cssSelector("[data-testid='" + element + "']"));
      assertTrue(popoverElement.isDisplayed());
    }
  }

}
