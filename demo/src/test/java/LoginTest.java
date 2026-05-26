import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Pages.LoginPage;
import com.utils.DriverManager;

public class LoginTest {
  private LoginPage loginPage;
  private WebDriver driver;

  @BeforeMethod
  public void setup(){
    loginPage = new LoginPage();
    loginPage.open();
    driver = DriverManager.getDriver();
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
  @Test(description = "login with invalid password")
  public void loginInvalidPassword(){
    loginPage.loginAs("sofia.navarro", "invalidpass");
    Assert.assertEquals(loginPage.getToastTitle(), "Contraseña inválida");
  }
  @Test(description = "login with not registered email")
  public void loginNoEmailFound(){
    loginPage.loginAs("noemail", "123");
    Assert.assertEquals(loginPage.getToastTitle(), "Correo electronico no encontrado");
  }
  @Test(description = "Hacer logout y validar que permanece en /login al intetnar entrar a rutas protegidas")
  public void logout(){
    loginPage.loginAs("sofia.navarro", "123");
    loginPage.waitUrlContains("/dashboard");
    loginPage.logout();
    loginPage.waitUrlContains("/login");
    Assert.assertTrue(loginPage.getCurrentUrl().contains("/login"));
    driver.get("http://localhost:5173/pacientes");
    loginPage.waitUrlContains("/login");
    Assert.assertTrue(loginPage.getCurrentUrl().contains("/login"));
  }
  // ui
  @Test(description = "Se muestra el titulo de login correctamente")
  public void titleIsDisplayed(){
    Assert.assertTrue(loginPage.isTitleDisplayed());
  }

}
