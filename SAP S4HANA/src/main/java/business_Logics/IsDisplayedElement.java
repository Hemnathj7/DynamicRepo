package business_Logics;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component("LIC16627_PJT1003_PE_NLPa35279da-1a7f-46b9-a057-520df18bcf52")
public class IsDisplayedElement implements Nlp {
	@InputParams({ @InputParam(name = "WebElement", type = "org.openqa.selenium.WebElement")})
	@ReturnType(name = "boolean", type = "java.lang.Boolean")
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
		Boolean b=null;
		WebElement ele = (WebElement) attributes.get("WebElement");
		
		try {
			WebDriver driver = nlpRequestModel.getWebDriver();
			if(ele.isDisplayed()==true) {
				b=ele.isDisplayed();
				nlpResponseModel.setStatus(CommonConstants.pass);	
				nlpResponseModel.setMessage("Element is Displayed "+ele.isDisplayed());
			}
			else {
				b=ele.isDisplayed();
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Element is not Displayed "+ele.isDisplayed());
			}
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            log.info(exceptionAsString);
      	    nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Failed to identify element "+exceptionAsString);
			
		}
		nlpResponseModel.getAttributes().put("boolean",b );
		return nlpResponseModel;
	}
	

}