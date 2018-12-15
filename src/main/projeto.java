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
		//site.get("http://yankton.com.br/#");
		site.get("https://www.google.com/");
		site.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) site;
		List<WebElement> Elements = ElementsPage.getElements(site, js);
		WebElement atual = null;
		Actions acao = new Actions(site);
		
		PreventDefaultClick.PreventDefault(js); 
		for(int i = 0; i < Elements.size(); i++) {
			atual = null;			
			atual = Elements.get(i);
			if(atual.getSize().height > 0 && atual.getSize().width > 0) {
				MutationScript.ScriptPorElemento(js, atual);
				acao.moveToElement(atual).pause(300);
				acao.build().perform();
				acao.click().pause(300);
				acao.build().perform();
				screenshot.getMudancasElemento(site, js);	
			}
		}
		//site.close();
		//1- Aceitando as ações em div(getElements), foi notado uma maior coleita de dados mas ao mesmo tempo dados repetidos.
		//2- If verificando height e width melhorou o desempenho de velocidade.
		//3- Pensamento: pegar meu dados/screenshots no ambiente javaScript e depois mandar apenas para o Java (não sei relação a imagem);
		
	}
}
