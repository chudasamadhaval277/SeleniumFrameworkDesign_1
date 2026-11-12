package RahulSheetyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulSheetyAcademy.AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutEle;
	
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
		return match;
	
	}
	
	public checkoutPage goToCheckout() {
		checkoutEle.click();
		
		checkoutPage cop = new checkoutPage(driver);
		return cop;
	}

}
