package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    static WebDriver driver;
        public Wrappers(WebDriver driver){
            this.driver = driver;
        }
        public static void logStatus(String Sentence,String result){
            System.out.println(Sentence + " - " + result);
        }
    
        public static void moveToElement(WebElement a){
            Actions action = new Actions(driver);
            action.moveToElement(a).perform();
        }
        public static void Print(String a){
            System.out.println(a);
        }
       // public static int 
}
