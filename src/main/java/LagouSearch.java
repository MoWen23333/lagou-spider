import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class LagouSearch {
    public static void main(String[] args){
        //知道webdriver在哪里,设置webdriver路径
        System.setProperty("webdriver.chrome.driver", LagouSearch.class.getClassLoader().getResource("chromedriver").getPath());

        //创建webdriver
        WebDriver webDriver = new SafariDriver();
        webDriver.get("https://www.lagou.com/shanghai-zhaopin/Java/?labelWords=label");
    }
}
