package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static Helpers.Elements.*;


public class Helpers {

    private WebDriver driver;
    private WebElement webElement;

    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By photoChange = By.cssSelector("#primary > main > div.card.profile__settings > div > div:nth-child(1) > span > div > div.edit-gravatar__label-container > span");
    private By displayName = By.id("display_name");
    private By aboutMe = By.id("description");
    private By toggleGravatar = By.id("inspector-toggle-control-0");
    private By saveButton = By.cssSelector("#primary > main > div.card.profile__settings > form > p > button");
    private By addLink = By.cssSelector("#primary > main > div:nth-child(4) > div.section-header__actions > button > svg");
    private By emailOrUsername = By.id("usernameOrEmail");
    private By continueButton = By.cssSelector("#primary > div > main > div > div > form > div.card.login__form > div.login__form-action > button");
    private By password = By.id("password");
    private By loginButton = By.cssSelector("#primary > div > main > div > div > form > div.card.login__form > div.login__form-action > button");
    private By successfulPopup = By.cssSelector("#notices > div > span.notice__content > span");
    private By cancelAddLink = By.cssSelector("#primary > main > div:nth-child(5) > div > form > button.button.profile-links-add-wordpress__cancel.form-button");
    private By checkBox = By.cssSelector("#primary > main > div:nth-child(5) > div > form > ul > li > input");
    private By addSite = By.cssSelector("#primary > main > div:nth-child(5) > div > form > button.button.form-button.is-primary");
    private By addExternalSite = By.cssSelector("#primary > main > div:nth-child(5) > form > fieldset > button.button.profile-links-add-other__add.form-button.is-primary");
    private By profileLinkURL = By.className("profile-link__url");
    private By enterURLField = By.cssSelector("#primary > main > div:nth-child(5) > form > fieldset > input.form-text-input.profile-links-add-other__value");
    private By enterDescriptionforURL = By.cssSelector("#primary > main > div:nth-child(5) > form > fieldset > input.form-text-input.profile-links-add-other__title");

    public Helpers(WebDriver driver) {
        this.driver = driver;
    }

    public void loginHelper(String user, String pw) {

        //Logs user in
        waitForElement(driver, 10, emailOrUsername);
        driver.findElement(emailOrUsername).sendKeys(user);
        driver.findElement(continueButton).click();
        waitForElement(driver, 10, password);
        driver.findElement(password).sendKeys(pw);
        driver.findElement(loginButton).click();
    }

    public void sectionScraper() throws Exception {

        //Looks for all expected elements on the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitForElement(driver, 10, firstName);
        waitForElement(driver, 10, lastName);
        waitForElement(driver, 10, photoChange);
        waitForElement(driver, 10, displayName);
        waitForElement(driver, 10, aboutMe);
        WebElement Element = driver.findElement(toggleGravatar);
        js.executeScript("arguments[0].scrollIntoView();", Element);
        driver.findElement(toggleGravatar).click();
        waitForElement(driver, 10, saveButton);
        waitForElement(driver, 10, addLink);
    }

    public void enterProfileInfo(String firstnameP, String lastnameP, String displayNameP, String aboutMeP) throws Exception {

        //Enters information and saves it to profile
        waitForElement(driver, 10, firstName);
        Thread.sleep(2000);
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(firstnameP);
        Thread.sleep(2000);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lastnameP);
        Thread.sleep(2000);
        driver.findElement(displayName).clear();
        driver.findElement(displayName).sendKeys(displayNameP);
        Thread.sleep(2000);
        driver.findElement(aboutMe).clear();
        driver.findElement(aboutMe).sendKeys(aboutMeP);
        driver.findElement(saveButton).click();

        //Checks for save popup
        waitForElement(driver, 10, successfulPopup);
        Thread.sleep(3000);

        //Validates New Profile Info
        String actualFirstName = driver.findElement(firstName).getDomProperty("value");
        String[] info = {"Joe", "Buck", "Joe Buck", "Test 1 2 3"};
        if (actualFirstName.equals(info[0])) {
            System.out.println("First name matches");
        } else System.out.println("First name is incorrect");
        String actualLastName = driver.findElement(lastName).getDomProperty("value");
        if (actualLastName.equals(info[1])) {
            System.out.println("Last name matches");
        } else System.out.println("Last name is incorrect");
        String actualUserName = driver.findElement(displayName).getDomProperty("value");
        if (actualUserName.equals(info[2])) {
            System.out.println("Public display name matches");
        } else System.out.println("Public display name is incorrect");
        String actualAboutMe = driver.findElement(aboutMe).getText();
        if (actualAboutMe.equals(info[3])) {
            System.out.println("About me matches");
        } else System.out.println("About me is incorrect");

        //clears fields to rerun test
        Thread.sleep(2000);
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys("new");
        Thread.sleep(2000);
        driver.findElement(lastName).clear();
        Thread.sleep(2000);
        driver.findElement(aboutMe).clear();
        driver.findElement(saveButton).click();
        waitForElement(driver, 10, successfulPopup);


    }

    public void addLink() throws Exception {

        //adds a link for user's domain to the profile link section and validates if the text of the link matches
        waitForElement(driver, 10, firstName);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(toggleGravatar);
        js.executeScript("arguments[0].scrollIntoView();", Element);
        driver.findElement(addLink).click();
        Thread.sleep(1000);
        List<WebElement> popbox = driver.findElements(By.className("popover__menu-item"));
        popbox.get(0).click();
        waitForElement(driver, 10, cancelAddLink);
        driver.findElement(checkBox).click();
        driver.findElement(addSite).click();
        waitForElement(driver, 10, profileLinkURL);
        String expectedText = "techinc135981440.wordpress.com";
        String actualText = driver.findElement(profileLinkURL).getText();
        System.out.println(actualText);
        if (actualText.equals(expectedText)) {
            System.out.println("Correct link was Saved");
        }
    }

    public void addURL(String website, String description) throws Exception {

        //Adds a url and description to link section
        waitForElement(driver, 10, firstName);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(toggleGravatar);
        js.executeScript("arguments[0].scrollIntoView();", Element);
        driver.findElement(addLink).click();
        Thread.sleep(1000);
        List<WebElement> dropbox = driver.findElements(By.className("popover__menu-item"));
        dropbox.get(1).click();
        waitForElement(driver, 10, enterURLField);
        driver.findElement(enterURLField).sendKeys(website);
        driver.findElement(enterDescriptionforURL).sendKeys(description);
        Thread.sleep(3000);
        driver.findElement(addExternalSite).click();

        //Checks to see if URL and website were added to my profile section
        WebElement expectedDesc = driver.findElement(By.xpath("//*[text()='This is Google']"));
        WebElement expectedURL = driver.findElement(By.xpath("//*[text()='www.google.com']"));
        if (expectedDesc.isDisplayed()){
            System.out.println("Pass");
            Thread.sleep(3000);
        }else throw new Exception("Description is missing");
        if(expectedURL.isDisplayed()){
            System.out.println();
            Thread.sleep(3000);
        }else throw new Exception("URl is missing");
    }

}



