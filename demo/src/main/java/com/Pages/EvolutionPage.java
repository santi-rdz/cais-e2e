package com.Pages;

import org.openqa.selenium.By;

import com.Base.BasePage;

public class EvolutionPage extends BasePage {
    //Nota de evolución guardada -> Toast
    //Nota de evolución actualizada
    // locators

    By patientBtn = By.xpath("//*[@id=\"root\"]/div[2]/main/div/div[2]/div/div[2]/div[1]");
    By notesBtn = By.cssSelector("button[data-testid=\"tab-notas\"");
    By addNoteBtn = By.cssSelector("button[data-testid=\"create-note-btn\"");
    By nextBtn = By.cssSelector("button[data-testid=\"modal-primary-btn\"");
    By updateNoteBtn = By.xpath("//*[@id=\"root\"]/div[2]/main/div/div/div[3]/div/div/div[2]/div[1]/button");
    By saveChangesBtn = By.xpath("/html/body/div[4]/div/div[2]/div/div[2]/div/button[2]");
    By evolutionNote = By.xpath("//*[@id=\"root\"]/div[2]/main/div/div/div[3]/div/div/div[2]/article[1]");

    By motiveText = By.id("motivo_consulta");
    By anteText = By.id("ant_gine_andro");
    By weightText = By.id("peso");
    By heightText = By.id("altura");
    By planText = By.id("plan_tratamiento");
    By treatmentText = By.id("tratamiento");
    By dateField = By.cssSelector("input[data-testid=\"datepicker-fecha\"]");
    By hourField = By.cssSelector("input[data-testid=\"datepicker-hora\"]");

    // actions
    public void open(){
        driver.get("http://localhost:5173/pacientes");
    }

    public void goToEvolution(){
        click(patientBtn);
        click(notesBtn);
    }

    public void addEvolutionNote(String motivo, String ante, String peso, String altura, String plan, String tratamiento){
        click(addNoteBtn);

        type(motiveText, motivo);
        type(anteText, ante);
        click(nextBtn);

        click(nextBtn);

        type(weightText,peso);
        type(heightText, altura);
        click(nextBtn);

        type(planText, plan);
        type(treatmentText, tratamiento);
        click(nextBtn);
    }

    public void updateEvolutionNote(String motivo){
        click(evolutionNote);
        click(updateNoteBtn);

        type(motiveText, motivo);
        click(saveChangesBtn);
    }

    public void addEmptyEvolutionNote(){
        click(addNoteBtn);
        clearTextField(dateField);
        clearTextField(hourField);

        click(nextBtn);
        click(nextBtn);
        click(nextBtn);
        click(nextBtn);
    }
}
