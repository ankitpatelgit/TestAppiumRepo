package android.Demo.Registration;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import android.Demo.Init.AppiumInit;
import android.Demo.Init.TestCommons;
import android.Demo.Utility.TestData;



public class Reg_Index extends AppiumInit{
	
	@Test
	public void reg() {
		
		int step=1;
		//SoftAssert softAssert = new SoftAssert();
		
		TestCommons.logCase("******** Test case ID: Registration");
		
		String invalidEmail = TestData.email;
		String validationMessage= TestData.validationMessage;
		
		
		TestCommons.logStep("Step "+(step++)+". Click on already sign in button.");
		reg_Process.clickOnAlreadySignInButton(androidDriver);
		
		TestCommons.logStep("Step "+(step++)+". Enter invalid email and click on continue:- "+invalidEmail);
		reg_Process.enterEmailAndClickOnContinue(androidDriver, invalidEmail);
		
		
		if(reg_Verification.verifyValdiationMessage(androidDriver, validationMessage))
		{
			TestCommons.logStep("Passed");
			Assert.assertTrue(true, "User is getting the validation message for invalid input.");
			TestCommons.logStep("User is getting the validation message for invalid input.");
		}else {
			
			TestCommons.logStep("Failed");
			Reporter.log("<br> <b>Please look to the screenshot - </b>");
			TestCommons.makeScreenshot(androidDriver, AppiumInit.name+TestCommons.getCurrentTimeStampString());
			Assert.assertTrue(false, "User is not getting the validation message for invalid input.");
			TestCommons.logStep("User is not getting the validation message for invalid input.");
		}
	}

}
