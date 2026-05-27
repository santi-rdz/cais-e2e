import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.Pages.UsersPage;

import Base.BaseTest;

public class UsersTest extends BaseTest{
  UsersPage usersPage;
  @BeforeMethod
  public void navigateToUsers() {
      usersPage = new UsersPage();
      usersPage.open(); // driver.get("http://localhost:5173/usuarios")
  }
  @Test(description = "Crear una invitacion de usuario exitosa")
  public void successfullInvitation(){
    usersPage.sendInvitationTo("prueba@gmail.com");
    Assert.assertEquals(usersPage.getToastTitle(), "1 invitación enviada");
  }
  @Test(description = "Crear una invitacion de usuario existente")
  public void failedInvitation(){
    usersPage.sendInvitationTo("sofia.navarro");
    Assert.assertEquals(usersPage.getToastTitle(), "Uno o más correos ya tienen una cuenta registrada");
  }
  @Test(description = "Crear registro de un usuario de manera exitosa")
  public void createUser(){
    usersPage.createFullUser("test", "test apellidos", "6642865012", "correo.prueba@gmail.com", "11/12/2003", "123", "123456");
    Assert.assertEquals(usersPage.getToastTitle(), "Usuario creado exitosamente");
  }
  @Test(description = "Desactivar exitosamente el acceso de un usuario al sistema")
  public void deactivateUser(){
    usersPage.toggleUserStatus("carlos.herrera@cais.com");
    Assert.assertEquals(usersPage.getToastTitle(), "Usuario desactivado");
  }
  // UI
  @Test(description = "Se muestra correctamente el titulo de pagina /usuarios")
  public void titleIsDisplayed(){
    Assert.assertTrue(usersPage.isTitleDisplayed());
  }
}
