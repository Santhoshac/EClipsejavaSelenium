package Business_Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;




public class Get_Width_of_Element implements Nlp {
	@InputParams({@InputParam(name = "Element", type = "org.openqa.selenium.WebElement")})
	@ReturnType(name = "width", type = "java.lang.Long")
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		long width = 0;
		try {
			WebElement ele=(WebElement) programElementsInput.get("Element");
			width=ele.getSize().getWidth();
			nlpResponseModel.setMessage("Width of the element is "+width);
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			nlpResponseModel.setMessage("Failed");
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		nlpResponseModel.getAttributes().put("width", width);
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