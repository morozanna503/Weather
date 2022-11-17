import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

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
        String faringeit = "°F";
        String expectedResult2 = "°F";

        driver.get(url);
        Thread.sleep(10000);

        WebElement menuTemp = driver.findElement(
                By.xpath("//div[text()='Imperial: °F, mph']")
        );
        menuTemp.click();
        Thread.sleep(5000);

        WebElement checkF = driver.findElement(
                By.xpath("//span[@class='heading']")
        );

        Boolean isTempFar = checkF.getText().contains("°F");

        Assert.assertTrue(isTempFar);

        driver.quit();
    }

    //TC_11_03
    //1.  Открыть базовую ссылку
    //2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential
    // for the site to work. We also use non-essential cookies to help us improve our services.
    // Any data collected is anonymised. You can allow all cookies or manage them individually.”
    //3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
    @Ignore
    @Test
    public void testCheckBelowPageContainTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/romanmorozov/Desktop/ChromDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement text = driver.findElement(
                By.xpath("")
        );
        text.click();
        Thread.sleep(5000);



        driver.quit();
    }
}
