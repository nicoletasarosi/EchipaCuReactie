package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.FlyMusicPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class FlyMusicUserSteps {
    String validEmail = "xuuahssrjywsyaoqtp@mhzayt.online";
    String validPassword = "VVSS2021";

    String nonValidEmail = "fufu@gmail.com";
    String nonValidPassword = "123456789";

    FlyMusicPage flyMusicPage;

    @Step
    public void is_the_home_page() {
        flyMusicPage.open();
    }

    @Step
    public void doValidLogin() {
        flyMusicPage.typeEmail(validEmail);
        flyMusicPage.typePassword(validPassword);
        flyMusicPage.clickLoginButton();
    }

    @Step
    public void should_see_logged_in_page() {
        String homePageTitle = "Magazinul tau complet de instrumente muzicale - Fly Music";
        assertThat("Logged in successfully", flyMusicPage.getTitle().equals(homePageTitle));
    }

    @Step
    public void doNonValidLogin() {
        flyMusicPage.typeEmail(nonValidEmail);
        flyMusicPage.typePassword(nonValidPassword);
        flyMusicPage.clickLoginButton();
    }

    @Step
    public void should_see_login_error() {
        String loginError = "Autentificare esuata";
        assertThat("Logged in error", flyMusicPage.containsText(loginError));
    }

    @Step
    public void logout() {
        flyMusicPage.clickLogoutButton();
    }

    @Step
    public void should_see_login_page_after_logout() {
        String loginTitle = "Magazinul tau preferat de instrumente muzicale";
        assertThat("Logout success", flyMusicPage.containsText(loginTitle));
    }

    @Step
    public void addToCart() {
        flyMusicPage.clickPromotii();
        flyMusicPage.clickFirstProduct();
    }
}