package sypne.assessment.test.sypne.assessment.test;

import java.awt.AWTException;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class SpyneTests {
	
	
	WebDriver driver;
	
	String url = "https://www.spyne.ai/image-upscaler";
	
	String resultURL= "https://www.spyne.ai/image-upscaler/result";
	
	String path = System.getProperty("user.dir");
	
	Random rand = new Random();
	
	@BeforeClass
	public void testSetup()
	{
	System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver.exe");
	driver=new ChromeDriver();

	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@Test(description="Test Case 1: Navigation")
	public void navigation() {
		
		driver.get(url);
		
		System.out.println("We are currently on the following URL" +driver.getCurrentUrl());
		
		Assert.assertEquals(url, driver.getCurrentUrl());

		
	}

	
	@Test(description="Test Case 2: File upload")
	public void fileUpload() throws InterruptedException, AWTException {
		
		driver.get(url);
		System.out.println("Page load");
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		//driver.navigate().refresh();
		
		By eleU= By.xpath("//input[@type='file']");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(eleU));
		
		System.out.println(path + "/testFiles/carPic.png");
		
		driver.findElement(eleU).sendKeys(path + "/testFiles/carPic.png");
		
		System.out.println(driver.getCurrentUrl());
		System.out.println("uploaded");
		wait.until(ExpectedConditions.urlContains("result"));
		System.out.println(driver.getCurrentUrl());
		
		Assert.assertEquals(resultURL,driver.getCurrentUrl());
		
				
	}
	
	@Test(description="Test Case 3: Invalid File upload")
	public void invalidFileUpload() {
		
		driver.get(url);
		System.out.println("Page load");
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		//driver.navigate().refresh();
		
		By eleU= By.xpath("//input[@type='file']");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(eleU));
		
		System.out.println(path + "/testFiles/carPic.png");
		
		driver.findElement(eleU).sendKeys(path + "/testFiles/carPic.xls");
		
		System.out.println(driver.getCurrentUrl());
		System.out.println("uploaded");
		wait.until(ExpectedConditions.urlContains("result"));
		System.out.println(driver.getCurrentUrl());
		
		Assert.assertEquals(resultURL,driver.getCurrentUrl());
		
		By processButton = By.xpath("//button[text()='Process']");
		
		Boolean checkForButtonEnabled =  driver.findElement(processButton).isEnabled();
		
		Assert.assertEquals(false, checkForButtonEnabled);
		
		//not working
		
		//check for process button to be disabled.
		
		// By oopsText = By.xpath("//p[text()='Opps..!!!']");
		
	}
	
	@Test(description="Test Case 4: Image upscaling")
	public void imageUpscaling() {
		
		driver.get(url);
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		
		By eleU= By.xpath("//input[@type='file']");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(eleU));
		
		System.out.println(path + "/testFiles/carPic.png");
		
		driver.findElement(eleU).sendKeys(path + "/testFiles/carPic.png");
		
		
		System.out.println(driver.getCurrentUrl());
		System.out.println("uploaded");
		wait.until(ExpectedConditions.urlContains("result"));
		System.out.println(driver.getCurrentUrl());
		
		//now on result page
		
		By processButton = By.xpath("//button[text()='Process']");
		
		wait.until(ExpectedConditions.elementToBeClickable(processButton));
		driver.findElement(processButton).click();
		
		//check for download button
		
		
		By continueWithEmail = By.xpath("//button[text()='Continue with Email']");
	
		wait.until(ExpectedConditions.elementToBeClickable(continueWithEmail));
		driver.findElement(continueWithEmail).click();
		
		By emailInputBox = By.xpath("//input[@name=\"emailId\"]");
		
		
		   
        // Generate random integers in range 0 to 999
        int randInt = rand.nextInt(1000);
		
		String randomEmail = randInt+ "shubham" + randInt + "@gmail.com"; 
		
		driver.findElement(emailInputBox).sendKeys(randomEmail);
		
		By nextButton = By.xpath("//button[@type=\"submit\"]");
		
		driver.findElement(nextButton).click();
		
		By nameInput = By.xpath("//input[@name=\"owner_name\"]");
		
		driver.findElement(nameInput).sendKeys("shubham");
		
		By continueButton = By.xpath("//button[@id=\"next\"]");
		
		driver.findElement(continueButton);
		
		By downloadButton = By.xpath("//input[@id=\"upload\"]/../following-sibling::button[contains(text(),\"Download\")]");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(downloadButton));
		
		//driver.findElement(downloadButton).click();		
		
		
	}
	
	@Test(description="Test Case 5: Basic UI validation")
	public void uiValidation() {
		
		driver.get(url);
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		
		By eleU= By.xpath("//input[@type='file']");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(eleU));
		
		System.out.println(path + "/testFiles/carPic.png");
		
		driver.findElement(eleU).sendKeys(path + "/testFiles/carPic.png");
		
		
		System.out.println(driver.getCurrentUrl());
		System.out.println("uploaded");
		wait.until(ExpectedConditions.urlContains("result"));
		System.out.println(driver.getCurrentUrl());
		
		//now on result page
		
		//check for download button
		// check for upload button
		//check Image output options JPEG, PNG, WEBP, 2X, 4x, 3x, slider
		
		By jpegOption = By.xpath("//p[text()='JPEG']");
		By pngOption = By.xpath("//p[text()='PNG']");
		By webpOption = By.xpath("//p[text()='WEBP']");
		By twoXOption = By.xpath("//span[text()='2' and text()='x']");
		By fourXOption = By.xpath("//span[text()='4' and text()='x']");
		By threeXOption = By.xpath("//span[text()='3' and text()='x']");
		By uploadButton = By.xpath("//input[@id=\"upload\"]/..");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(pngOption));
		wait.until(ExpectedConditions.visibilityOfElementLocated(jpegOption));
		wait.until(ExpectedConditions.visibilityOfElementLocated(webpOption));
		wait.until(ExpectedConditions.visibilityOfElementLocated(twoXOption));
		wait.until(ExpectedConditions.visibilityOfElementLocated(fourXOption));
		wait.until(ExpectedConditions.visibilityOfElementLocated(threeXOption));
		wait.until(ExpectedConditions.visibilityOfElementLocated(uploadButton));
		
		By processButton = By.xpath("//button[text()='Process']");
		
		wait.until(ExpectedConditions.elementToBeClickable(processButton));
		
		
		
	}
	
	@Test(description="Test Case 6: Download functionality")
	public void downloadFunction() {
		
		driver.get(url);
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		
		By eleU= By.xpath("//input[@type='file']");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(eleU));
		
		System.out.println(path + "/testFiles/carPic.png");
		
		driver.findElement(eleU).sendKeys(path + "/testFiles/carPic.png");
		
		
		System.out.println(driver.getCurrentUrl());
		System.out.println("uploaded");
		wait.until(ExpectedConditions.urlContains("result"));
		System.out.println(driver.getCurrentUrl());
		
		//now on result page
		
		By processButton = By.xpath("//button[text()='Process']");
		
		wait.until(ExpectedConditions.elementToBeClickable(processButton));
		driver.findElement(processButton).click();
		
		//check for download button and click
		
		
	}
	
	@Test(description="Test Case 7: Time consumed")
	public void timeConsumed() {
		
		
	}
	
	
/*
	@AfterClass
	public void afterClass()
	{
	driver.quit();
	}
*/
}
