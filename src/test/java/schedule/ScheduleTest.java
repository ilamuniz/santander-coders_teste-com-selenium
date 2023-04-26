package schedule;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScheduleTest {
    private static WebDriver driver;
    private static String name;
    private static String username;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(
                new ChromeOptions().addArguments("--remote-allow-origins=*")
        );
        //driver.get("http://localhost:8080/app/users");
        //name = RandomStringUtils.randomAlphabetic(15);
    }

    @AfterAll
    public static void destroy() {
        //driver.quit();
    }

    @Test
    @Order(1)
    public void cadastrarUsuario() {
        driver.get("http://localhost:8080/app/users");

        driver.findElement(By.className("create")).click();

        name = "Hermione Granger";
        username = "hermionegranger";
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(RandomStringUtils.randomNumeric(4));
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/button")).click();

        WebElement element = driver.findElement(
                By.xpath("//*[text()='" + name + "']")
        );
        element.getText();
        assertNotNull(element);
    }

    @Test
    public void verificarSeTemNomeUsernameEBotaoEditar() {
        driver.get("http://localhost:8080/app/users");

        WebElement elementName = driver.findElement(
                By.xpath("//*[text()='" + name + "']")
        );
        WebElement elementUserName = driver.findElement(
                By.xpath("//*[text()='" + username + "']")
        );
        elementName.getText();
        elementUserName.getText();

        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/table/tbody/tr/td[3]/a")).click();

        assertNotNull(elementName);
        assertNotNull(elementUserName);
        assertEquals("http://localhost:8080/app/users/edit/1", driver.getCurrentUrl());
    }

    @Test
    public void naoMostrarSenhaAntiga() {
        driver.get("http://localhost:8080/app/users/edit/1");

        WebElement senha = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        boolean resultado = false;
        if(senha.getText()== null || senha.getText().trim().equals("")) {
             resultado = true;
        }
        assertTrue(resultado);
    }

//    @Test
//    public void cadastrarFilmeComTituloNull_naoPermitido() {
//        driver.get("http://localhost:8080/app/movies");
//
//        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/a")).click();
//
//        driver.findElement(By.id("genre")).sendKeys("Comédia");
//        driver.findElement(By.id("rating")).sendKeys("10");
//        driver.findElement(By.xpath("//form/button")).click();
//
//        WebElement element = driver.findElement(
//                By.className("movie-form-error")
//        );
//        assertNotNull(element);
//        assertEquals("must not be blank", element.getText());
//    }
//
//    @Test
//    public void cadastrarFilmeComNotaMaiorQue10_naoPermitido() {
//        driver.get("http://localhost:8080/app/movies");
//
//        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/a")).click();
//
//        driver.findElement(By.id("title")).sendKeys("Uma comédia");
//        driver.findElement(By.id("genre")).sendKeys("Comédia");
//        driver.findElement(By.id("rating")).sendKeys("11");
//        driver.findElement(By.xpath("//form/button")).click();
//
//        String message = driver.findElement(By.id("rating")).getAttribute("validationMessage");
//        assertEquals("Value must be less than or equal to 10.", message);
//    }
//
//    @Test
//    @Order(2)
//    public void listarFilmesCadastrados_encontrarSeleniumTest() {
//        driver.get("http://localhost:8080/app/movies");
//
//        WebElement element = driver.findElement(
//                By.xpath("//*[text()='" + name + "']")
//        );
//        assertNotNull(element);
//    }
//
//    @Test
//    @Order(3)
//    public void editarUmFilmeJaCadastrado() {
//        driver.get("http://localhost:8080/app/movies");
//
//        WebElement editElement = driver.findElement(
//                By.xpath("//td[text()='" + name + "']/following-sibling::td/a[text()='Edit']")
//        );
//        editElement.click();
//
//        name = RandomStringUtils.randomAlphabetic(20);
//        WebElement titleElement = driver.findElement(By.id("title"));
//        titleElement.clear();
//        titleElement.sendKeys(name);
//        driver.findElement(By.xpath("//form/button")).click();
//
//        WebElement movieElement = driver.findElement(
//                By.xpath("//td[text()='" + name + "']")
//        );
//        assertNotNull(movieElement);
//    }
//
//    @Test
//    @Order(4)
//    public void deletarUmFilmeCadastrado() {
//        driver.get("http://localhost:8080/app/movies");
//
//        WebElement deleteElement = driver.findElement(
//                By.xpath("//td[text()='" + name + "']/following-sibling::td/a[text()='Delete']")
//        );
//        deleteElement.click();
//
//        assertThrows(NoSuchElementException.class, () ->
//                driver.findElement(By.xpath("//td[text()='" + name + "']"))
//        );
//    }
}
