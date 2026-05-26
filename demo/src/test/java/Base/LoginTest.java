package Base;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Pages.LoginPage;
import com.utils.DriverManager;

public class LoginTest {
  private LoginPage loginPage;

  @BeforeMethod
  public void setup(){
    loginPage = new LoginPage();
    loginPage.open();
  }

  @AfterMethod
  public void quit(){
    DriverManager.quitDriver();
  }

  @Test(description = "login with valid credentials")
  public void loginSuccessfully(){
    loginPage.loginAs("sofia.navarro", "123");
    loginPage.waitUrlContains("/dashboard");
    Assert.assertTrue(loginPage.getCurrentUrl().contains("/dashboard"));
  }
  
  @Test(description = "login with invalid credentials")
  public void loginFail(){
    loginPage.loginAs("sofia.navarro", "invalidpass");
    Assert.assertEquals(loginPage.getToastDescription(), "Verifica tus credenciales e inténtalo de nuevo.");
  }

}
