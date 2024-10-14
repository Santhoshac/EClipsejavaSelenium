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

@Component("LIC20069_PJT1001_PE_NLPc2293502-2238-42a6-b6ca-a0fd4a8489b4")
public class Fetch_Travellers_Details_In_Detail implements Nlp {
	@InputParams({ @InputParam(name = "Travellers_Details", type = "java.lang.String") })
	@ReturnType(name = "detailsList", type = "java.util.List")

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
		List<List<String>> li = new ArrayList<>();
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
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Travellers details stored in the list");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Travellers details is not stored in the list " + e);
		}

		nlpResponseModel.getAttributes().put("detailsList", li);
		return nlpResponseModel;
	}
}