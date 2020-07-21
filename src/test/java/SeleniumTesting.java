//source: http://the-internet.herokuapp.com/

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SeleniumTesting {

    private WebDriver driver;
    private String baseURL = "http://the-internet.herokuapp.com/";

    @Before
    public void setup(){

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){

        //driver.close();
        //driver.quit();
    }

    @Test
    public void testAddRemoveElements(){

        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.navigate().to(baseURL + "add_remove_elements/");

        //Poczekaj, az button :Add Element: bedzie klikalny
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button")));

        //Stworz piec nowych przyciskow
        for (int i = 0; i < 5; i++){

            addBtn.click();
        }

        //Wyszukaj wszystkie stworzone buttony
        List<WebElement> groupOfNewButtons = driver.findElements(By.className("added-manually"));

        //Potwierdz liczbe stworzonych buttonow (ASSERCJA)
        assertEquals(5, groupOfNewButtons.size());

        for (WebElement Button: groupOfNewButtons){

            Button.click();
        }

        List<WebElement> emptyGroupOfNewButtons = driver.findElements(By.className("added-manually"));

        assertEquals(0, emptyGroupOfNewButtons.size());
    }
}
