package Helpers;

import org.openqa.selenium.chrome.ChromeDriver;

public class Setup {
    public static ChromeDriver getWebSetup(String webURL){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.get(webURL);
        return chromeDriver;
    }
}
