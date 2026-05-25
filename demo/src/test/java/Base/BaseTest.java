package Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.Pages.LoginPage;
import com.utils.DriverManager;

public class BaseTest {
  protected WebDriver driver;
  @BeforeMethod
  public void setup(){
    driver = DriverManager.getDriver();
    driver.get("http://localhost:5173/login");
    LoginPage loginPage = new LoginPage();
    loginPage.loginAs("sofia.navarro", "123");
    loginPage.waitUrlContains("/dashboard");
  }
  @AfterMethod
  void quit(){
    DriverManager.quitDriver();
  }
}
