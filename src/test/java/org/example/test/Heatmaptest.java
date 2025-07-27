package org.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Heatmaptest {
    @Test
    public void testHeatmapClickMap() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        // 1. Open the Heatmap link
        driver.get("https://app.vwo.com/#/test/ab/13/heatmaps/1?token=eyJhY2NvdW50X2lkIjo2NjY0MDAsImV4cGVyaW1lbnRfaWQiOjEzLCJjcmVhdGVkX29uIjoxNjcxMjA1MDUwLCJ0eXBlIjoiY2FtcGFpZ24iLCJ2ZXJzaW9uIjoxLCJoYXNoIjoiY2IwNzBiYTc5MDM1MDI2N2QxNTM5MTBhZDE1MGU1YTUiLCJzY29wZSI6IiIsImZybiI6ZmFsc2V9&isHttpsOnly=1&accountId=666400"); // Replace with actual URL

        // 2. Click on the variation link (open new tab/window)
        WebElement variationLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Variation Name']")));
        actions.moveToElement(variationLink).click().perform();

        // 3. Switch to the new window/tab
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // 4. Wait for iframe and switch to it
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("heatmap-iframe"))); // Replace ID as needed

        // 5. Verify Click Map button is clickable
        WebElement clickMap = wait.until(ExpectedConditions.elementToBeClickable(By.id("click-map"))); // Replace locator
        clickMap.click();

        // Optionally assert something
        Assert.assertTrue(clickMap.isDisplayed());

        // Cleanup
        driver.quit();
    }

}
