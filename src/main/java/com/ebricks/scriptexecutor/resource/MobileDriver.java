package com.ebricks.scriptexecutor.resource;

import com.ebricks.scriptexecutor.config.Execution;
import com.ebricks.scriptexecutor.config.ExecutionConfig;
import com.ebricks.scriptexecutor.finder.ElementFinder;
import com.ebricks.scriptexecutor.model.*;
import com.ebricks.scriptexecutor.model.Screen;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.model.UIElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.xml.sax.SAXException;
import java.time.Duration;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileDriver {

    private static MobileDriver instance;

    private AndroidDriver<MobileElement> driver;
    private AppiumDriverLocalService service;
    private int screenCount;

    public AndroidDriver<MobileElement> getDriver() {
        return driver;
    }

    private MobileDriver() throws IOException {

        startSession();
        createSession();
        screenCount = 1;
    }

    public static MobileDriver getInstance() throws IOException {
        if (instance == null)
            instance = new MobileDriver();
        return instance;
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void startSession() throws IOException {
        //Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(ExecutionConfig.getInstance().getAPPIUMPORT());
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        if (isServerRunnning(ExecutionConfig.getInstance().getAPPIUMPORT()))
            service.stop();
        service.start();
    }

    public void stopSession() {
        service.stop();
    }

    public boolean isServerRunnning(int port) {

        boolean runningState = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            runningState = true;
        } finally {
            serverSocket = null;
        }
        return runningState;
    }

    public void createSession() throws IOException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("udid", ExecutionConfig.getInstance().getDEVICE().getUdid());
        desiredCapabilities.setCapability("deviceName", ExecutionConfig.getInstance().getDEVICE().getUdid());
        desiredCapabilities.setCapability("platformName", ExecutionConfig.getInstance().getDEVICE().getPlatform());
        desiredCapabilities.setCapability("platformVersion", ExecutionConfig.getInstance().getDEVICE().getOs());
        desiredCapabilities.setCapability("app", System.getProperty("user.dir") + "/resources/" + ExecutionConfig.getInstance().getBUILD().getFILENAME());

        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        driver = new AndroidDriver<MobileElement>(new URL(service.getUrl().toString()), desiredCapabilities);
        //driver = new AndroidDriver<MobileElement>(new URL(  "http://127.0.0.1:"+ExecutionConfig.getInstance().getAPPIUMPORT() + "/wd/hub"), desiredCapabilities);
    }


    public void quit() {
        driver.quit();
        stopSession();
    }

    public void click(Step step) throws IOException, SAXException, ParserConfigurationException {
        TouchAction touchAction = new TouchAction(driver);

        Point2D.Double percentagePoint = ElementFinder.relativeXandY(step);
//        String bounds = ElementFinder.findBoundsInReplayUIElement(step.getUiElement(), driver.getPageSource());
        String bounds = step.getUiElement().getBounds();
        Point valuesOfPoint = ElementFinder.findValuesFromPercentage(percentagePoint, bounds);

        //int x = (int)((TapEvent)step.getEvent()).getX();
        //int y = (int)((TapEvent)step.getEvent()).getY();
        touchAction.tap(PointOption.point(valuesOfPoint.x, valuesOfPoint.y)).perform();
        //driver.findElement(By.xpath("//*[@text='" + uiElement.getText() + "']")).click();
    }

    public void back() {
        driver.navigate().back();
    }

    public void lock() {
        driver.lockDevice();
    }

    public void unlock() {
        driver.unlockDevice();
    }

    public void home() {
        driver.pressKeyCode(AndroidKeyCode.HOME);
    }

    public void launch() {
        driver.launchApp();
    }

    public void input(UIElement uiElement, String text) {
        driver.findElement(By.xpath("//*[@text='" + uiElement.getText() + "']")).sendKeys(text);
        //driver.switchTo().activeElement().sendKeys(text);
        driver.hideKeyboard();
    }

    public void doWait() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void takeScreenshot(Screen screen) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File targetFile = new File(ResultFolder.getPath() + "/screenshots/" + screen.getImage());
        FileUtils.copyFile(scrFile, targetFile);
    }

    public void getDom(Screen screen) throws IOException {
        FileUtils.write(new File(ResultFolder.getPath() + "/dom/" + screen.getDom()), driver.getPageSource());
    }

    public void swipe(Step step){
        SwipeEvent swipeEvent = (SwipeEvent)step.getEvent();
        Duration duration = Duration.ofMillis(swipeEvent.getTouchDuration());
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(swipeEvent.getStartX(), swipeEvent.getStartY()))
                .waitAction((WaitOptions.waitOptions(duration)))
                .moveTo(PointOption.point(swipeEvent.getEndX(), swipeEvent.getEndY()))
                .release()
                .perform();
    }

    public double getScreenSize(){
        return Math.sqrt(Math.pow(driver.manage().window().getSize().getWidth(), 2) +
                Math.pow(driver.manage().window().getSize().getHeight(), 2));
    }
}
