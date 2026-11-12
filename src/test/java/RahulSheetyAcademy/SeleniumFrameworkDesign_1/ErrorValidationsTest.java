package RahulSheetyAcademy.SeleniumFrameworkDesign_1;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulSheetyAcademy.TestComponents.BaseTest;
import RahulSheetyAcademy.pageobjects.cartPage;
import RahulSheetyAcademy.pageobjects.productCatalog;


public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups = {"ErrorValidationsTest"})
	public void loginErrorValidation() {
		
		productCatalog pc = lp.loginApplication("dhaval@gmail.com", "Dhaval@@123");
		//Assert.assertEquals("aa", lp.getErrorMessage()); 
		
		Assert.assertEquals(lp.getErrorMessage(), "Incorrect email or password.");
	}
	
	@Test
	public void ProductErrorValidation() {
		
		String productName = "ZARA COAT 3";
		productCatalog pc = lp.loginApplication("dhaval@gmail.com", "Dhaval@123");
		List<WebElement> products = pc.getProductList();
		
		pc.addProductToCart(productName);
		
		cartPage cp = pc.goToCartPage();
		Boolean match = cp.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
