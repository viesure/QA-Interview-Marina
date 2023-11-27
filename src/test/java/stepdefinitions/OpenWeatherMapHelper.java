package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public class OpenWeatherMapHelper {

    private final WebDriver driver;
    private final String searchFieldInputName = "q";
    private final String searchMainInputXpath = "//input[@placeholder='Search city']";
    private final String acceptAllButtonCss = "button.stick-footer-panel__link";
    private final String loaderSignCss = ".owm-loader";
    private final String searchButtonCSS = "button.button-round";
    private final String suggestedCityString = "//span[text()='%s ']";
    private final String selectedCityTitleCSS = ".current-container h2";
    private final String currentDateAndTimeOfSelectedCityCss = ".current-container .orange-text";
    private final int WAIT_TIMEOUT_SECONDS = 60;

    public OpenWeatherMapHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToOpenWeatherMap() throws IOException {
        Properties prop = new Properties();
        try {
            FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.get(prop.getProperty("openWeatherMapUrl"));
    }

    public void acceptCookies() {
        WebElement acceptCookiesButton = driver.findElement(By.cssSelector(acceptAllButtonCss));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(loaderSignCss)));
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
        acceptCookiesButton.click();
    }

    public String getSearchFieldPlaceholderText() {
        return driver.findElement(By.name(searchFieldInputName)).getAttribute("placeholder");
    }

    public void searchCity(String city) {
        WebElement searchMainInput = driver.findElement(By.xpath(searchMainInputXpath));
        searchMainInput.sendKeys(city);
        WebElement searchButton = driver.findElement(By.cssSelector(searchButtonCSS));
        searchButton.click();
    }

    public void selectSuggestedCity(String suggestedCity) {
        WebElement selectedCityTitle = driver.findElement(By.cssSelector(selectedCityTitleCSS));
        String originalCityTitle = selectedCityTitle.getText();
        String suggestedCityXpath = String.format(suggestedCityString, suggestedCity);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(suggestedCityXpath)));
        driver.findElement(By.xpath(suggestedCityXpath)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(selectedCityTitle, originalCityTitle)));
    }

    public String getSelectedCityTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchMainInputXpath)));
        WebElement selectedCityTitle = driver.findElement(By.cssSelector(selectedCityTitleCSS));
        return selectedCityTitle.getText();
    }

    public String getCurrentDate(String cityWithCountry) {
        WebElement currentDateAndTimeOfSelectedCity = driver.findElement(By.cssSelector(currentDateAndTimeOfSelectedCityCss));
        return currentDateAndTimeOfSelectedCity.getText().split(",")[0];
    }

    public String getExpectedCurrentDate(String cityWithCountry) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone(cityWithCountry));
        return dateFormat.format(new Date());
    }

    public String getCurrentTime(String cityWithCountry) {
        WebElement currentDateAndTimeOfSelectedCity = driver.findElement(By.cssSelector(currentDateAndTimeOfSelectedCityCss));
        return currentDateAndTimeOfSelectedCity.getText().split(", ")[1];
    }

    public String getExpectedCurrentTime(String cityWithCountry) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mma", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone(cityWithCountry));
        return dateFormat.format(new Date()).toLowerCase();
    }

}