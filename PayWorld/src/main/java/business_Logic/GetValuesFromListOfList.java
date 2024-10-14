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
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("LIC20069_PJT1001_PE_NLP2e8e138c-2cd2-429e-ac90-c6d8c9d503f6")
public class GetValuesFromListOfList implements Nlp {
	@InputParams({ @InputParam(name = "Travellers_Details", type = "java.lang.String"),
			@InputParam(name = "Index1", type = "java.lang.Integer"),
			@InputParam(name = "Index2", type = "java.lang.Integer") })

	@ReturnType(name = "values", type = "java.lang.String")

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
		Integer Index1 = (Integer) attributes.get("Index1");
		Integer Index2 = (Integer) attributes.get("Index2");

		List<List<String>> li = new ArrayList<>();
		String values = null;
		try {
			String[] sp = travellers.split(";");
			for (int i = 0; i < sp.length; i++) {
				sp[i] = sp[i].substring(sp[i].indexOf('[') + 1, sp[i].indexOf(']'));
				String[] details = sp[i].split(",");
				List li1 = new ArrayList<>();
				for (int j = 0; j < details.length; j++) {
					li1.add(details[j]);
				}
				li.add(li1);
			}
			values = li.get(Index1).get(Index2);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Travellers details fetched successfully " + values);
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to fetch details from list " + e);
		}

		nlpResponseModel.getAttributes().put("values", values);
		return nlpResponseModel;
	}
}
