package Business_Logic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;


public class Swipe_and_Get_Attribute_from_List_of_WebElement implements Nlp {
	@InputParams({ @InputParam(name = "Direction(Up/Down/Right/Left)", type = "java.lang.String"),
			@InputParam(name = "Refference Element", type = "java.lang.String"),
			@InputParam(name = "Actual Element", type = "java.lang.String"),
			@InputParam(name = "Attribute", type = "java.lang.String") })
	@ReturnType(name = "List", type = "java.util.List")

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
		ArrayList<String> list = null;
		try {
			AndroidDriver driver = nlpRequestModel.getAndroidDriver();
			String attribute = (String) attributes.get("Attribute");
			String refElementXpath = (String) attributes.get("Refference Element");
			String actualElememtXpath = (String) attributes.get("Actual Element");
			WebElement refElement = driver.findElement(By.xpath(refElementXpath));
			String direction = (String) attributes.get("Direction(Up/Down/Right/Left)");
			Rectangle rect = refElement.getRect();
			int refWidth = rect.getWidth();
			int refHeight = rect.getHeight();
			int refX = rect.getX();
			int refY = rect.getY();
			int midX = refX + (refWidth / 2);
			int midY = refY + (refHeight / 2);
			int fromY;
			int toY;
			int fromX;
			int toX;

			if (direction.toLowerCase().equals("up")) {
				fromX = midX;
				toX = midX;
				fromY = midY + (refHeight / 2) / 2;
				toY = midY - (refHeight / 2) / 2;
			} else if (direction.toLowerCase().toLowerCase().equals("down")) {
				fromX = midX;
				toX = midX;
				fromY = midY - (refHeight / 2) / 2;
				toY = midY + (refHeight / 2) / 2;
			} else if (direction.toLowerCase().toLowerCase().equals("right")) {
				fromX = (int) (midX + (refWidth / 2) * 0.8);
				toX = (int) (midX - (refWidth / 2) * 0.8);
				fromY = midY;
				toY = midY;
			} else {
				fromX = (int) (midX - (refWidth / 2) * 0.8);
				toX = (int) (midX + (refWidth / 2) * 0.8);
				fromY = midY;
				toY = midY;
			}
			String beforeSwipe = "";
			String afterSwipe = "";
			TreeSet<String> set = new TreeSet<String>();
			List<WebElement> actualElementList = driver.findElements(By.xpath(actualElememtXpath));
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			for (int i = 0; i <= 50; i++) {
				beforeSwipe = actualElementList.get(actualElementList.size() - 1).getAttribute(attribute).replace(" ",
						"\n");
				for (int j = 0; j < actualElementList.size(); j++) {
					set.add(actualElementList.get(j).getAttribute(attribute).replace("\n", " "));
				}
				Sequence swipe = new Sequence(finger, 1);
				swipe.addAction(
						finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), fromX, fromY));
				swipe.addAction(finger.createPointerDown(0));
				swipe.addAction(
						finger.createPointerMove(Duration.ofMillis(900), PointerInput.Origin.viewport(), toX, toY));
				swipe.addAction(finger.createPointerUp(0));
				driver.perform(Arrays.asList(swipe));
				actualElementList = driver.findElements(By.xpath(actualElememtXpath));
				afterSwipe = actualElementList.get(actualElementList.size() - 1).getAttribute(attribute).replace(" ",
						"\n");
				if (afterSwipe.equals(beforeSwipe)) {
					break;
				}
			}
			list = new ArrayList<String>(set);
			nlpResponseModel.setMessage("Successfully Fetched the attribute from given element " + list);
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			nlpResponseModel.setMessage("Failled to Fetch the Attribute Value");
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		nlpResponseModel.getAttributes().put("List", list);
		return nlpResponseModel;
	}
}