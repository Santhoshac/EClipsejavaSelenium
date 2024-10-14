package Business_Logic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;

public class Wait_till_element_is_invisible implements Nlp {
	@InputParams({ @InputParam(name = "Xpath", type = "java.lang.String"),
			@InputParam(name = "Implicit Wait", type = "java.lang.Long"),
			@InputParam(name = "Explicit Wait", type = "java.lang.Long") })
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		long implicitWait = 0;
		long explicitWait = 0;
		String xpath = null;
		AndroidDriver driver=null;
		try {
			implicitWait = (long) programElementsInput.get("Implicit Wait");
			explicitWait = (long) programElementsInput.get("Explicit Wait");
			xpath = (String) programElementsInput.get("Xpath");
			driver = nlpRequestModel.getAndroidDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(xpath))));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
			nlpResponseModel.setMessage("Completed Wait for given Element to be Invisible");
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
			nlpResponseModel.setMessage("Completed Wait for given Element to be Invisible");
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			nlpResponseModel.setMessage("Wait till Element is Invisible is failed");
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
//		nlpResponseModel.getAttributes().put("Time", time);
		return nlpResponseModel;
	}

	@Override
	public List<String> getTestParameters() throws NlpException {
		List<String> params = new ArrayList<>();
		return params;
	}

	@Override
	public StringBuilder getTestCode() throws NlpException {
		StringBuilder sb = new StringBuilder();
		return sb;
	}
}