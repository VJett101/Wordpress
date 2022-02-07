package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Elements {
    public static void waitForElement(WebDriver driver, int time, By locator){
        var wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static Boolean waitforText(WebDriver driver, int time, String webelement, String text){
       try {
           var wait = new WebDriverWait(driver, time);
           wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className(webelement), text));
           return true;
       }catch (NoSuchElementException e) {
           return false;
       }
    }



}
