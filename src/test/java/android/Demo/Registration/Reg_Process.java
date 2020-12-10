package android.Demo.Registration;

import android.Demo.Init.PageObject;
import android.Demo.Init.TestCommons;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Reg_Process {
	
	


	public void clickOnAlreadySignInButton(AndroidDriver<MobileElement> androidDriver)
	{
		androidDriver.findElementById("com.amazon.mShop.android.shopping:id/sign_in_button").click();
		
	}
	
	public void enterEmailAndClickOnContinue(AndroidDriver<MobileElement> androidDriver, String email)
	{	
		TestCommons.pause(10);
		androidDriver.findElementByXPath("//android.widget.EditText[@resource-id='ap_email_login']").sendKeys(email);
		System.err.print("reached till continue click button");
		androidDriver.findElementByXPath("//android.widget.Button[@resource-id='continue']").click();
	}
}
	