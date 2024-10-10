package RetailProject;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver= new ChromeDriver();
		
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.manage().window().maximize();
		
		WebElement email = driver.findElement(By.xpath("//input[@placeholder='email@example.com']"));
		
		email.sendKeys("sooryamail99@gmail.com");
		
		WebElement password = driver.findElement(By.xpath("//input[@placeholder='enter your passsword']"));
		
		password.sendKeys("Surya@123");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(2000);
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().
				equals("ZARA COAT 3")).findFirst().orElse(null);
		
		System.out.println(prod.getText());
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();	
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click()	;
		
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));	
		
		Stream<WebElement> cartitem = cartproducts.stream().filter(cart->cart.findElement(By.cssSelector(".cartSection h3")).getText().equals("ZARA COAT 3"));
		System.out.println(cartitem);
		
		
		
	}

}
