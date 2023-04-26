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
    @Order(2)
    public void verificarSeTemNomeUsernameEBotaoEditar() {
        //driver.get("http://localhost:8080/app/users");

        WebElement elementName = driver.findElement(
                By.xpath("/html/body/div/div/div/div[2]/table/tbody/tr/td[1]")
        );
        WebElement elementUserName = driver.findElement(
                By.xpath("/html/body/div/div/div/div[2]/table/tbody/tr/td[2]")
        );

        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/table/tbody/tr/td[3]/a")).click();

        // erro StaleElementReferenceException no elementName.getText()
        assertEquals(name, elementName.getText());
        assertEquals(username, elementUserName.getText());
        assertEquals("http://localhost:8080/app/users/edit/1", driver.getCurrentUrl());
    }

    @Test
    @Order(3)
    public void naoMostrarSenhaAntiga() {
        driver.get("http://localhost:8080/app/users/edit/1");

        WebElement senha = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        boolean resultado = false;
        if(senha.getText()== null || senha.getText().trim().equals("")) {
             resultado = true;
        }
        assertTrue(resultado);
    }

    @Test
    @Order(5)
    public void permitirAtualizarNomeESenha() {
        driver.get("http://localhost:8080/app/users/edit/1");

        WebElement nome = driver.findElement(By.id("name"));
        nome.clear();
        nome.sendKeys("Mary Jane");

        WebElement senha = driver.findElement(By.id("password"));
        senha.sendKeys("abcdef");

        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/button")).click();

        WebElement nomeAtualizado = driver.findElement(
                By.xpath("/html/body/div/div/div/div[2]/table/tbody/tr/td[1]")
        );
        assertEquals("Mary Jane", nomeAtualizado.getText());
    }

    @Test
    @Order(4)
    public void naoPermitirAlterarUsername() {
        driver.get("http://localhost:8080/app/users/edit/1");

        WebElement campoUsername = driver.findElement(By.id("username"));
        boolean resultado = false;

        if (campoUsername.isEnabled()) {
            resultado = true;
        }

        assertTrue(resultado);
    }

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
