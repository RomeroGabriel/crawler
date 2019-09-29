package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "/home/romero/Documentos/IC/Drivers-Selenium/Navegadores/chromedriver");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.setExperimentalOption("useAutomationExtension", false);
		
		WebDriver driver = new ChromeDriver(options);
		String path = "/home/romero/Imagens/IC/";
		//Crawler c = new Crawler(driver, "https://www.google.com/", path);
		//Crawler c = new Crawler(driver, "https://jqueryui.com/menu/", path);
		//Crawler c = new Crawler(driver, "https://www.youtube.com/", path);
		//Crawler c = new Crawler(driver, "https://www.facebook.com/", path);
		//Crawler c = new Crawler(driver, "https://outlook.live.com/owa/", path);
		//Crawler c = new Crawler(driver, "https://www.wikipedia.org/", path);
		//Crawler c = new Crawler(driver, "https://www.netflix.com/br/", path);
		Crawler c = new Crawler(driver, "https://www.mercadolivre.com.br/", path);
		c.Execute(0);
		driver.close();
	}
}
