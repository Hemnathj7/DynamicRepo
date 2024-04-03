package q.org.coverage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MobileScript {

	public static void main(String[] args) throws IOException {
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setPlatform(Platform.ANDROID);
		dc.setCapability(MobileCapabilityType.NO_RESET, true);
		//dc.setCapability("appPackage", "com.gamma.scan");
		//dc.setCapability("appActivity", "com.gamma.barcodeapp.ui.BarcodeCaptureActivity");
		AndroidDriver driver=new AndroidDriver(new URL("http://localhost:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.openNotifications();
	
		 String imagePath = "C:\\Users\\User\\Downloads\\SAP_GUI_Images\\Finish_SAP.png";

	     File imageFile = new File(imagePath);
	        // Convert the image to base64 String
	        byte[] imageBytes = java.nio.file.Files.readAllBytes(imageFile.toPath());
	        String imageBase64 = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);

	        // Create the argument Map for sending the script to the Appium server
	        Map<String, Object> argumentMap = new HashMap<>();
	        argumentMap.put("command", String.format(
	                "mobile: executeShellCommand",
	                String.format("am start -a android.media.action.IMAGE_CAPTURE -n com.example.myapp/com.example.myapp.activity.CameraActivity -e android.intent.extra.STREAM %s",
	                        imageBase64)
	        ));

	        // Execute the script to select the desired camera
	        ((JavascriptExecutor) driver).executeScript("mobile: shell", argumentMap);
	}
	  private static String encodeFileAsBase64(String imagePath) throws IOException {

	        byte[] fileData = new byte[(int) new File(imagePath).length()];

	        try (FileInputStream fis = new FileInputStream(imagePath)) {
	            fis.read(fileData);
	        }

	        return Base64.getEncoder().encodeToString(fileData);
	    }
}
