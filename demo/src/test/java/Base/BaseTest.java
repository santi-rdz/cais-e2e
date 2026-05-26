package Base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.Pages.LoginPage;
import com.utils.DriverManager;

public class BaseTest {

  @BeforeMethod
  public void setup(){
    LoginPage loginPage = new LoginPage();
    loginPage.open();
    loginPage.loginAs("sofia.navarro", "123");
    loginPage.waitUrlContains("/dashboard");
  }
  
  @AfterMethod
  public void quit(){
    DriverManager.quitDriver();
  }
}
