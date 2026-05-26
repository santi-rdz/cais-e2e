package com.Pages;

import org.openqa.selenium.By;

import com.Base.BasePage;

public class EmergenciesPage extends BasePage {
    // locators
    //Emergencia creada exitosamente -> Toast
    //Emergencia del ... eliminada -> Toast

    By addEmergencyBtn = By.xpath("//*[@id=\"root\"]/div[2]/main/div/div[1]/button");
    By ubicationInput = By.cssSelector("input[name=\"ubicacion\"]");
    By nameInput = By.cssSelector("input[name=\"nombre\"]");
    By matriculaInput = By.cssSelector("input[name=\"matricula\"]");
    By cellphoneInput = By.id("telefono");
    By diagnosticText = By.id("diagnostico");
    By actionText = By.id("accion_realizada");
    By registerEmergencyBtn = By.cssSelector("button[data-testid=\"modal-primary-btn\"]");

    By moreBtn = By.xpath("//*[@id=\"root\"]/div[2]/main/div/div[2]/div/div[2]/div[1]/div[6]/button");
    By viewBtn = By.cssSelector("button[data-testid=\"emergency-view-btn\"]");
    By eraseBtn = By.cssSelector("button[data-testid=\"emergency-delete-btn\"]");
    By confirmationBtn = By.cssSelector("button[data-testid=\"confirm-action-btn\"");
    By dateText = By.xpath("//*[@id=\"root\"]/div[2]/main/div/div[2]/div/div[2]/div[1]/div[1]");

    // actions
    public void open(){
        driver.get("http://localhost:5173/emergencias");
    }
    public void addEmergency(String ubicacion, String nombre, String matricula, String telefono, String diagnostico, String accion){
        click(addEmergencyBtn);
        type(ubicationInput, ubicacion);
        type(nameInput, nombre);
        type(matriculaInput, matricula);
        type(cellphoneInput, telefono);
        type(diagnosticText, diagnostico);
        type(actionText, accion);
        click(registerEmergencyBtn);
    }

    public void viewEmergency() {
        click(moreBtn);
        click(viewBtn);
    }
    public void eraseEmergency(){
        click(moreBtn);
        click(eraseBtn);
        click(confirmationBtn);
    }
    public String getDate(){
      return getText(dateText,"");
    }
}
