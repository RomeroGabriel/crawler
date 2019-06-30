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
		WebDriver driver = new ChromeDriver(options);
		String path = "/home/romero/Imagens/IC/";
		Crawler c = new Crawler(driver, "https://www.google.com/", path);
		c.Execute(0);
		driver.close();
	}
}
