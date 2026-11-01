package practical9;

import java.nio.file.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ElementCounting {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.getBoolean("headless")) options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(System.getProperty("pageUrl", Path.of("web", "objects.html").toUri().toString()));
            int total = 0;
            String[] tags = {"a", "img", "button", "input"};
            String[] labels = {"Links", "Images", "Buttons", "Inputs"};
            int[] expected = {3, 2, 2, 4};
            for (int i = 0; i < tags.length; i++) {
                int count = driver.findElements(By.tagName(tags[i])).size();
                System.out.println(labels[i] + ": " + count);
                total += count;
                if (System.getProperty("pageUrl") == null && count != expected[i]) {
                    throw new IllegalStateException("Unexpected " + labels[i] + " count");
                }
            }
            System.out.println("Total objects in these four categories: " + total);
        } finally {
            driver.quit();
        }
    }
}
