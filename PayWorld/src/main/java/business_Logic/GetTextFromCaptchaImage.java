package business_Logic;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.stereotype.Component;

@Component("LIC20069_PJT1001_PE_NLP90d0336b-f047-4a86-92b1-4641df605ae2")
public class GetTextFromCaptchaImage implements Nlp {
	@InputParams({ @InputParam(name = "FilePath", type = "java.lang.String"),
			@InputParam(name = "ApiKey", type = "java.lang.String") })
	@ReturnType(name = "text", type = "java.lang.String")

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
		String filePath = (String) attributes.get("FilePath");
		String apiKey = (String) attributes.get("ApiKey");

		String responseText = "";
		try {
			// Read binary file and encode to Base64
			byte[] bodyBytes = readFileToBytes(filePath);

			// Send POST request with base64 encoded body
			Response response = RestAssured.given().header("apikey", apiKey.trim())
					.contentType("application/octet-stream") // Set content type as binary
					.body(bodyBytes).post("https://api.apilayer.com/image_to_text/upload");

			responseText = response.jsonPath().get("all_text");

			nlpResponseModel.setMessage("Fetched text from Image");
			nlpResponseModel.setStatus(CommonConstants.pass);

		} catch (Exception e) {
			nlpResponseModel.setMessage("Failed to Fetch text from Image " + e);
			nlpResponseModel.setStatus(CommonConstants.fail);
		}

		nlpResponseModel.getAttributes().put("text", responseText);
		return nlpResponseModel;
	}

	public static byte[] readFileToBytes(String filePath) {
		try {
			return Files.readAllBytes(Paths.get(filePath));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}