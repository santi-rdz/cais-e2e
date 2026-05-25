package com.Pages;

import org.openqa.selenium.By;

import com.Base.BasePage;

public class LoginPage extends BasePage {
  // locators
  By emailInput = By.id("email");
  By passwordInput = By.id("password");
  By submit = By.cssSelector("[data-testid='login-submit']");

  // actions
  public void open(){
     driver.get("http://localhost:5173/login");
  }
  public void loginAs(String email, String password){
    type(emailInput, email);
    type(passwordInput, password);
    click(submit);
  }
}
