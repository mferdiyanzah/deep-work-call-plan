package steps.call_overview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CallOverviewSteps {
  private WebDriver driver;
  private final TestContext testContext;
  private List<String> sortedDoctorNames;
  private List<String> originalDoctorNames;

  // class type
  private List<String> originalClassTypes;
  private List<String> sortedClassTypes;

  public CallOverviewSteps() {
    testContext = new TestContext();
    sortedDoctorNames = new ArrayList<>();
    originalDoctorNames = new ArrayList<>();

    originalClassTypes = new ArrayList<>();
    sortedClassTypes = new ArrayList<>();
  }

  @Before
  public void setup() throws Exception {
    driver = testContext.getDriver();
  }

  @After
  public void tearDown() {
    testContext.quitDriver();
  }

  @Given("I am on the call overview page")
  public void iAmOnTheCallOverviewPage() {
    driver.get("https://dev.ifocusng.com/call-management/overview");
  }

  @When("I click the search button")
  public void iClickTheSearchButton() {
    WebElement searchButton = driver.findElement(By.cssSelector("button[data-testid='search-button']"));
    searchButton.click();
    searchButton.click();
  }

  @Then("I should see the search result")
  public void iShouldSeeTheSearchResult() {
    WebElement searchResult = driver.findElement(By.cssSelector("div[class='ant-form-item-explain-error']"));
    assertTrue(searchResult.isDisplayed());
  }

  @When("I input the month {string} of {string} year")
  public void iInputTheMonthOfYear(String month, String year) {
    // Wait for the element to be clickable
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    WebElement monthInput = wait.until(
        ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='month-picker']")));

    // Create the month string in the format "2024-02"
    String monthString = String.format("%s-%02d", year, Integer.parseInt(month));

    // Use JavaScript to set the attribute value
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript(
        "arguments[0].setAttribute('value', arguments[1]); " +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true })); " +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
        monthInput,
        monthString);

    // wait 1 second
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Then("I should see the result of overview")
  public void iShouldSeeTheResultOfOverview() {
    WebElement resultOfOverview = driver.findElement(By.cssSelector("li[title='1']"));
    assertTrue(resultOfOverview.isDisplayed());

    // Find all first td elements in each row
    List<WebElement> doctorNames = driver.findElements(By.cssSelector("tr.ant-table-row td:first-child"));
    for (WebElement doctorName : doctorNames) {
      originalDoctorNames.add(doctorName.getText());
    }
  }

  @When("I switch to weekly tab")
  public void iSwitchToWeeklyTab() {
    WebElement weeklyTab = driver.findElement(By.cssSelector("div[data-node-key='2']"));
    weeklyTab.click();
  }

  @Then("I should see the weekly tab")
  public void iShouldSeeTheWeeklyTab() {
    WebElement weeklyTab = driver.findElement(By.cssSelector("input[data-testid='week-picker']"));
    assertTrue(weeklyTab.isDisplayed());
  }

  @When("I input the {string} week of {string} year of overview")
  public void iInputTheWeek(String week, String year) {
    // Wait for the element to be clickable
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    WebElement dateInput = wait.until(
        ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='week-picker']")));

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

  @When("I click the history button")
  public void iClickTheHistoryButton() {
    WebElement historyButton = driver.findElement(By.cssSelector("button[data-testid='history-button']"));
    historyButton.click();
  }

  @Then("I can see the history of doctor leave")
  public void iCanSeeTheHistoryOfDoctorLeave() {
    // wait for the element to be visible
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement historyOfDoctorLeave = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='ant-drawer-title']")));
    assertTrue(historyOfDoctorLeave.isDisplayed());
  }

  @When("I click the sort by {string} button {string}")
  public void iClickTheSortByNameButton(String sortType, String sortValue) {
    // Wait for the element to be clickable
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement sortByNameButton = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.xpath("//th[.//span[text()='" + sortType + "']]")));
    sortedDoctorNames = new ArrayList<>(originalDoctorNames);
    if (sortValue.equals("ascending")) {
      switch (sortType) {
        case "Person Name":
          Collections.sort(sortedDoctorNames);
          break;
        case "Class":
          Collections.sort(sortedClassTypes);
          break;
      }
      sortByNameButton.click();
    } else {
      switch (sortType) {
        case "Person Name":
          Collections.reverse(sortedDoctorNames);
          break;
        case "Class":
          Collections.reverse(sortedClassTypes);
          break;
      }
      sortByNameButton.click();
      sortByNameButton.click();
    }

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Then("I can see the sorted {string} by {string}")
  public void iCanSeeTheSortedDoctorNames(String sortType, String sortValue) {
    if (sortValue.equals("ascending")) {
      switch (sortType) {
        case "Person Name":
          assertEquals(sortedDoctorNames, originalDoctorNames);
          break;
      }
    } else {
      switch (sortType) {
        case "Person Name":
          Collections.reverse(sortedDoctorNames);
          assertEquals(sortedDoctorNames, originalDoctorNames);
          break;
      }
    }
  }

  @When("I can filter the doctor by {string} with value {string}")
  public void iCanFilterTheDoctorBy(String filterType, String filterValue) {
    // Wait for the element to be clickable
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement filterButton = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.xpath(
                "//th[contains(@aria-label, 'Person Name')]//span[@role='button' and contains(@class, 'ant-dropdown-trigger')]")));
    filterButton.click();

    // Wait for filter input to be visible and enter the filter value
    WebElement filterInput = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.cssSelector("input.ant-input")));
    filterInput.sendKeys(filterValue);

    // Click the filter search button
    WebElement searchButton = driver.findElement(
        By.xpath("//button[contains(@class, 'ant-btn-primary') and .//span[text()='Search']]"));
    searchButton.click();

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Then("I can see the filtered doctor names {string}")
  public void iCanSeeTheFilteredDoctorNames(String filterValue) {
    // find the doctor name in the table
    WebElement doctorName = driver.findElement(By.xpath("//td[contains(text(), '" + filterValue + "')]"));
    assertTrue(doctorName.isDisplayed());
  }

  @When("I reset the filter by {string}")
  public void iResetTheFilterBy(String filterType) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement filterButton = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.xpath(
                "//th[contains(@aria-label, 'Person Name')]//span[@role='button' and contains(@class, 'ant-dropdown-trigger')]")));
    filterButton.click();

    WebElement resetButton = driver
        .findElement(By.xpath("//button[contains(@class, 'ant-btn-default') and .//span[text()='Reset']]"));
    resetButton.click();
  }

  @Then("I can see the input filter of {string} is empty")
  public void iCanSeeTheInputFilterOfIsEmpty(String filterType) {
    WebElement filterInput = driver.findElement(By.cssSelector("input.ant-input"));
    assertEquals("", filterInput.getAttribute("value"));
  }
}
