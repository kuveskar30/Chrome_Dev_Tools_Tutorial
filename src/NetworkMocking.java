import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.fetch.Fetch;
import org.openqa.selenium.devtools.v108.network.model.Request;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkMocking {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver d1 = new ChromeDriver();

		DevTools devToolsObject = d1.getDevTools();
		devToolsObject.createSession();

		devToolsObject.send(Fetch.enable(Optional.empty(), Optional.empty()));

		devToolsObject.addListener(Fetch.requestPaused(), request -> {
			Request req = request.getRequest();

			if (req.getUrl().contains("shetty")) {
				String newUrl = req.getUrl().replace("=shetty", "=BadGuy");

				System.out.println(newUrl);
				devToolsObject.send(Fetch.continueRequest(request.getRequestId(), Optional.of(newUrl),
						Optional.of(req.getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
			} else {
				devToolsObject.send(Fetch.continueRequest(request.getRequestId(), Optional.of(req.getUrl()),
						Optional.of(req.getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
			}
			;
		});

		d1.get("https://rahulshettyacademy.com/angularAppdemo/");
		d1.findElement(By.cssSelector("button[routerlink*='library']")).click();

	}

}
