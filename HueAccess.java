package com.redeye.controller;
/**
 * 
 */

/**
 * @author abisasok
 *
 */

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
public class HueAccess {
public void access() throws InterruptedException, AWTException {
	Map<String, List<String>> empDataMap =new HashMap<String, List<String>>();
	DesiredCapabilities capabilites = DesiredCapabilities.chrome();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--headless");
	capabilites.setCapability(ChromeOptions.CAPABILITY, options);
	String currentPath = System.getProperty("user.dir");
	System.setProperty("webdriver.chrome.driver", currentPath+"//chromedriver.exe");
	WebDriver driver = new ChromeDriver(capabilites);
	driver.get("http://192.168.41.222:8080/internal-tool-front/hue/login");
	Thread.sleep(1000);
	driver.findElement(By.id("userId")).sendKeys("anandh.s");
	driver.findElement(By.id("password")).sendKeys("Test@123");
	driver.findElement(By.id("login-btn")).click();
	Thread.sleep(100);
	driver.get("http://192.168.41.222:8080/internal-tool-front/hue/conversion/ac/hrtool/attendance/attendancereport/attendancereport/index?sid=ConversionAcattendanceReport");
	Thread.sleep(1000);
	WebElement singleSelectWebElement = driver.findElement(By.id("day-select"));
	singleSelectWebElement.findElement(By.className("wap-icon-angle-down")).click();
	singleSelectWebElement.findElement(By.cssSelector("#day-select li:nth-child(1)")).click();
	Thread.sleep(1000);
	driver.findElement(By.id("load-button_target")).click();
	Thread.sleep(1000);
	WebElement labLead = driver.findElement(By.name("labLeader"));
	Thread.sleep(100);
	labLead.findElement(By.className("button-right-icon")).click();
	driver.findElement(By.cssSelector("#status-grid_header-filter-filter  > ul > li:nth-of-type(76)")).click();
	WebElement gridData = driver.findElement(By.id("status-grid_body"));
	for(int i = 0;i<gridData.findElements(By.className("div-table-row")).size();i++){
		List<String> empDataList = new ArrayList<String>();
		String info[] = gridData.findElements(By.className("div-table-row")).get(i).getText().split("\\r?\\n");
		for(int j=1;j<info.length;j++){
			empDataList.add(info[j].trim());
		}
		empDataMap.put(info[0],empDataList);
	}
	for(Map.Entry<String, List<String>> entry : empDataMap.entrySet()){
		String empId = entry.getKey();
		List<String> details = entry.getValue();
		System.out.println("Employee Id : " +empId);
		System.out.println("details : " +details);
		
	}
	driver.close();
}
}
