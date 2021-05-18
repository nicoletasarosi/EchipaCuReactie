package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.example.steps.serenity.FlyMusicUserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class LoginLogout {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public FlyMusicUserSteps user;

    @Test
    public void start() {
        user.is_the_home_page();
        user.doValidLogin();
        user.should_see_logged_in_page();
        user.logout();
        user.should_see_login_page_after_logout();
    }
}