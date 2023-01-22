
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.emulation.Emulation;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetGeolocationsMethod {


	public static void main(String[] args) throws InterruptedException{
		
	WebDriverManager.chromedriver().setup();
	ChromeDriver d1 = new ChromeDriver();
	
	DevTools devToolsObject = d1.getDevTools();
	devToolsObject.createSession();
	
	devToolsObject.send(Emulation.setGeolocationOverride(
			Optional.of(40),
			Optional.of(3),
			Optional.of(1)));
	d1.get("https://google.com");
	d1.findElement(By.name("q")).sendKeys("netflix",Keys.ENTER);
//	d1.get("https://mylocation.org/");
	
	//at bottom of page update location option is present on clicking on that 
	//location is getting updated
	d1.findElement(By.cssSelector("update-location")).click();
	Thread.sleep(3000);
	d1.findElements(By.cssSelector("h3")).get(0).click();
	
	}

}
