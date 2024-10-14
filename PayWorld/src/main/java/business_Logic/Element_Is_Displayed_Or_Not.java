package business_Logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.springframework.stereotype.Component;

@Component("LIC20069_PJT1001_PE_NLPddb0930d-4977-4004-8ef5-1004658dbd8f")
public class Element_Is_Displayed_Or_Not implements Nlp {
	@InputParams({ @InputParam(name = "xpath", type = "java.lang.String") })
	@ReturnType(name = "IsDisplayed", type = "java.lang.Boolean")

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

	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		String xpath = (String) attributes.get("xpath");
		WebDriver driver = nlpRequestModel.getAndroidDriver();
		Boolean IsDisplayed = null;
		Duration impli = driver.manage().timeouts().getImplicitWaitTimeout();
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			IsDisplayed = driver.findElement(By.xpath(xpath)).isDisplayed();
			if (IsDisplayed==true) {
				IsDisplayed = true;				
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element is Displayed on the page");
		} catch (Exception e) {
			IsDisplayed = false;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element is not Displayed on the page");

		}

		driver.manage().timeouts().implicitlyWait(impli);
		nlpResponseModel.getAttributes().put("IsDisplayed", IsDisplayed);
		return nlpResponseModel;
	}
}