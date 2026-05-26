package com.Pages;

import org.openqa.selenium.By;

import com.Base.BasePage;

public class EditPage extends BasePage {
    // locators

    By patientNameInfo = By.xpath("/html/body/div/div[2]/main/div/div/div[2]/div[1]/div[2]/div[1]/h2");
    By editPatientButton = By.cssSelector("button[data-testid='edit-patient-btn']");
    By modalTitle = By.xpath("/html/body/div[3]/div/div[2]/div/header/h2/span");
    By telephoneNumberField = By.id("telefono");
    By emailField = By.id("correo");
    By updatePatientButton = By.cssSelector("button[data-testid='modal-primary-btn']");
    By patientTelephoneNumberInfo = By.xpath("/html/body/div/div[2]/main/div/div/div[2]/div[1]/div[2]/div[2]/span[2]/span[2]");

    // actions
    public String getModalTitle() {
        return getText(modalTitle);
    }

    public String getPatientNameInfo() {
        return waitFor(patientNameInfo).getText();
    }

    public void editPatient() {
        waitFor(editPatientButton);
        click(editPatientButton);
    }

    public void enterTelephoneNumber(String n) {
        type(telephoneNumberField, n);
    }

    public void enterEmail(String n) {
        clear(emailField);
        type(emailField, n);
    }

    public void editInfo(String correctTelephoneNumber, String email) {
        enterTelephoneNumber(correctTelephoneNumber);
        enterEmail(email);
        click(updatePatientButton);
    }

    public String getInfoTelephoneNumber() {
        return getText(patientTelephoneNumberInfo);
    }
}
