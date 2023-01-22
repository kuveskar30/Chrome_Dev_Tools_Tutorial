import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.emulation.Emulation;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileEmulatorTest {

	public static void main(String[] args) throws InterruptedException {

		// here we are nor using WebDriver as it doesen't have method of devtools
		// we have to use explicitly ChromeDriver or EdgeDriver for using devtools
		// as both are extending ChromiumDriver class
		WebDriverManager.chromedriver().setup();
		ChromeDriver d1 = new ChromeDriver();

		DevTools devToolsObject = d1.getDevTools();

		devToolsObject.createSession();
		devToolsObject.send(Emulation.setDeviceMetricsOverride(360, 680, 0, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		d1.get("https://rahulshettyacademy.com/");
		//it comes to default browesr size after execution of code
		Thread.sleep(15000);
//		d1.quit();
	}

}
