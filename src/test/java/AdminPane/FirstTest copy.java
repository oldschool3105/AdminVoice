package AdminPane;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;

public class FirstTest {

    private static ChromeDriverService service;
    private WebDriver driver;

    public static void main(String[] args) {
        createAndStartService();
    }

    @BeforeClass
    public static void createAndStartService() {

        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/Users/myhail/Downloads/chromedriver"))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void createAndStopService() {
       // service.stop(); //остановка сервиса
    }

    @Before
    public void createDriver() {
        //System.setProperty("webdriver.chrome.driver", "/Users/myhail/Downloads/chromedriver");
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
    }

    @After
    public void quitDriver() {
//        driver.quit(); // закрытия браузера
    }



    @Test
    public void testGoogleSearch() {
        driver.get("http://178.159.110.21:88/"); //переходим по ссылке


        WebElement login = driver.findElement(By.name("login")); //ввод логина
        login.sendKeys("misha");

        WebElement password = driver.findElement(By.name("password")); // ввод пароля
        password.sendKeys("misha");

        driver.findElement(By.tagName("button")).click(); // нажатие на кнопку
        driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);

        driver.findElement(By.cssSelector("#left_menu > a:nth-child(1) > input:nth-child(1)")).click(); // переход в категорию жители
        driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);


        for(int i=0; i<1; i++){
            AddUserToDB();
        }


    }

    private void AddUserToDB () {


        driver.findElement(By.cssSelector("#content > a > button")).click();
        driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);

//        driver.findElement(By.cssSelector("#content > a > button > img")).click();
       driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);



        WebElement number = driver.findElement(By.cssSelector("#info > form > table > tbody > tr:nth-child(1) > td:nth-child(2) > input[type=\"text\"]"));
        number.sendKeys(String.valueOf(new Random().nextInt(10000)));
//

        DecimalFormat format = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        String areaa =  format.format((Math.random() * (1000 - 1)) + 1);

        WebElement area = driver.findElement(By.cssSelector("#info > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > input[type=\"number\"]"));
        area.clear();
        area.sendKeys(areaa);
        area.click();
        area.sendKeys(Keys.BACK_SPACE);


        driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);


        int x = (int)(Math.random() * (999999999 - 100000000)) + 1; // ввод номера телефона kk
        WebElement numberphone = driver.findElement(By.cssSelector("#phone"));
        numberphone.sendKeys("0" + String.valueOf(x));

        //System.out.println("0"+x);

        WebElement fio = driver.findElement(By.cssSelector("#info > form > table > tbody > tr:nth-child(4) > td:nth-child(2) > input[type=\"text\"]"));
        fio.sendKeys(String.valueOf("test" + new Random().nextInt(10000)));

        WebElement passwordUser = driver.findElement(By.cssSelector("#info > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > input[type=\"number\"]"));
        passwordUser.sendKeys("qwerty");

        System.out.println("hfbdgjhbfjhg");
//
//        driver.findElement(By.cssSelector("#info > form")).submit();
//
//
//        driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);

        }








//        WebElement form = driver.findElement(By.tagName("form"));
//        form.submit();
    }
