package mainpage;

import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import waiters.Waiters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainPageTest {
    private final String BASE_URL = "https://otus.ru";
    private WebDriver driver;
    private Waiters waiters;

    private final String PASSWORD = System.getProperty("password");
    private final String LOGIN = System.getProperty("login");

    @BeforeAll
    public static void manage() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() {
        this.driver = new WebDriverFactory().create();
        this.waiters = new Waiters(driver);
    }

    @Test
    public void openingMainPage() {
        driver.get(BASE_URL + "/");
        WebElement header = driver.findElement(By.xpath("//div[./h2[text()='Авторские онлайн‑курсы для профессионалов']]"));

        // assertThat(header.isDisplayed()).as("Header with text should be visible").isTrue();
        // assertThat(waiters.waitForCondition(ExpectedConditions.visibilityOf(header))).as("Header with text should be visible").isTrue();
        assertThat(waiters.waitElementVisible(header)).as("Header with text should be visible").isTrue();

    }

    @AfterEach
    public void shutdownDriver() {
        driver.quit();
    }
}
