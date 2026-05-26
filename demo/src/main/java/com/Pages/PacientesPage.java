package com.Pages;

import org.openqa.selenium.By;

import com.Base.BasePage;

public class PacientesPage extends BasePage {
    // locators

    By pacientesNavLink = By.cssSelector("a[href='/pacientes']");
    By nuevoPacienteButton = By.xpath("/html/body/div/div[2]/header/div[4]/button");
    By sortDropDown = By.xpath("/html/body/div/div[2]/main/div/div[1]/div[3]/button");
    By oldestToYoungestSortOption = By.xpath("/html/body/div/div[2]/main/div/div[1]/div[3]/div/div[3]/button[1]");
    By filterDropDown = By.xpath("/html/body/div/div[2]/main/div/div[1]/div[2]/button");
    By masculineFilterOption = By.xpath("/html/body/div/div[2]/main/div/div[1]/div[2]/div/div[1]/button[1]");

    By modalTitle = By.xpath("/html/body/div[3]/div/div[2]/div/header/h2/span");

    By nameField = By.id("nombre");
    By lastNameField = By.id("apellidos");
    By birthdayField = By.cssSelector("input[data-testid='datepicker-fecha_nacimiento']");
    By telephoneNumberField = By.id("telefono");
    By genderDropDown = By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/form/div/div[1]/div[2]/div[3]/div/button");
    By otherGenderOption = By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/form/div/div[1]/div[2]/div[3]/div/div/button[3]");
    By nextButton = By.xpath("//button[normalize-space()='Siguiente']");
    By savePatientButton = By.cssSelector("button[data-testid='modal-primary-btn']");

    String patientRowByNameXpath = "(//div[starts-with(@data-testid, 'patient-row-')][.//span[@title=\"%s\"]])[1]";
    By patientSearchBox = By.cssSelector("input[data-testid='patients-search']");
    By noDataMessage = By.xpath("/html/body/div/div[2]/main/div/div[2]/div/div[2]/span[2]");
    By firstPatientAge = By.xpath("/html/body/div/div[2]/main/div/div[2]/div/div[2]/div[1]/div[3]/span[2]");
    By secondPatientAge = By.xpath("/html/body/div/div[2]/main/div/div[2]/div/div[2]/div[2]/div[3]/span[2]");
    By firstPatientGender = By.xpath("/html/body/div/div[2]/main/div/div[2]/div/div[2]/div[1]/div[4]");

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
            waitFor(nextButton).click();
        }

        click(savePatientButton);
    }

    public boolean isNewPatientOnPatientList(String fullName) {
        try {
            waitFor(By.xpath(String.format(patientRowByNameXpath, fullName)));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void openFirstPatientByName(String fullName) {
        click(By.xpath(String.format(patientRowByNameXpath, fullName)));
    }

    public void enterPatientToSearch(String n) {
        type(patientSearchBox, n);
    }

    public void searchForPatient(String name) {
        enterPatientToSearch(name);
    }

    public boolean isNoDataVisible() {
        try {
            waitFor(noDataMessage);
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void selectSortByOldestToYoungest() {
        click(sortDropDown);
        click(oldestToYoungestSortOption);
    }

    public boolean isSortedOldestToYoungest() {
        return (getAgeFirstPatientOnList() >= getAgeSecondPatientOnList());
    }

    private int getAgeFirstPatientOnList() {
        return Integer.parseInt(getText(firstPatientAge).split(" ")[0]);
    }

    private int getAgeSecondPatientOnList() {
        return Integer.parseInt(getText(secondPatientAge).split(" ")[0]);
    }

    public void seletcFilterByMasculine() {
        click(filterDropDown);
        click(masculineFilterOption);
    }

    public boolean isShownOnlyMasculine() {
        return getText(firstPatientGender).equals("Masculino");
    }
}
