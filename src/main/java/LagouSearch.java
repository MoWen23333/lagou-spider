import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.List;

public class LagouSearch {
    public static void main(String[] args){
        //知道webdriver在哪里,设置webdriver路径
        System.setProperty("webdriver.chrome.driver", LagouSearch.class.getClassLoader().getResource("chromedriver").getPath());

        //创建webdriver
        WebDriver webDriver = new SafariDriver();
        //跳转页面
        webDriver.get("https://www.lagou.com/zhaopin/Java/?labelWords=label");
        //通过xpath选择span元素下"工作经验"ChromeDriver
        clickOption(webDriver, "工作经验", "不限");
        clickOption(webDriver, "学历要求", "本科");
        clickOption(webDriver, "融资阶段", "不限");
        clickOption(webDriver, "公司规模", "不限");
        clickOption(webDriver, "行业领域", "移动互联网");
        extractJobsByPagination(webDriver);

    }

    private static void extractJobsByPagination(WebDriver webDriver) {
        //解析页面元素
        //className去获取
        List<WebElement> jobElements = webDriver.findElements(By.className("con_list_item"));
        for (WebElement jobElement : jobElements) {
            WebElement moneyElement = jobElement.findElement(By.className("position")).findElement(By.className(("money")));
            String companyname = jobElement.findElement(By.className("company_name")).getText();
            System.out.println(companyname + " : " + moneyElement.getText());
        }

        WebElement nextPageBtn = webDriver.findElement(By.className("pager_next"));
        if(!nextPageBtn.getAttribute("class").contains("pager_next_disabled")){
            nextPageBtn.click();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            extractJobsByPagination(webDriver);
        }

    }

    private static void clickOption(WebDriver webDriver, String choseTitle, String optionTitle) {
        WebElement chosenElement = webDriver.findElement(By.xpath("//li[@class='multi-chosen']//span[contains(text(),'" + choseTitle + "')]"));
        //选择a元素下"应届毕业生"
        WebElement optionElement = chosenElement.findElement(By.xpath("../a[contains(text(),'" + optionTitle + " ')]"));
        //模拟点击
        optionElement.click();
    }
}
