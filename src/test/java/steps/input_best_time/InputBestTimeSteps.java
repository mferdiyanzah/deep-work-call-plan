package steps.input_best_time;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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

public class InputBestTimeSteps {
  private WebDriver driver;
  private final TestContext testContext;
  private String totalDocters;
  private String totalDoctorsOnSelectedDay = "0";
  private String selectedDoctor;
  private Boolean isSwitchActive = false;
  private String longLeaveNote = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum";

  public InputBestTimeSteps() {
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

  @Given("I am on the input best time page")
  public void iAmOnTheInputBestTimePage() {
    driver.get("https://dev.ifocusng.com/call-management/masterlist");
  }

  @Then("I should see the input best time page")
  public void iShouldSeeTheInputBestTimePage() {
    WebElement inputBestTimeTitle = driver.findElement(By.cssSelector("h4.row.jumbotron-title"));

    WebElement badgeCount = driver
        .findElement(By.cssSelector("sup.ant-scroll-number.ant-badge-count.ant-badge-multiple-words"));
    totalDocters = badgeCount.getText();
    System.out.println(totalDocters);

    assertTrue(inputBestTimeTitle.isDisplayed());
  }

  @When("I click the {string} day")
  public void iClickTheDay(String day) {
    WebElement dayElement = driver.findElement(By.cssSelector("div[data-testid='heatmap-cell-" + day + "-day']"));

    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.invisibilityOfElementLocated(By.className("ant-spin-fullscreen")));

    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dayElement);

