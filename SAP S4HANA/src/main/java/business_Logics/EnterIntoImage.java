package business_Logics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("LIC16627_PJT1003_PE_NLP882c51af-1402-440b-ba3a-8c7544772770")
public class EnterIntoImage implements Nlp {
    @InputParams({@InputParam(name = "Image Stream", type = "java.io.InputStream"),@InputParam(name = "Target X", type = "java.lang.Integer"),@InputParam(name = "Target Y", type = "java.lang.Integer"),@InputParam(name = "Input", type = "java.lang.String")})
   
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
          InputStream imageStream = (InputStream) attributes.get("Image Stream");
          int targetX = (Integer) attributes.get("Target X");
          int targetY = (Integer) attributes.get("Target Y");
          String input = (String) attributes.get("Input");
          System.setProperty("java.awt.headless", "false");
         try
         {
        	  BufferedImage bufferedImage = ImageIO.read(imageStream);
        	    Screen screen = new Screen();
    	        Pattern imagePattern = new Pattern(bufferedImage);

   
		
		 screen.click(imagePattern.targetOffset(targetX, targetY));
         screen.type(input);	

  
      
       nlpResponseModel.setStatus(CommonConstants.pass);
		nlpResponseModel.setMessage("Successfully entered "+input);	
   
          
	        
         }
        catch(Exception e) {
        	StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            log.info(exceptionAsString);
      	    nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to perform action "+exceptionAsString);	
        }
          return nlpResponseModel;
      }
  } 