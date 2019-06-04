package main;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;;

public class projeto {
	
	static Actions acao = null;
	static WebDriver driver = null;
	static JavascriptExecutor js = null;
	static String mainWindowHandle = null;
	static List<WebElement> Elements = null;
	
	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "/home/romero/Documentos/IC/Drivers-Selenium/Navegadores/chromedriver");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");	
		driver = new ChromeDriver(options);
		//driver.get("https://jqueryui.com/resources/demos/menu/default.html");
		driver.get("https://www.google.com/");
		js = (JavascriptExecutor) driver;
		JSCode.setJquery(js);
		acao = new Actions(driver);
		mainWindowHandle = driver.getCurrentUrl();	
		//percorreElementos(0);
		driver.close();
	}
	
	public static void percorreElementos(int i)
	{
		Elements = ElementsPage.getElements(driver);
		WebElement atual = null;		
		try 
		{
			for(;i < Elements.size(); i++) {
				atual = null;			
				atual = Elements.get(i);
				if(atual.getSize().height > 0 && atual.getSize().width > 0) {
					MutationScript.ScriptPorElemento(js, atual);
					
					acao.moveToElement(atual).pause(500);
					acao.build().perform();					
					if(screenshot.verificaJanela(driver, mainWindowHandle, js)) 
						System.out.println();
//						screenshot.getMudancasElemento(driver, js, i);				
					
					acao.click().pause(500);
					acao.build().perform();
					if(screenshot.verificaJanela(driver, mainWindowHandle, js)) 
						System.out.println();
//						screenshot.getMudancasElemento(driver, js, i);										
				}
			}
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			System.out.println(ex.getMessage());
			percorreElementos(i + 1);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			percorreElementos(i + 1);
		}	
	} 
}