package com.automation.training.tests;

import com.automation.training.pages.HomePage;
import org.testng.annotations.Test;

public class TestSuite extends BaseTests {

    @Test(description = "First test")
    public void firstTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.goToHome();
        homePage.getFreeSampleButtonLocation();
    }

}
