import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v107.network.Network;
import org.openqa.selenium.devtools.v107.network.model.Request;
import org.openqa.selenium.devtools.v107.network.model.Response;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkLogActivity {

	public static void main(String[] args) {
		
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver d1 = new ChromeDriver();
		
		DevTools devToolsObject = d1.getDevTools();
		devToolsObject.createSession();
		
		//first we need to enable network tab
		devToolsObject.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		//catch request when it is sent
		//request and response are events addListener keeps listening to these events
		devToolsObject.addListener(Network.requestWillBeSent(), request -> {
			Request req = request.getRequest();
			
//			System.out.println(req.getUrl());
		});
		
		//catch response when it is received
		devToolsObject.addListener(Network.responseReceived(), response -> {
			Response res = response.getResponse();
			System.out.println(res.getUrl());
//			System.out.println(res.getStatus());
			if(res.getStatus() > 400) {
				System.out.println(res.getUrl() + " is failing with status code= "+ res.getStatus());
			}
		});
		
		d1.get("https://rahulshettyacademy.com/angularAppdemo/");
		d1.findElement(By.cssSelector("button[routerlink*='library']")).click();
		

	}

}
