package android.Demo.Registration;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Reg_Verification {
	
	public boolean verifyValdiationMessage(AndroidDriver<MobileElement> driver,String validationMessage)
	{
		try {
			if(driver.findElementByXPath("//*[contains(@text,'"+validationMessage+"')]").isDisplayed())
			{
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return true;
		}
	}

}
