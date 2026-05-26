package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Base.BasePage;

public class PacientesPage extends BasePage {
    // locators

    By pacientesNavLink = By.cssSelector("a[href='/pacientes']");
    By nuevoPacienteButton = By.xpath("/html/body/div/div[2]/header/div[4]/button");

    By modalTitle = By.xpath("/html/body/div[3]/div/div[2]/div/header/h2/span");

    By nameField = By.id("nombre");
    By lastNameField = By.id("apellidos");
    By birthdayField = By.cssSelector("input[data-testid='datepicker-fecha_nacimiento']");
    By telephoneNumberField = By.id("telefono");
    By genderDropDown = By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/form/div/div[1]/div[2]/div[3]/div/button");
    By otherGenderOption = By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/form/div/div[1]/div[2]/div[3]/div/div/button[3]");
    By nextButton = By.xpath("//button[normalize-space()='Siguiente']");
    By savePatientButton = By.xpath("button[data-testid='modal-primary-btn']");
    
    String patientRowByNameXpath = "(//div[starts-with(@data-testid, 'patient-row-')][.//span[@title=\"%s\"]])[1]";

    // actions
    public void openFromSidebar() {
        click(pacientesNavLink);
        waitUrlContains("/pacientes");
    }

    public String getModalTitle() {
        return driver.findElement(modalTitle).getText();
    }

    public void enterName(String n) {
        driver.findElement(nameField).sendKeys(n);
    }

    public void enterLastName(String n) {
        driver.findElement(lastNameField).sendKeys(n);
    }

    public void enterBirthday(String n) {
        driver.findElement(birthdayField).sendKeys(n);
    }

    public void enterTelephoneNumber(String n) {
        driver.findElement(telephoneNumberField).sendKeys(n);
    }

    public void selectOtherGenderOption() {
        click(genderDropDown);
        click(otherGenderOption);
    }

    public void addNewPatient() {
        click(nuevoPacienteButton);
    }

    public void filloutNewPatient(String name, String lastName, String birthday, String telephoneNumber) {
        enterName(name);
        enterLastName(lastName);
        enterBirthday(birthday);
        enterTelephoneNumber(telephoneNumber);
        selectOtherGenderOption();

        for (int i = 0; i < 6; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        }

        click(savePatientButton);
    }

    public boolean isNewPatientOnPatientList(String fullName) {
        return (!driver.findElements(By.xpath("//*[text()='" + fullName + "']")).isEmpty());        
    }

    public void openFirstPatientByName(String fullName) {
        click(By.xpath(String.format(patientRowByNameXpath, fullName)));
    }
}