    dayElement.click();
    totalDoctorsOnSelectedDay = Integer
        .toString(Integer.parseInt(totalDoctorsOnSelectedDay) + Integer.parseInt(dayElement.getText()));
    System.out.println("Total doctors on selected day: " + totalDoctorsOnSelectedDay);
  }

  @Then("I should see the total doctors on the {string} day")
  public void iShouldSeeTheTotalDoctorsOnTheDay(String day) {
    WebElement badgeCount = driver
        .findElement(By.cssSelector("sup.ant-scroll-number.ant-badge-count.ant-badge-multiple-words"));
    String titleValue = badgeCount.getAttribute("title");
    System.out
        .println("Title value: " + titleValue + " and total doctors on selected day: " + totalDoctorsOnSelectedDay);
    assertTrue(titleValue.equals(totalDoctorsOnSelectedDay));
  }

  @When("I select the doctor from the list")
  public void iSelectTheDoctorFromTheList() {
    // list of all doctors using the data-testid attribute
    List<WebElement> doctorList = driver.findElements(By.cssSelector("div[data-testid^='doctor-row-']"));

    // Check if the doctor list is not empty before selecting
    if (!doctorList.isEmpty()) {
      selectedDoctor = doctorList.get(0).getAttribute("data-testid").split("-")[2];
      doctorList.get(0).click();
    } else {
      System.out.println("No doctors available to select.");
    }
  }

  @Then("I should see the selected doctor")
  public void iShouldSeeTheSelectedDoctor() {
    WebElement doctorCode = driver.findElement(By.cssSelector("span[data-testid='person-code']"));

    // count data-testid="location-card-1"
    List<WebElement> locationCards = driver.findElements(By.cssSelector("div[data-testid^='location-card']"));
    if (!locationCards.isEmpty()) {
      assertTrue(doctorCode.getText().equals(selectedDoctor));
    } else {
      System.out.println("No location cards available to check.");
    }
  }

  @When("I submit the visit time")
  public void iSubmitTheVisitTime() {
    WebElement submitButton = driver.findElement(By.cssSelector("button[data-testid='submit-button']"));

    // Scroll the button into view first
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

    // Wait for it to be clickable
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(submitButton));

    // Try JavaScript click if regular click fails
    try {
      submitButton.click();
    } catch (Exception e) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
    }
  }

  @Then("I should see the error message when submitting visit time of multiple location doctor with same time")
  public void iShouldSeeTheErrorMessageWhenSubmittingVisitTimeOfMultipleLocationDoctorWithSameTime() {
    WebElement errorMessage = driver.findElement(By.cssSelector("div[aria-labelledby='swal2-title']"));
    assertTrue(errorMessage.isDisplayed());
  }

  @When("I select {string} shift for {string} at {string}")
  public void iSelectShiftForDayAtLocation(String shift, String day, String location) {
    WebElement shiftButton = driver
        .findElement(By.cssSelector("button#dynamic_form_complex_items_0_" + day.toLowerCase() + shift));
    isSwitchActive = Boolean.parseBoolean(shiftButton.getAttribute("aria-checked"));
    System.out.println("Is switch active: " + isSwitchActive);

    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(shiftButton));
    shiftButton.click();
  }

  @Then("{string} shift for {string} at {string} should be changed")
  public void shiftForDayAtLocationShouldBeChanged(String shift, String day, String location) {
    WebElement shiftButton = driver
        .findElement(By.cssSelector("button#dynamic_form_complex_items_0_" + day.toLowerCase() + shift));
    boolean currentState = Boolean.parseBoolean(shiftButton.getAttribute("aria-checked"));
    System.out.println("Current state: " + currentState + " and isSwitchActive: " + isSwitchActive);
    assertNotEquals(currentState, isSwitchActive);
  }

  @When("I switch all shift to off")
  public void iSwitchAllShiftToOff() {
    List<WebElement> shiftButtons = driver.findElements(By.cssSelector("button[role='switch']"));
    for (WebElement shiftButton : shiftButtons) {
      // if the shift button is active, click it
      if (Boolean.parseBoolean(shiftButton.getAttribute("aria-checked"))) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(shiftButton));
        shiftButton.click();
      }
    }
  }

  @Then("I should see confirmation modal")
  public void iShouldSeeConfirmationModal() {
    WebElement confirmationModal = driver.findElement(By.cssSelector("div[aria-labelledby='swal2-title']"));
    assertTrue(confirmationModal.isDisplayed());
  }

  @When("I click the Confirm button")
  public void iClickTheConfirmButton() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Wait for and click confirm button
    WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
        By.cssSelector("button.swal2-confirm.swal2-styled.swal2-default-outline")));
    confirmButton.click();

    // Add a small delay to ensure message is fully loaded
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Then("I can see success modal of {string}")
  public void iCanSeeSuccessModal(String message) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector("div[id='swal2-html-container']")));

    // Add a small delay to ensure message is fully loaded
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    // Re-fetch the element to avoid stale reference
    successMessage = driver.findElement(By.cssSelector("div[id='swal2-html-container']"));
    assertTrue(successMessage.getText().equals(message));
  }

  @When("I switch the tab to leave")
  public void iSwitchTheTabToLeave() {
    WebElement leaveTab = driver.findElement(By.cssSelector("div[data-node-key='3']"));
    leaveTab.click();
  }

  @When("I switch the tab to best time")
  public void iSwitchTheTabToBestTime() {
    WebElement bestTimeTab = driver.findElement(By.cssSelector("div[data-node-key='2']"));
    bestTimeTab.click();
  }

  @Then("I should see the best time tab")
  public void iShouldSeeTheBestTimeTab() {
    WebElement bestTimeTab = driver.findElement(By.cssSelector("h3.ant-typography.m-0.text-left.css-g24l0l"));
    assertTrue(bestTimeTab.isDisplayed());
  }

  @Then("I should see the leave tab")
  public void iShouldSeeTheLeaveTab() {
    WebElement leaveTab = driver.findElement(By.cssSelector("label[for='NOTES']"));
    assertTrue(leaveTab.isDisplayed());
  }

  @When("I input the leave date of {string} to {string} and {string}")
  public void iInputTheStartOfLeaveDate(String startDate, String endDate, String enter) {
    WebElement startDateInput = driver.findElement(By.cssSelector("input[placeholder='Start date']"));

    // Wait for the element to be clickable
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(startDateInput));

    startDateInput.sendKeys(startDate);

    WebElement endDateInput = driver.findElement(By.cssSelector("input[placeholder='End date']"));
    endDateInput.sendKeys(endDate);

    if (enter.equals("enter")) {
      endDateInput.sendKeys(Keys.ENTER);
    }
  }

  @When("I input invalid date on leave tab")
  public void iInputInvalidDateOnLeaveTab() {
    WebElement startDateInput = driver.findElement(By.cssSelector("input[placeholder='Start date']"));
    startDateInput.sendKeys("2024-11-31");
    startDateInput.sendKeys(Keys.ENTER);
  }

  @Then("I can see error message of {string}")
  public void iCanSeeErrorMessage(String message) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector("div[id='swal2-html-container']")));

    // Add a small delay to ensure message is fully loaded
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    System.out.println("Error message: " + errorMessage.getText());
    // Re-fetch the element to avoid stale reference
    errorMessage = driver.findElement(By.cssSelector("div[id='swal2-html-container']"));
    assertTrue(errorMessage.getText().equals(message));
  }

  @When("I click the close of history of doctor leave")
  public void iClickTheCloseOfHistoryOfDoctorLeave() {
    // wait for the close button to be clickable
    WebElement closeButton = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Close']")));
    closeButton.click();
  }

  @Then("I can not see the history of doctor leave")
  public void iCanNotSeeTheHistoryOfDoctorLeave() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(
        By.cssSelector("div[class='ant-drawer-title']")));
    assertTrue(isInvisible);
  }

  @When("I input the leave note of {string}")
  public void iInputTheLeaveNoteOf(String note) {
    WebElement notesInput = driver.findElement(By.cssSelector("textarea[data-testid='notes-input']"));
    notesInput.sendKeys(note);
  }

  @When("I input a long leave note")
  public void iInputALongLeaveNote() {
    WebElement notesInput = driver.findElement(By.cssSelector("textarea[data-testid='notes-input']"));

    // Split the long note into smaller chunks (e.g., 500 characters each)
    int chunkSize = 100;
    for (int i = 0; i < longLeaveNote.length(); i += chunkSize) {
      String chunk = longLeaveNote.substring(i, Math.min(longLeaveNote.length(), i + chunkSize));
      notesInput.sendKeys(chunk);

      // Small pause between chunks to ensure proper input
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    // Wait for final input to be complete
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(driver -> notesInput.getAttribute("value").length() == longLeaveNote.length());

    notesInput.sendKeys(Keys.TAB);
  }

  @When("I submit the leave")
  public void iSubmitTheLeave() {
    // Wait for any loading spinners to disappear
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.invisibilityOfElementLocated(By.className("ant-spin-fullscreen")));

    // Find the button using XPath
    WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id='rc-tabs-1-panel-3']/div/form/div[4]/div/button")));

    // Scroll to the button
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submitButton);

    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    // Click using JavaScript
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
  }

  @When("I click the {string} filter button")
  public void iClickTheFilterButton(String filter) {
    WebElement filterButton = driver.findElement(By.cssSelector("button[data-testid='" + filter + "-filter-button']"));
    filterButton.click();
  }

  @Then("I can see the {string} filter active")
  public void iCanSeeTheFilterActive(String filter) {
    WebElement filterButton = driver.findElement(By.cssSelector("button[data-testid='" + filter + "-filter-button']"));
    assertTrue(filterButton.getAttribute("class").contains("bg-blue-400"));
  }

  // <input data-testid="doctor-search-input" type="text" placeholder="Search..."
  // class="ant-input css-g24l0l ant-input-outlined mt-1 h-1/6" value="">
  @When("I input the doctor name {string}")
  public void iInputTheDoctorName(String name) {
    WebElement searchInput = driver.findElement(By.cssSelector("input[data-testid='doctor-search-input']"));
    searchInput.sendKeys(name);
  }

  @Then("I can see the doctor {string} in the list")
  public void iCanSeeTheDoctorInTheList(String name) {
    List<WebElement> doctorNames = driver.findElements(By.cssSelector("span[data-testid^='doctor-name-']"));
    for (WebElement doctorName : doctorNames) {
      if (doctorName.getText().contains(name)) {
        assertTrue(true);
        return;
      }
    }
    assertTrue(false);
  }
}
