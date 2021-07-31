package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class LearnTestNG {
	
	
	
	WebDriver driver;
	String browser;
	String url;
	
	@BeforeClass
	public void readConfig() {
		
		//BufferedReader//inputStream//FileReader//Scanner
		Properties prop=new Properties();
		
		try {
			InputStream input=new FileInputStream("C:\\Users\\Abdelkader\\Desktop\\AEK\\Selenium\\session5-TestNG\\src\\main\\java\\Config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used" +browser);
			url=prop.getProperty("url");
			

		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@BeforeMethod
	public void launchBrowser() {


		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Abdelkader\\Desktop\\AEK\\Selenium\\session5-TestNG\\driver\\chromedriver.exe");
			driver = new ChromeDriver();}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Abdelkader\\Desktop\\AEK\\Selenium\\session5-TestNG\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		

		// GO TO WEBSITE
		driver.get(url);

		driver.manage().window().maximize();

		// delete cookies
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

//	@Test (priority=1)
	public void loginTest1() throws InterruptedException {
		
		Assert.assertEquals(driver.getTitle(), "Login - iBilling1", "Wrong Page!!!!!");
		
		// Element Lib
				WebElement USER_NAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
				WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
				WebElement SUBMIT_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@type='submit']"));
				
				
				//loin data
		
				String loginId="demo@techfios.com";
				String password="abc123";
				
				USER_NAME_ELEMENT.sendKeys(loginId);
				PASSWORD_ELEMENT.sendKeys(password);
				SUBMIT_BUTTON_ELEMENT.click();
				
				WebElement DASHBOARD_TITLE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(), 'Dashboard')]"));
				
			 	Assert.assertEquals(DASHBOARD_TITLE_ELEMENT.getText(), "Dashboard", "Wrong Page!!!!!");
			 	
			 	
			 	
	}
	
	
	
	@Test (priority=2)
	public void loginTest(){
		
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Wrong Page!!!!!");
		
		// Element Lib
				WebElement USER_NAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
				WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
				WebElement SUBMIT_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@type='submit']"));
				
				
				//loin data
		
				String loginId="demo@techfios.com";
				String password="abc123";
				
				//test data
				String fullname="abdel AEK";
				String CompanyName="Google";
				String email="Techfios@gmail.com";
				String phone="2134567592";
				
				
				USER_NAME_ELEMENT.sendKeys(loginId);
				PASSWORD_ELEMENT.sendKeys(password);
				SUBMIT_BUTTON_ELEMENT.click();
				
				WebElement DASHBOARD_TITLE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(), 'Dashboard')]"));
				
			 	Assert.assertEquals(DASHBOARD_TITLE_ELEMENT.getText(), "Dashboard", "Wrong Page!!!!!");
			 	
			 	WebElement Customer_ELEMENT = driver.findElement(By.xpath("//*[@id='side-menu']/li[3]/a"));
			 	
			 	WebDriverWait wait =new WebDriverWait(driver , 5);
			 	wait.until(ExpectedConditions.visibilityOf(Customer_ELEMENT));
			 	
			 	Customer_ELEMENT.click();
			 	
			 	WebElement ADD_Customer_ELEMENT = driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a"));
			 	
			 	//wait with method
			 	waitForElement(driver , 5 , ADD_Customer_ELEMENT);
			 	
			 	ADD_Customer_ELEMENT.click();
			 	
			 	WebElement FULL_NAME_ELEMENT = driver.findElement(By.xpath("//input[@id='account']"));
			 	WebElement COMPANY_DROPDOW_ELEMENT = driver.findElement(By.xpath("//select[@id='cid']"));
			 	
			 	FULL_NAME_ELEMENT.sendKeys(fullname);
			 	
			 	//DropDown
			 	Select sel=new Select(COMPANY_DROPDOW_ELEMENT);
			 	sel.selectByVisibleText(CompanyName);
			 	
			 	WebElement EMAIL_ELEMENT = driver.findElement(By.xpath("//input[@id=\'email\']"));
			 	
			 	//Random Number
			 	Random rnd=new Random();
			 	int a=rnd.nextInt(9999);
			 	
			 	EMAIL_ELEMENT.sendKeys(a+email);
			 	
			 	
	}
	
	
	
	public void waitForElement(WebDriver driver, int waitTimeInSec, WebElement element) {
		WebDriverWait wait =new WebDriverWait(driver , waitTimeInSec);
	 	wait.until(ExpectedConditions.visibilityOf(element));
		
	}


	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
