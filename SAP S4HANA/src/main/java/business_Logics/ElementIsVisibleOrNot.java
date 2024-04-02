package business_Logics;

import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import com.tyss.optimize.common.util.CommonConstants;
import org.springframework.stereotype.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component("LIC16627_PJT1003_PE_NLPcd37eab3-fcaa-46d2-95bd-394aa69b23dd")
public class ElementIsVisibleOrNot implements Nlp {
	@InputParams({ @InputParam(name = "Xpath", type = "java.lang.String") })
	@ReturnType(name = "isNotDisplayed", type = "java.lang.Boolean")

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
		String xpath = (String) attributes.get("Xpath");
		WebDriver driver = nlpRequestModel.getWebDriver();
		Duration duration = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

		Boolean isNotDisplayed = null;
		try {
			driver.findElement(By.xpath(xpath)).isDisplayed();
			isNotDisplayed = true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("The Element is displayed");

		} catch (Exception e) {
			isNotDisplayed = false;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element Is Not Displayed");
		} finally {
			driver.manage().timeouts().implicitlyWait(duration);
		}
		nlpResponseModel.getAttributes().put("isNotDisplayed", isNotDisplayed);
		return nlpResponseModel;
	}
}
