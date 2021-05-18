package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("https://www.fly-music.ro/index.php?controller=authentication")
public class FlyMusicPage extends PageObject {

    @FindBy(id="email")
    private WebElementFacade emailField;

    @FindBy(id="passwd")
    private WebElementFacade passwordField;

    @FindBy(id="SubmitLogin")
    private WebElementFacade loginBtn;

    @FindBy(id="header_user_info")
    private WebElementFacade header;

    @FindBy(id="st_menu_74")
    private WebElement promotiiBtn;

    @FindBy(id="product_list")
    private WebElement productList;

    public void typeEmail(String email) {
        emailField.type(email);
    }

    public void typePassword(String password) {
        passwordField.type(password);
    }

    public void clickLoginButton() {
        loginBtn.click();
    }

    public void clickLogoutButton() {
        List<WebElement> children = header.findElements(By.xpath("./child::*"));
        WebElement logoutBtn = children.get(3);
        logoutBtn.click();
    }

    public void clickPromotii() {
        promotiiBtn.click();
    }

    public void clickFirstProduct() {
        WebElement elem = productList.findElement(By.xpath(".//a"));
        elem.click();
    }
}