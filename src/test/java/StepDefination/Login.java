package StepDefination;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Login {

	public static WebDriver driver;
	public static Properties prop;
    @BeforeClass
	@Given("^Launch the browser and enter url$")
	public void Launch_the_browser_and_enter_url() throws IOException {
		FileInputStream ip = new FileInputStream("G:\\Rahane\\Amazon\\src\\test\\java\\Object\\Config.properties");
		prop = new Properties();
		prop.load(ip);
		if (prop.getProperty("Browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"G:\\Rahane\\Amazon\\src\\test\\java\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

		}
		driver.get("https://www.amazon.in/");
	}

	@Given("^User click on Sign In button$")
	public void user_click_on_Sign_In_button() {
		WebElement sign= driver.findElement(By.xpath("//a[@id='icp-nav-flyout']/following::a[1]"));
		Actions act= new Actions(driver);
		act.moveToElement(sign).click().build().perform();
	}

	@When("^User enter \"([^\"]*)\" and User enter \"([^\"]*)\"$")
	public void user_enter_and_User_enter(String UserID, String Password) {
		driver.findElement(By.xpath(prop.getProperty("Email"))).sendKeys(UserID);
		driver.findElement(By.xpath(prop.getProperty("Click"))).click();
		driver.findElement(By.xpath(prop.getProperty("Password"))).sendKeys(Password);
		

	}

	@When("^Click on Sign In button$")
	public void click_on_Sign_In_button() {
		driver.findElement(By.xpath(prop.getProperty("Submit_Button"))).click();
		
	}

	@Then("^Verify login$")
	public void verify_login() {
		if (driver.findElement(By.xpath(prop.getProperty("Verify_Link"))).isEnabled()) {
			System.out.println("Current User login is verified");
		}
		
	}
	

	@Given("^User serach the item$")
	public void user_serach_the_item() {
		driver.findElement(By.xpath(prop.getProperty("Search_Box"))).sendKeys("Bag");
		driver.findElement(By.xpath(prop.getProperty("Submit"))).click();
	}

	@When("^User click on item$")
	public void user_click_on_item() {
		driver.findElement(By.xpath(prop.getProperty("Bag"))).click();
	}

	@Then("^Add to cart$")
	public void add_to_cart() throws InterruptedException {
		List<String> alltab = new ArrayList<String>(driver.getWindowHandles()); 
		driver.switchTo().window(alltab.get(1));
		// WebElement element=driver.findElement(By.xpath(prop.getProperty("Add_to_Cart")));
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,500)", "");
				Thread.sleep(100);
				js.executeScript("document.getElementById('add-to-cart-button').click()");
				Thread.sleep(100);
				//driver.switchTo().window(alltab.get(1)).close();
				driver.switchTo().window(alltab.get(0));
				
				
			
		}
	@Given("^User click on cart button$")
	public void user_click_on_cart_button() throws InterruptedException  {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Thread.sleep(1000);
		js.executeScript("document.getElementById('nav-cart').click()");
		
	}

	@Given("^Verify your item added to the cart$")
	public void verify_your_item_added_to_the_cart() throws InterruptedException { 
		Thread.sleep(1000);
		if(driver.findElement(By.xpath(prop.getProperty("Verify_Item_In_cart"))).isDisplayed()) 
		{
			System.out.println("Selected Item is verified"); 
		}
	    
	}

	@Then("^Check quantity as per requirement$")
	public void check_quantity_as_per_requirement() {
		
		System.out.println(driver.findElement(By.xpath(prop.getProperty("value"))).getText());
	    
	}
	
	@Given("^User click on Procced To Buy button$")
	public void user_click_on_Procced_To_Buy_button()  {
		/*JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('sc-buy-box-ptc-button-announce').click()");*/
		
	    driver.findElement(By.xpath(prop.getProperty("Procced_To_Buy"))).click();
	}

	

	@When("^Verify Address Details$")
	public void Verify_Address_Details()  {
	    if(driver.findElement(By.xpath(prop.getProperty("Address"))).isDisplayed())
	    { 
	    	 WebElement address=driver.findElement(By.xpath(prop.getProperty("Address")));
	    	System.out.println(address.getText());
	    }
	}

	@Then("^Click on Deliver To Address button$")
	public void Click_on_Deliver_To_Address_button()  {
		driver.findElement(By.xpath(prop.getProperty("Delivery_Address"))).click();
	    
	}
	
	@Given("^User click on Payment method$")
	public void user_click_on_Payment_method()   {
		//Thread.sleep(1000);
		//List<WebElement>allRadios = driver.findElements(By.xpath("Payment_Option"));
		
		System.out.println("Appropriate Payment option selected"); 
	    
	}

	@When("^Add details for payment$")
	public void add_details_for_payment()  {
	    System.out.println("Payment details added "); 
	}

	@And("^Click on continue button$")
	public void click_on_continue_button() {
		System.out.println("Items are buy sucsessfully"); 
		
	    
	}
	@AfterClass
	@Then("^Close Browser$")
	public void Close_Browser()
	{
		driver.quit();
	}
		

	}


