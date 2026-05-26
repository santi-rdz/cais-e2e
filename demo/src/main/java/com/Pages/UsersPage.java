package com.Pages;

import org.openqa.selenium.By;

import com.Base.BasePage;

public class UsersPage extends BasePage{
  // locators
  By usersSearch = By.cssSelector("[data-testid='users-search']");
  By addUserBtn = By.cssSelector("[data-testid='add-user-btn']");
  By inviteEmailInput = By.id("invite-email");
  By addToListBtn = By.cssSelector("[data-testid='add-to-list-btn']");
  By tabInvitations = By.cssSelector("[data-testid='tab-invitations']");
  By tabFull = By.cssSelector("[data-testid='tab-full']");
  By tabPasante = By.cssSelector("[data-testid='tab-pasante']");
  By tabCoordinador = By.cssSelector("[data-testid='tab-coordinador']");
  By nombreInput = By.id("nombre");
  By apellidosInput = By.id("apellidos");
  By telefonoInput = By.id("telefono");
  By correoInput = By.id("correo");
  By fechaNacInput = By.cssSelector("[data-testid='datepicker-fecha_nacimiento']");
  By matriculaInput = By.id("matricula");
  By selectInicioAnio = By.cssSelector("[data-testid='select-inicio-anio']");
  By selectInicioPeriodo = By.cssSelector("[data-testid='select-inicio-periodo']");
  By selectFinAnio = By.cssSelector("[data-testid='select-fin-anio']");
  By selectFinPeriodo = By.cssSelector("[data-testid='select-fin-periodo']");
  By passwordInput = By.id("password");
  By modalPrimaryBtn = By.cssSelector("[data-testid='modal-primary-btn']");
  By toggleEstadoBtn = By.cssSelector("[data-testid='toggle-estado-btn']");
  By confirmActionBtn = By.cssSelector("[data-testid='confirm-action-btn']");
  By pageTitle = By.cssSelector("[data-testid='page-title-users']");
  // actions
  public void open(){
    driver.get("http://localhost:5173/usuarios");
  }
  public void sendInvitationTo(String email){
    click(addUserBtn);
    type(inviteEmailInput, email);
    click(addToListBtn);
    click(modalPrimaryBtn);
  }
  public void createFullUser(String nombre, String apellidos, String telefono, String correo, String fechaNacimiento,String matricula, String password) {
    click(addUserBtn);
    click(tabFull);
    click(tabPasante);
    type(nombreInput, nombre);
    type(apellidosInput, apellidos);
    type(telefonoInput, telefono);
    type(correoInput, correo);
    type(fechaNacInput, fechaNacimiento);
    type(matriculaInput, matricula);
    hover(selectInicioAnio);
    selectFrom(selectInicioAnio, "2026");
    selectFrom(selectInicioPeriodo, "Enero - Junio");
    selectFrom(selectFinAnio, "2026");
    selectFrom(selectFinPeriodo, "Julio - Diciembre");
    click(modalPrimaryBtn); // Siguiente
    type(passwordInput, password);
    click(modalPrimaryBtn); // Crear usuario
  }
  public void toggleUserStatus(String userEmail) {
    By rowMenu = By.cssSelector("[data-testid='row-menu-" + userEmail + "']");
    click(rowMenu);
    click(toggleEstadoBtn);
    click(confirmActionBtn);
  }
  public boolean isTitleDisplayed(){
    return waitFor(pageTitle).isDisplayed();
  }

}
