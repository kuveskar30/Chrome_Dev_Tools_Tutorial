import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.fetch.Fetch;
import org.openqa.selenium.devtools.v108.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v108.network.model.ErrorReason;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkFailedRequest {

	public static void main(String[] args) {
//by intentionally failing network we will check how application behaves
		WebDriverManager.chromedriver().setup();
		ChromeDriver d1 = new ChromeDriver();

		DevTools devToolsObject = d1.getDevTools();
		devToolsObject.createSession();

		Optional<List<RequestPattern>> patterns = Optional
				.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));

		devToolsObject.send(Fetch.enable(patterns, Optional.empty()));

		devToolsObject.addListener(Fetch.requestPaused(), request -> {
			devToolsObject.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
		});

		d1.get("https://rahulshettyacademy.com/angularAppdemo/");
		d1.findElement(By.cssSelector("button[routerlink*='library']")).click();
	}

}
