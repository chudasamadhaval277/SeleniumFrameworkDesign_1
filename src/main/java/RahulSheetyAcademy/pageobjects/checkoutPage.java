package RahulSheetyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulSheetyAcademy.AbstractComponents.AbstractComponent;

public class checkoutPage extends AbstractComponent {

	WebDriver driver;
	
	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
		@FindBy(css = "input[placeholder='Select Country']")
		WebElement country;
		
		@FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
		WebElement selectCountry;
		
		@FindBy(css = ".actions a")
		WebElement submit;
		
		By result = By.cssSelector(".ta-results");
		
		public void selectCounty(String coutryName) {
		
			country.sendKeys(coutryName);
			waitForElementToAppear(result);
			selectCountry.click();
			
			
		}
		
		public confirmationPage submitOrder() {
			submit.click();
			confirmationPage confP = new confirmationPage(driver);
			return confP;
		}
		
		
}
