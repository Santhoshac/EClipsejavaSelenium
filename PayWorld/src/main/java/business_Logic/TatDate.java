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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("LIC20069_PJT1001_PE_NLP9fbaafbd-db6a-48d1-b5cd-7a3bc9000420")
public class TatDate implements Nlp {
	@InputParams({ @InputParam(name = "No of Days", type = "java.lang.String"),
		@InputParam(name = "Pattern", type = "java.lang.String") })
	@ReturnType(name = "date", type = "java.lang.String")

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
		String addDate = (String) attributes.get("No of Days");
		String pattern = (String) attributes.get("Pattern");
		String tat="";
		try {
			LocalDate date = LocalDate.now().plusDays(Integer.valueOf(addDate));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		    tat = formatter.format(date);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Performed date operation "+tat);
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to perform date operation "+e);
		}

		nlpResponseModel.getAttributes().put("date", tat);
		return nlpResponseModel;
	}
}