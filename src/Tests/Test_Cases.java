package Tests;

import Helpers.Helpers;
import Helpers.Setup;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import static Helpers.Elements.waitForElement;


public class Test_Cases {
    private ChromeDriver driver;

    /**
     * Login for the user and navigates to profile section
     */

    @Before
    public void setUp(){
        driver = Setup.getWebSetup("https://wordpress.com/me");
        Helpers hlp = new Helpers(driver);
        hlp.loginHelper("testvfuser101", "Test!123#");

    }
    /**
     * Test for opening profile section
     * @throws Exception
     */

    @Test
    public void openProfile()throws Exception
    {
        By firstName = By.id("first_name");
        waitForElement(driver,10, firstName);
    }

    /**
     * Test to see if all Profile Sections and Buttons are present
     * @throws Exception
     */

    @Test
    public void sectionScrape()throws Exception
    {
        Helpers hlp = new Helpers(driver);
        hlp.sectionScraper();

    }

    /**
     * Test to see if profile changes save successfully
     * @throws Exception
     */

    @Test
    public void saveProfile()throws Exception
    {
        Helpers hlp = new Helpers(driver);
        hlp.enterProfileInfo("Joe", "Buck","Joe Buck","Test 1 2 3");
    }

    /**
     * Test to see if user can add link for their own website to the profile section
     */

    @Test
    public void saveLink()throws Exception
    {
        Helpers hlp = new Helpers(driver);
        hlp.addLink();
    }

    /**
     * Test to see if user can add URL and Description for Google to their profile section
     */

    @Test
    public void saveURL()throws Exception
    {
        Helpers hlp = new Helpers(driver);
        hlp.addURL("www.google.com", "This is Google");
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}
