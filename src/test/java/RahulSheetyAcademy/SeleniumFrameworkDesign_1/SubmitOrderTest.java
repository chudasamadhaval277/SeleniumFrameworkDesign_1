package RahulSheetyAcademy.SeleniumFrameworkDesign_1;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulSheetyAcademy.TestComponents.BaseTest;
import RahulSheetyAcademy.pageobjects.cartPage;
import RahulSheetyAcademy.pageobjects.checkoutPage;
import RahulSheetyAcademy.pageobjects.confirmationPage;
import RahulSheetyAcademy.pageobjects.landingPage;
import RahulSheetyAcademy.pageobjects.orderPage;
import RahulSheetyAcademy.pageobjects.productCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ADIDAS ORIGINAL";

	@Test(groups = {"purchase"}, dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws IOException {
		
		//String productName = "ZARA COAT 3";
		//String productName = "IPHONE 13 PRO";W
		//WebDriverManager.chromedriver().setup();
		
		
		productCatalog pc = lp.loginApplication(input.get("email"), input.get("password"));
		
		
		List<WebElement> products = pc.getProductList();
		
		
		pc.addProductToCart(input.get("product"));
		
		cartPage cp = pc.goToCartPage();
		
		
		Boolean match = cp.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		
		checkoutPage cop = cp.goToCheckout();
		
		cop.selectCounty("India");
		confirmationPage confP = cop.submitOrder();
		
		String confirmMessage = confP.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
	}
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		
		 productCatalog pc = lp.loginApplication("dhaval@gmail.com", "Dhaval@123");
		  
		 orderPage op =  pc.goToOrderPage();
		 Assert.assertTrue(op.verifyOrderDispaly(productName));
	}
	
	@DataProvider
	public Object[][] getData() {
		
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		
		map.put("email", "dhaval@gmail.com");
		map.put("password", "Dhaval@123");
		map.put("product", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		
		map1.put("email", "dhaval@gmail.com");
		map1.put("password", "Dhaval@123");
		map1.put("product", "ZARA COAT 3");
		
		return new Object[][] {
			{map}, {map1},
		};
		
//		return new Object[][] {
//			{"dhaval@gmail.com", "Dhaval@123", "ADIDAS ORIGINAL"},
//			{"dhaval@gmail.com", "Dhaval@123", "ZARA COAT 3"},
//		};
	}
}