/**
 * 
 */
package android.Demo.Init;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * @author Ankit P
 *
 */
public class TestCommons {

	public static void type(AndroidDriver<MobileElement> driver, MobileElement element, String text) {

		element.clear();

		hideKeyboard(driver);

		element.sendKeys(text);

		hideKeyboard(driver);

		pause(1);
	}

	public static void hideKeyboard(AndroidDriver<MobileElement> driver) {
		try {
			//driver.hideKeyboard();
		} catch (Exception e) {
			System.err.println("Keyboard Hidden Already");
		}
	}

	public static int getRandomNumber(int maxNum) {
		return new Random().nextInt(maxNum) + 1;
	}

	public static void pause(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			System.err.println("Error in Thread - Wait");
			e.printStackTrace();
		}
	}

	public static void addLabel(AndroidDriver<MobileElement> driver, String msg) {

		if (AppiumInit.isAddLabel)
		{
			//driver.label(msg);
		}
		else {
			TestCommons.log(msg);
		}

	}

	public static String generateRandomChars(int length) {
		String random = RandomStringUtils.randomAlphabetic(length);
		return random;
	}
	
	public static String generateRandomNum(int length) {
		String random = RandomStringUtils.randomNumeric(length);
		return random;
	}
	
	public static int randBetween(int start, int end) {
		  return start + (int) Math.round(Math.random() * (end - start));
	}
	
	public static String getTestDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c= Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,7);
        String newDate=sdf.format(c.getTime());
		return newDate;
	}
	
	
	public static boolean isElementDisplayed(WebDriver androidDriver, MobileElement element) {
		try {
			if(element.isDisplayed())
			{
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String getCurrentTimeStampString() {

		java.util.Date date = new java.util.Date();

		SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmssSS");
		TimeZone timeZone = TimeZone.getDefault();
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(timeZone.getOffset(date.getTime()), "GMT"));
		sd.setCalendar(cal);
		return sd.format(date);
	}
	
	public static void navigateBack(AndroidDriver<MobileElement> androidDriver) {
		pause(3);
		TestCommons.log("Navigate back.");
		TestCommons.addLabel(androidDriver, "Navigate back.");
		androidDriver.navigate().back();
		pause(6);
	}
	
	public static void logVerification(String log)
	{
		System.out.println(""+log+"</br>");
		Reporter.log("<b style='color:DarkCyan;'>"+log+"</b></br>");
	}
	
	public static void logCase(String log)
	{
		System.out.println(""+log+"</br>");
		Reporter.log("<h4 style='color:Purple;'>"+log+"</h4>");
	}
	
	public static void logStep(String log)
	{
		System.out.println("<br>"+log+"</br>");
		Reporter.log("<br><b>"+log+"</b></br>");
	}
	
	public static void log(String log)
	{
		System.out.println(log+"</br>");
		Reporter.log(log+"</br>");
	}
	
	public static void makeScreenshot(WebDriver driver, String screenshotName) {

		WebDriver augmentedDriver = new Augmenter().augment(driver);

		/* Take a screenshot */
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		String nameWithExtention = screenshotName + ".png";

		/* Copy screenshot to specific folder */
		try {
			/*
			 * String reportFolder = "target" + File.separator +
			 * "failsafe-reports" + File.separator + "firefox" + File.separator;
			 */
			String reportFolder = "test-output" + File.separator;
			String screenshotsFolder = "screenshots";
			File screenshotFolder = new File(reportFolder + screenshotsFolder);
			if (!screenshotFolder.getAbsoluteFile().exists()) {
				screenshotFolder.mkdir();
			}
			FileUtils.copyFile(screenshot,
					new File(screenshotFolder + File.separator + nameWithExtention).getAbsoluteFile());
		} catch (IOException e) {
			log("Failed to capture screenshot: " + e.getMessage());
		}
		log(getScreenshotLink(nameWithExtention, nameWithExtention)); // add
		
	}
	
	public static String getScreenshotLink(String screenshot_name, String link_text) {

		log("<br><Strong><font color=#FF0000>--Failed</font></strong>");
		return "<a href='../test-output/screenshots/" + screenshot_name + "' target='_new'>" + link_text + "</a>";
	}
	
	
}
