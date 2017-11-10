package AdminPane;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;

public class FirstTest {

    private static ChromeDriverService service;
    private WebDriver driver;

    public static String getChromeDriverPath() {

        String os = System.getProperty("os.name").toLowerCase();
        String bytes = System.getProperty("os.arch");

        boolean isWin = os.contains("win");
        boolean isMac = os.contains("mac");
        boolean isLinux = os.contains("nix") || os.contains("nux") || os.contains("aix");
        boolean is32 = bytes.equals("x86") || bytes.equals("i386") || bytes.equals("i486") || bytes.equals("i586") || bytes.equals("i686");
        boolean is64 = bytes.equals("x86_64") || bytes.equals("amd64");

        // Detect chrome driver directory
        String fileName = "";
        Path chromeDriverDirectory = null;
        if (isMac || isLinux) {
            chromeDriverDirectory = Paths.get("src/main/resources/chrome_driver");
        }
        if (isWin) {
            chromeDriverDirectory = Paths.get("src\\main\\resources\\chrome_driver");
        }

        String chromeDriverPath = chromeDriverDirectory.toAbsolutePath().toString();

        // Detect which driver to use
        if (isMac) {
            chromeDriverPath = chromeDriverPath.concat("/chromedriver_mac");
        }

        if (isLinux) {
            if (is32) {
                chromeDriverPath = chromeDriverPath.concat("/chromedriver_linux32");
            }
            if (is64) {
                chromeDriverPath = chromeDriverPath.concat("/chromedriver_linux32");
            }
        }

        if (isWin) {
            chromeDriverPath = chromeDriverPath.concat("\\chromedriver.exe");
        }

        return chromeDriverPath;
    }


    @BeforeClass
    public static void createAndStartService() {

        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(getChromeDriverPath()))
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
        login.sendKeys("misha2");

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

        WebElement number = driver.findElement(By.name("num_apartment"));
        number.sendKeys(String.valueOf(new Random().nextInt(10000)));
//
        DecimalFormat format = new DecimalFormat("#,##", new DecimalFormatSymbols(new Locale("ru")));
        String square = format.format((Math.random() * (100 - 20)) + 1);

        WebElement area = driver.findElement(By.name("area_apartment"));
        area.clear();
        area.sendKeys(square);

        int x = (int)(Math.random() * (999999999 - 100000000)) + 1; // ввод номера телефона kk
        WebElement numberphone = driver.findElement(By.cssSelector("#phone"));
        numberphone.sendKeys("0" + String.valueOf(x));

        WebElement fio = driver.findElement(By.name("full_name"));
        fio.sendKeys(String.valueOf("test SELENIUM" + new Random().nextInt(100)));

        WebElement passwordUser = driver.findElement(By.name("password"));
        passwordUser.clear();
        passwordUser.sendKeys("qwerty");

        driver.findElement(By.cssSelector("#info > form")).submit();

        driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);

        }








//        WebElement form = driver.findElement(By.tagName("form"));
//        form.submit();
    }
