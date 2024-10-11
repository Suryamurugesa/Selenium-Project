package RetailProject;


import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// Storing the product name in the String variable 
		String productname="ZARA COAT 3";
		
		// Initializing the chromeDriver with webDriver manager 
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver= new ChromeDriver();
		
	    // launching the URL 
		driver.get("https://rahulshettyacademy.com/client/");
		// Maximizing the window 
		driver.manage().window().maximize();
		// logging in using email and password 
		WebElement email = driver.findElement(By.xpath("//input[@placeholder='email@example.com']"));
		
		email.sendKeys("sooryamail99@gmail.com");
		
		WebElement password = driver.findElement(By.xpath("//input[@placeholder='enter your passsword']"));
		
		password.sendKeys("Surya@123");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(2000);
		// Here getting all the products list with giving common class name 
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		// Iterating each product and getting the product text and comparing it with our product name
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().
				equals(productname)).findFirst().orElse(null);
		
		System.out.println(prod.getText());
		// clicking the Add to cart button
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();	
		// Initializing the webDriver wait class
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		// waiting until the visibility of the "added to cart successfully" message displayed 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// clicking the cart button to check our products 
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click()	;
		// Here getting all the products listed in the cart 
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));	
		// Making sure if our added product is in the cart 
		boolean anyMatch = cartproducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productname));
		
		System.out.println(anyMatch);
		
		Thread.sleep(2000);
		// clicking the checkout button
		 driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		 // Here under payment section selecting the country field and clicking it 
		 WebElement country = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		 country.click();
		 // Giving country name to the field 
		 country.sendKeys("India");
		 // waiting until the country list dropDown populated
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	    //clicking the Appropriate country 
		 driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[2]")).click();
		 // Finally click the place order button
		 driver.findElement(By.cssSelector(".action__submit")).click();	
		 
		 Thread.sleep(3000);
		 // Getting the confirmation message via text and storing it in String 
		 String confirmmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		 
		 System.out.println(confirmmessage);
		 // closing the Browser 
		 driver.close();		
		
		
	}

}
