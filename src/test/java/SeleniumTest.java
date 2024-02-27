import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumTest {

        WebDriver driver;

        @Test
        public void loginTestPositive() throws InterruptedException {
            //open browser
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get("https://the-internet.herokuapp.com/login");

            //explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            //input username & password
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username']")));
            driver.findElement(By.id("username")).sendKeys("tomsmith");
            driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
            driver.findElement(By.xpath("//button[@class='radius']")).click();

            // verifikasi berhasil masuk halaman homepage
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(.,'Secure Area')]")));
            String txtActual = driver.findElement(By.xpath("//h2[contains(.,'Secure Area')]")).getText();
            System.out.println(txtActual);
            String txtExpected = "Secure Area";
            Assert.assertEquals(txtActual,txtExpected);
            Thread.sleep(3);

            //close browser
            driver.quit();

        }
}
