package main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ning.http.client.ProxyServer;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;

public class projeto {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		System.setProperty("webdriver.chrome.driver", "/home/romero/Documentos/Faculdade/IC/Drivers-Selenium/Navegadores/chromedriver");
		
		BrowserMobProxy server = new BrowserMobProxyServer();

	    server.start(0);
	    //server.blacklistRequests("https?://.*\\.google-analytics\\.com/.*", 410);

	    Proxy proxy = ClientUtil.createSeleniumProxy(server);

	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(CapabilityType.PROXY, proxy);
	    
	    ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/home/romero/Documentos/Faculdade/IC/Drivers-Selenium/Navegadores/chromedriver"))
                .usingAnyFreePort()
                .build();
	    ChromeOptions options = new ChromeOptions();
	    options.merge(capabilities); 
		
		WebDriver site = new ChromeDriver();
		//site.get("https://jqueryui.com/menu/");
		site.get("https://www.google.com/");
		site.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) site;
		List<WebElement> Elements = ElementsPage.getElements(site, js);
		WebElement atual = null;
		Actions acao = new Actions(site);
		
		MutationScript.Script(js);
		
		for(int i = 0; i < Elements.size(); i++) {
			atual = null;			
			atual = Elements.get(i);
			acao.moveToElement(atual).pause(300);
			acao.build().perform();
		}
		screenshot.getMudancas(site, js);
		//site.close();
	}
}
