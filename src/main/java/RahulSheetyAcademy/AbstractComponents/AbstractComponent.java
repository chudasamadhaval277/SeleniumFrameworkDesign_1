package RahulSheetyAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulSheetyAcademy.pageobjects.cartPage;
import RahulSheetyAcademy.pageobjects.orderPage;

public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css= "[routerlink*='orders']")
	WebElement orderHeader;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	
	public void waitForElementToAppear(By FindBy) {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public cartPage goToCartPage() {
		
		cartHeader.click();
		cartPage cp = new cartPage(driver);
		return cp;
		
	}
	
	public orderPage goToOrderPage() {
		
		
		waitForElementToDisappear(spinner);
		orderHeader.click();
		
		orderPage op = new orderPage(driver);
		return op;
		
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
}
