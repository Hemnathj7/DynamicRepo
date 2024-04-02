package business_Logics;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
@Component("LIC16627_PJT1003_PE_NLPe628da5d-dd26-42c0-815e-c302a9c766f6")
public class FutureDate implements Nlp {
	@InputParams({ @InputParam(name = "Plus Days", type = "java.lang.Long"),
		@InputParam(name = "Plus Months", type = "java.lang.Long"),
		@InputParam(name = "Day", type = "java.lang.String")})
	@ReturnType(name = "Date Generated", type = "java.lang.String")
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
		long day = (Long) attributes.get("Plus Days");
		long month = (Long) attributes.get("Plus Months");
		String week = (String) attributes.get("Day");
		String s="";
		
		try {
			LocalDate currentDate = LocalDate.now();

		    LocalDate firstDayOfNextMonth = currentDate.plusMonths(month).plusDays(day);

		    LocalDate nextMonthWorkingDay = firstDayOfNextMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(week)));

		    String pattern = "dd/MMM/yyyy";
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
 String formattedDate = nextMonthWorkingDay.format(formatter);
		  s=formattedDate;
		  nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("The Generated date is "+formattedDate);
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            log.info(exceptionAsString);
      	    nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Generated date "+exceptionAsString);
			
		}
		nlpResponseModel.getAttributes().put("Date Generated",s );
		return nlpResponseModel;
	}
	

}