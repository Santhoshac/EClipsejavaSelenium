package business_Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC20069_PJT1001_PE_NLPc72210db-a77a-4172-a632-539c4661e019")
public class Check_Element_is_Displayed implements Nlp {

	@InputParams({ @InputParam(name = "xpath", type = "String") })
	@ReturnType(name = "isDisplayed", type = "java.lang.Boolean")

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
		WebDriver driver = nlpRequestModel.getWebDriver();
		Boolean isDisplayed = true;
		try {

			if (driver.findElement(By.xpath(xpath)).isDisplayed() == true) {
				isDisplayed = true;
				nlpResponseModel.setStatus("PASS");
				nlpResponseModel.setMessage("The element is Displayed");
			}
		}

		catch (NoSuchElementException e) {
			isDisplayed = false;
			nlpResponseModel.setStatus("PASS");
			nlpResponseModel.setMessage("The element is not Displayed");
		}

		nlpResponseModel.getAttributes().put("isDisplayed", isDisplayed);
		return nlpResponseModel;
	}
}
