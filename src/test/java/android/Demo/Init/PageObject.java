package android.Demo.Init;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PageObject {

    AndroidDriver<MobileElement> driver;

    public PageObject(AppiumDriver<MobileElement> androidDriver) {

        this.driver = (AndroidDriver<MobileElement>) androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);

    }

}