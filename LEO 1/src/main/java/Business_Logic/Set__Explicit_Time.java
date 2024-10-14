package Business_Logic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;

public class Set__Explicit_Time implements Nlp {
	@InputParams({@InputParam(name = "Time", type = "java.lang.Long")})
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		long time = 0;
		try {
			time=(long) programElementsInput.get("Time");
			AndroidDriver driver= nlpRequestModel.getAndroidDriver();
			WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(time));
			nlpResponseModel.setMessage("Passed");
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			nlpResponseModel.setMessage("Failed");
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		nlpResponseModel.getAttributes().put("Time", time);
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