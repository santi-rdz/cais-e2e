package com.Base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.DriverManager;

public abstract class BasePage {

    private By toastTitle = By.cssSelector("[data-sonner-toast] [data-title]");
    private By toastDescription = By.cssSelector("[data-sonner-toast] [data-description]");

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public WebElement waitFor(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUrlContains(String fragment) {
        wait.until(ExpectedConditions.urlContains(fragment));
    }

    public void click(By locator) {
        waitFor(locator).click();
    }

    public void type(By locator, String text) {
        waitFor(locator).sendKeys(text);
    }

    public void hover(By locator) {
        WebElement element = waitFor(locator);
        new Actions(driver).moveToElement(element).perform();
    }

    public String getText(By locator) {
        return waitFor(locator).getText();
    }

    public void clear(By locator) {
        waitFor(locator).clear();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isDiplayed(By locator) {
        return waitFor(locator).isDisplayed();
    }

    public boolean areAllDisplayed(List<WebElement> elements) {
        return elements.stream().allMatch(WebElement::isDisplayed);
    }

    public String getToastTitle() {
        return waitFor(toastTitle).getText();
    }

  public void clearTextField(By locator){
    waitFor(locator).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
  }

  public String getToastDescription() {
    return waitFor(toastDescription).getText();
  }
  public void selectFrom(By select, String text) {
    hover(select);
    String testId = driver.findElement(select).getAttribute("data-testid");
    By option = By.xpath("//button[@data-testid='" + testId + "']/following-sibling::div//button[.//span[text()='" + text + "']]");
    waitFor(option).click();
  }
}
