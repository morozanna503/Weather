import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class MorozAnna503Test {
    //TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR"
    @Test
    public void testH2TagTextWhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/romanmorozov/Desktop/ChromDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();
        Thread.sleep(5000);

        WebElement parisFRChoiceInDrownMenu = driver.findElement(
                By.xpath("//ul[@class=\"search-dropdown-menu\"]//li//span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDrownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();
        //A
        Assert.assertEquals(actualResult, expectedResult);
        Thread.sleep(5000);

        driver.quit();

    }

    //TC_11_01
    //1.  Открыть базовую ссылку
    //2.  Нажать на пункт меню Guide
    //3.  Подтвердить, что вы перешли на страницу со ссылкой
    // https://openweathermap.org/guide и что title этой страницы OpenWeatherMap API guide -
    // OpenWeatherMap
    @Test
    public void testFindGuideAndTitle() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/romanmorozov/Desktop/ChromDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchGuide = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/guide']")
        );
        searchGuide.click();
        Thread.sleep(5000);

        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);
        Thread.sleep(5000);

        String actualResult2 = driver.getTitle();
        Assert.assertEquals(actualResult2, expectedResult2);
        Thread.sleep(5000);

        driver.quit();

    }

    //TC_11_02
    //1.  Открыть базовую ссылку
    //2.  Нажать на единицы измерения Imperial: °F, mph
    //3.  Подтвердить, что температура для города показана в Фарингейтах
    @Test
    public void testCheckFaringeityToPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/romanmorozov/Desktop/ChromDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        driver.get(url);
        driver.manage().window().maximize();// раскрывает окно на полный экран
        Thread.sleep(10000);

        WebElement text = driver.findElement(
                By.xpath("//div[@class = 'option']/following-sibling::div")
        );
        text.click();
        Thread.sleep(5000);

        WebElement tempF = driver.findElement(
                By.xpath("//div[@class = 'current-temp']/span")
        );

        String tempInf = tempF.getText();
        String actualResult = tempInf.substring((tempInf.length() - 2));//метод string rоторый выводит кусочек желаемого текста F

        Assert.assertTrue(tempF.getText().contains(expectedResult));
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_03
    //1.  Открыть базовую ссылку
    //2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential
    // for the site to work. We also use non-essential cookies to help us improve our services.
    // Any data collected is anonymised. You can allow all cookies or manage them individually.”
    //3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    @Test
    public void testCheckBelowPageContainTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/romanmorozov/Desktop/ChromDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        driver.get(url);

        Thread.sleep(10000);
        WebElement textElement = driver.findElement(
                By.className("stick-footer-panel__description"));

        WebElement buttonAllowAll = driver.findElement(By.xpath("//button[text()='Allow all']"));
        WebElement buttonManageCookies = driver.findElement(
                By.xpath("//a[@href='/cookies-settings']"));

        Assert.assertEquals(buttonAllowAll.getText(),"Allow all");
        Assert.assertEquals(buttonManageCookies.getText(),"Manage cookies");
        Assert.assertEquals(textElement.getText(),expectedResult);

        driver.quit();
    }

    //TC_11_04
    //1.  Открыть базовую ссылку
    //2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start”
    // и “Ask a question”
    @Test
    public void testOpenSupportThenCheckSubMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/romanmorozov/Desktop/ChromDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultFAQ = "FAQ";
        String expectedResultHTS = "How to start";
        String expectedResultASK = "Ask a question";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement searchSupport = driver.findElement(By.id("support-dropdown"));
        searchSupport.click();
        Thread.sleep(5000);

        //Assert.assertEquals(driver.findElement(By.xpath("//div[@id='support-dropdown']/ul[@id='support-dropdown-menu']/li")).getSize(),3);

        WebElement checkFAQIsPresent = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']"));
        String actualResultFAQ = checkFAQIsPresent.getText();
        Thread.sleep(5000);

        WebElement checkHTSIsPresent = driver.findElement(By.xpath("//ul[@id = 'support-dropdown-menu']//a[@href = '/appid']"));
        String actualResultHTS = checkHTSIsPresent.getText();
        Thread.sleep(5000);

        WebElement checkASKIsPresent = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']"));
        String actualResultASK = checkASKIsPresent.getText();
        Thread.sleep(5000);

        Assert.assertEquals(actualResultFAQ,expectedResultFAQ);
        Assert.assertEquals(actualResultHTS,expectedResultHTS);
        Assert.assertEquals(actualResultASK,expectedResultASK);

        driver.quit();
    }
    //TC_11_05
    //1. Открыть базовую ссылку
    //2. Нажать пункт меню Support → Ask a question
    //3. Заполнить поля Email, Subject, Message
    //4. Не подтвердив CAPTCHA, нажать кнопку Submit
    //5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed,please try again.”
    @Test
    public void testCheckSubmenuSupport() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/romanmorozov/Desktop/ChromDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String email = "google@gmail.com";
        String message = "Test message";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement supportMenu = driver.findElement(By.xpath("//li[@class='with-dropdown']"));
        supportMenu.click();

        WebElement itemAskQuestion = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']"));
        itemAskQuestion.click();

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        WebElement emailField = driver.findElement(By.xpath("//input[@class='form-control string email required']"));
        emailField.sendKeys(email);

        WebElement selectField = driver.findElement(By.xpath("//select[@class='form-control select required']"));
        selectField.click();

        WebElement selectFieldChoice = driver.findElement(By.xpath("//option[@value='Sales']"));
        selectFieldChoice.click();

        WebElement messageField = driver.findElement(By.xpath("//textarea[@class='form-control text required']"));
        messageField.sendKeys(message);

        WebElement submitButton = driver.findElement(By.xpath("//input[@data-disable-with='Create Question form']"));
        submitButton.click();
        Thread.sleep(2000);

        WebElement reCaptchaText = driver.findElement(By.xpath("//div[@class='help-block']"));
        String actualResult = reCaptchaText.getText();

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }
    //TC_11_06
    //1.  Открыть базовую ссылку
    //2.  Нажать пункт меню Support → Ask a question
    //3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
    //4. Оставить пустым поле Email
    //5. Заполнить поля  Subject, Message
    //6. Подтвердить CAPTCHA
    //7. Нажать кнопку Submit
    //8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
   @Ignore
    @Test
    public void testCheckCaptcha() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/romanmorozov/Desktop/ChromDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "can't be blank";
        String subject = "Other";
        String message = "Hi lilu we are waiting for you";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement clickSupport = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        clickSupport.click();

        String originalWindow = driver.getWindowHandle();
        Thread.sleep(5000);
        WebElement selectSubmenuASK = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']"));
        selectSubmenuASK.click();
        Thread.sleep(5000);

        for(String windowHandle : driver.getWindowHandles()){
            if(!originalWindow.contentEquals(windowHandle)){
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(5000);

        WebElement enterMessage = driver.findElement(By.xpath("//textarea[@class='from-control text required']"));
        enterMessage.click();
        enterMessage.sendKeys(message);
        Thread.sleep(5000);

        String window2 = driver.getWindowHandle();
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));

        WebElement enterCaptcha = driver.findElement(By.xpath("//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked "
        +"rc-anchor-checkbox']"));
        enterCaptcha.click();
        Thread.sleep(10000);

        driver.switchTo().window(window2);

        WebElement pressSubmit = driver.findElement(By.xpath("//input[@data-disable-with='Create Question form']"));
        pressSubmit.click();

        WebElement confirmErrorEmail = driver.findElement(By.xpath("//span[@class='help-block']"));
        String actualResult = confirmErrorEmail.getText();

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }
    //TC_11_07
    //1.  Открыть базовую ссылку
    //2.  Нажать на единицы измерения Imperial: °F, mph
    //3.  Нажать на единицы измерения Metric: °C, m/s
    //4.  Подтвердить, что в результате этих действий,
    // единицы измерения температуры изменились с F на С
    @Test
    public void testCheckImperial() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/romanmorozov/Desktop/ChromDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String tempValue = "°C";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement changeTempToF = driver.findElement(By.xpath("//div[@class='option'][contains(text(),'°F')]"));
        changeTempToF.click();
        Thread.sleep(5000);

        WebElement changeTempToC = driver.findElement(By.xpath("//div[@class='option'][contains(text(),'°C')]"));
        changeTempToC.click();
        Thread.sleep(5000);

        String tempC = driver.findElement(By.xpath("//span[@class = 'heading'][contains(text(),'°C')]")).getText();

        Boolean actualResult = tempC.contains(tempValue);

        Assert.assertTrue(actualResult);

        driver.quit();
    }
    //TC_11_08
    //1.  Открыть базовую ссылку
    //2.  Нажать на лого компании
    //3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить,
    // что текущая ссылка не изменилась
    @Test
    public void testLogo() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/romanmorozov/Desktop/ChromDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement imageBanner = driver.findElement(By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']"));
        imageBanner.click();

        Thread.sleep(5000);

        String expectedResult = "https://openweathermap.org/";
        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }
    @Ignore
    @Test
    public void testLogoTWO() {
        System.setProperty("webdriver.chrome.driver", "/Users/romanmorozov/Desktop/ChromDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.get(url);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='own-loader-container']/div")));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        url = driver.getCurrentUrl();
        driver.findElement(By.xpath("//ul[@id='first-level-nav']/li/a/img")).click();

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@class='own-loader-container']/div")));
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getCurrentUrl().equals(url));
    }
    //

}
