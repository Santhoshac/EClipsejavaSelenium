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
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("LIC20069_PJT1001_PE_NLP335f873b-e680-46fe-a419-80817b9520da")
public class No_Of_Traveller_Details implements Nlp {
	@InputParams({ @InputParam(name = "Travellers_Details", type = "java.lang.String") })
	@ReturnType(name = "detailsMap", type = "java.util.Map")

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
		Map<String, String> countMap=new LinkedHashMap<>();
		try {
			String[] tr = travellers.split(",");
			for(int i=0;i<tr.length;i++) {
				String[] count = tr[i].split(":");
				for (int j = 0; j < count.length; j++) {
					countMap.put(count[j], count[++j]);
				}
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Travellers details stored in key and value");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Travellers details is not stored in key and value "+e);
		}

		nlpResponseModel.getAttributes().put("detailsMap", countMap);
		return nlpResponseModel;
	}
}