package RahulSheetyAcademy.SeleniumFrameworkDesign_1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	
	public static void main(String[] args) {
		
		
		String productName = "ADIDAS ORIGINAL";
		//String productName = "ZARA COAT 3";
		//String productName = "IPHONE 13 PRO";
		//WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		//WebDriver driver = new ChromeDriver();
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("https://www.google.com/");
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.findElement(By.id("userEmail")).sendKeys("dhaval@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Dhaval@123");
		
		//driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		//driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();
		
		//driver.switchTo().alert().accept();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		
		
		//wait.until(ExpectedConditions.titleContains("Let's Shop"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		
		
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("window.scrollBy(0, 500)");
		
		wait.until(ExpectedConditions.elementToBeClickable(prod.findElement(By.cssSelector(".card-body button:last-of-type"))));
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//driver.navigate().refresh();
		
		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#toast-container"), "Product Added To Cart"));
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		cartProducts.stream().filter(cartProduct->cartProduct.getText().trim().equalsIgnoreCase(productName.trim()));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("india");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, 500)");
		
		driver.findElement(By.cssSelector(".actions a")).click();
		//driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
		
		
		
		driver.close();
		
	}
}
