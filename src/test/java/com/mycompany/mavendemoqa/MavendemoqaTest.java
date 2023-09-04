/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mavendemoqa;

import static com.mycompany.mavendemoqa.MavendemoqaTest.driver;
import io.netty.channel.unix.Errors;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


/**
 *
 * @author Nevsk
 */
public class MavendemoqaTest {
    
    static WebDriver driver;
    
    public MavendemoqaTest() {
    }

    /**
     * Test of main method, of class Mavendemoqa.
     */
    @Test
    public void testMain() {
        boolean hasPopup;
        try {
            driver = new ChromeDriver();
            RegistrationForm.fillInTheForm(driver);
            hasPopup = driver.findElement(By.xpath("//div[text()=\"Thanks for submitting the form\"]")).isDisplayed();
        } catch (NoSuchElementException  e) {
            hasPopup = false; 
        }
        assertTrue(hasPopup, "Thanks modal not display");
    }
    
    class RegistrationForm {
        
        public static void fillInTheForm(WebDriver driver) {
            driver.get("https://demoqa.com/automation-practice-form");
            new Actions(driver).scrollToElement(driver.findElement(By.id("submit"))).perform();
            driver.findElement(By.id("firstName")).sendKeys(MavendemoqaTest.GenerateData.getRandName());
            driver.findElement(By.id("lastName")).sendKeys(MavendemoqaTest.GenerateData.getRandName());
            driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(MavendemoqaTest.GenerateData.getRandEmail());
            driver.findElement(By.cssSelector("#genterWrapper div:nth-child(2) div:nth-child(" + MavendemoqaTest.GenerateData.getRandomInt(1, 3) +") .custom-control-label")).click();
            driver.findElement(By.id("userNumber")).sendKeys(MavendemoqaTest.GenerateData.getRandomPhone()); 
            driver.findElement(By.id("dateOfBirthInput")).sendKeys("");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.findElement(By.cssSelector(".react-datepicker__month .react-datepicker__week:nth-child(" + MavendemoqaTest.GenerateData.getRandomInt(1, 6) +") .react-datepicker__day:nth-child(" + MavendemoqaTest.GenerateData.getRandomInt(1, 7) +")")).click();
            driver.findElement(By.id("currentAddress")).sendKeys(MavendemoqaTest.GenerateData.getRandStr(10));
            driver.findElement(By.cssSelector("#state div")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.findElement(By.cssSelector("#react-select-3-option-" + MavendemoqaTest.GenerateData.getRandomInt(0, 3))).click();
            String file = new File("src/main/java/com/mycompany/mavendemoqa/file/file.jpg").getAbsolutePath();
            driver.findElement(By.cssSelector("#uploadPicture")).sendKeys(file);
            driver.findElement(By.cssSelector("#city div")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.findElement(By.cssSelector("#react-select-4-option-" + MavendemoqaTest.GenerateData.getRandomInt(0, 1))).click();

            driver.findElement(By.id("subjectsContainer")).click();
            driver.findElement(By.id("subjectsInput")).sendKeys("e");
            driver.findElement(By.id("react-select-2-option-0")).click();

            driver.findElement(By.id("userForm")).submit();
        }
    }
    
    
    class GenerateData {
        
        static Random random = new Random();
        
        public static String getRandStr(int len) {
            String res = RandomString.make(len);
            
            
            return res;
        }
        
        public static String getRandName() {
            String alphabetsInUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
            String alphabetsInLowerCase = "abcdefghijklmnopqrstuvwxyz";
            int randInt = random.nextInt(alphabetsInUpperCase.length());
            String res = alphabetsInUpperCase.substring(randInt, randInt + 1);
            int len = getRandomInt(4, 8);
            for (int i = 0; i < len; i++) { 
                randInt = random.nextInt(alphabetsInLowerCase.length());
                res += alphabetsInLowerCase.substring(randInt, randInt + 1);
            } 
            
            return res;
        }
        
        public static String getRandEmail() {
            String res = RandomString.make(getRandomInt(4, 8));
            res += "@";
            res += RandomString.make(getRandomInt(4, 8));
            res += ".com";
            
            return res;
        }
        
        public static String getRandomPhone() {
            String res = "";
            
            for (int i = 0; i < 10; i++) {
                res += getRandomInt(0, 9);
            }
            
            return res;
        }
        
        public static int getRandomInt(int start, int end) {
            return start + (int) (random.nextFloat() * (end - start)); 
        }
        
        public static Date getRandomDate()
        {
            return new Date(random.nextLong());
        }
    }
}
