package business_Logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component("LIC20069_PJT1001_PE_NLPcbae0c86-7cea-43bf-b6b1-ab721a62de57")
public class Add_The_Travellers implements Nlp {
	@InputParams({ @InputParam(name = "Travellers_Details", type = "java.lang.String") })
	// @ReturnType(name = "detailsMap", type = "java.util.Map")

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
		String travellers = (String) attributes.get("Travellers_Details");
		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		try {
			String[] tra = travellers.split(",");
			String[] pas = new String[3];
			Map<String, Integer> hm = new LinkedHashMap<>();
			for (int i = 0; i < tra.length; i++) {
				pas = tra[i].split(":");
				for (int j = 0; j < pas.length; j++) {
					hm.put(pas[j], Integer.valueOf(pas[++j]));
				}
			}
			for (String str : hm.keySet()) {
				switch (str) {
				case "Adult":
					for (int i = 2; i <= hm.get(str); i++) {
						driver.findElement(By.xpath(
								"//android.view.View[contains(@content-desc,'"+str+"')]/android.widget.Button[2]"))
								.click();
					}
					break;
				case "Child":
					for (int i = 1; i <= hm.get(str); i++) {
						driver.findElement(By.xpath(
								"//android.view.View[contains(@content-desc,'"+str+"')]/android.widget.Button[2]"))
								.click();
					}
					break;
				case "Infant":
					for (int i = 1; i <= hm.get(str); i++) {
						driver.findElement(By.xpath(
								"//android.view.View[contains(@content-desc,'"+str+"')]/android.widget.Button[2]"))
								.click();

					}
				}
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("No of Travellers are Added Successfully");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Add No of Travellers " + e);
		}

		// nlpResponseModel.getAttributes().put("detailsMap", countMap);
		return nlpResponseModel;
	}
}
