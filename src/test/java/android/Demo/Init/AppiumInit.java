package android.Demo.Init;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import android.Demo.Registration.Reg_Process;
import android.Demo.Registration.Reg_Verification;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class AppiumInit {
	
	
	public Reg_Process reg_Process;
	public Reg_Verification reg_Verification;
	
	public AndroidDriver<MobileElement> androidDriver;
	
	public static String name="";
	
	public static boolean isAddLabel = true;
	
	public static File apkFile = new File("App/Amazon_shopping.apk");
	
	public static String platformVersion = "10";
	
	
	@BeforeMethod(alwaysRun=true)
	public void setUp(Method method,ITestContext context)
	{
		name = method.getName();
		
		String permission = context.getCurrentXmlTest().getParameter("autoGrantPermissions");
		
		URL remote_grid = null ;
		try {
			remote_grid = new URL("http://0.0.0.0:4723/wd/hub");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("app", apkFile.getAbsolutePath());
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", platformVersion);
		caps.setCapability("deviceName", "emulator-5554");
		caps.setCapability("fullReset", "true");
		caps.setCapability("automationName", "Appium");
		caps.setCapability("autoGrantPermissions", permission);
		caps.setCapability("newCommandTimeout", 900);
		caps.setCapability("relaxed-security", true);
		caps.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
		/*caps.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		caps.setCapability("appActivity", "com.amazon.mShop.sso.SigninPromptActivity");*/
		
		
		System.out.println("--- Before Connection---");
		
		
		androidDriver = new AndroidDriver<MobileElement>(remote_grid, caps);
		System.out.println("Android driver is intialized");
		androidDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("--- Connection establish---");
		
		
	
		
		//Reg -> Reg
		reg_Process = new Reg_Process();
		reg_Verification = new Reg_Verification() ;
		
		TestCommons.log("--------------------------------------------------------------------------");
		TestCommons.log("------------------- Initiating the Mobile App Session -------------------");
		TestCommons.log("--------------------------------------------------------------------------");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult testResult)
	{
		

		
		String testName = testResult.getName();
		
			
			if (!testResult.isSuccess()) {
				
			
				//System.err.println("=====Creating Log=======");
				
				System.err.println("=====Log Completed=======");
				
				/* Print test result to Jenkins Console */
				System.out.println();
				System.out.println("TEST FAILED - " + testName);
				System.out.println();
				System.out.println("ERROR MESSAGE: " + testResult.getThrowable());
				System.out.println("\n");
				Reporter.setCurrentTestResult(testResult);

				/* Make a screenshot for test that failed */
				String screenshotName = TestCommons.getCurrentTimeStampString() + testName;

				System.out.println("========++++++" + TestCommons.getCurrentTimeStampString() + "========++++++++++");
				Reporter.log("<br> <b>Please look to the screenshot - </b>");
				TestCommons.makeScreenshot(androidDriver, screenshotName);
			} else {
				System.out.println("TEST PASSED - " + testName + "\n"); // Print
																		// test
																		// result
																		// to
																		// Jenkins
																		// Console
			}

		
			androidDriver.quit();
		   		    
	}

}
