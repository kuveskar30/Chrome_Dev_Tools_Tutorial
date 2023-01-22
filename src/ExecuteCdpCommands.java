import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.emulation.Emulation;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExecuteCdpCommands {

	public static void main(String[] args) throws InterruptedException {

		// here we are nor using WebDriver as it doesen't have method of devtools
		// we have to use explicitly ChromeDriver or EdgeDriver for using devtools
		// as both are extending ChromiumDriver class
		WebDriverManager.chromedriver().setup();
		ChromeDriver d1 = new ChromeDriver();

		DevTools devToolsObject = d1.getDevTools();

		devToolsObject.createSession();
		Map<String, Object> deviceMetrics = new HashMap<String,Object>();
		deviceMetrics.put("width", 360);
		deviceMetrics.put("height", 680);
		deviceMetrics.put("deviceScaleFactor", 100);
		deviceMetrics.put("mobile", true);
		d1.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
		d1.get("https://rahulshettyacademy.com/");
		Thread.sleep(2000);
		d1.findElement(By.cssSelector(".navbar-toggle")).click();
		//it comes to default browesr size after execution of code
		Thread.sleep(15000);
//		d1.quit();
	}

}
