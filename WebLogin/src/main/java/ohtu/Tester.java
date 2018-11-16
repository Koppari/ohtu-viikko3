package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        Random r = new Random();

        // login+logout oikeilla tiedoilla
        driver.get("http://localhost:4567");

        sleep(1);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(1);

        // failed login with wrong pw
        driver.get("http://localhost:4567");

        sleep(1);

        element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("a");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(1);

        // failed login with non-existing user
        driver.get("http://localhost:4567");

        sleep(1);

        element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("a");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(1);

        // create new user
        driver.get("http://localhost:4567");

        sleep(1);

        element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("user" + r.nextInt(1000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("password123");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("password123");

        sleep(1);
        element.submit();

        sleep(1);

        // create new user and log out
        driver.get("http://localhost:4567");

        sleep(1);

        element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("user" + r.nextInt(1000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("password123");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("password123");

        sleep(1);
        element.submit();

        sleep(1);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(1);
        driver.quit();

    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
