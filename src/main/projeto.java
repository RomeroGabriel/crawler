package main;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;


public class projeto {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		System.setProperty("webdriver.chrome.driver", "/home/romero/Documentos/Faculdade/IC/Drivers-Selenium/Navegadores/chromedriver");
		
		WebDriver site = new ChromeDriver();
		//site.get("https://www.diogocezar.com/");
		site.get("https://www.google.com/");
		site.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) site;
		List<WebElement> Elements = ElementsPage.getElements(site, js);
		WebElement atual = null;
		Actions acao = new Actions(site);
		
		PreventDefaultClick.PreventDefault(js);
		MutationScript.Script(js); 

		for(int i = 0; i < Elements.size(); i++) {
			atual = null;			
			atual = Elements.get(i);
			acao.moveToElement(atual).pause(300);
			acao.build().perform();
			acao.click().pause(300);
			acao.build().perform();
			//Em vez de pegar todas as mudanças e depois passar, vê um elemento e tirar a screenshot logo em seguida
		}
		screenshot.getMudancas(site, js, acao);
		//site.close();
	}
}
