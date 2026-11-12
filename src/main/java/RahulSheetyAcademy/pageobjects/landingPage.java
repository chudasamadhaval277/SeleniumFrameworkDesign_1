package RahulSheetyAcademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulSheetyAcademy.AbstractComponents.AbstractComponent;

public class landingPage extends AbstractComponent {

	WebDriver driver;
	
	public landingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Factory Design pattern
	
	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//ng-tns-c4-7 toast-message ng-star-inserted	
	//ng-tns-c4-7 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
	
	public productCatalog loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		productCatalog pc = new productCatalog(driver);
		return pc;
	}
	
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo(String url) {
		driver.get(url);
	}

}
