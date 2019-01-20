package main;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class projeto {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		System.setProperty("webdriver.chrome.driver", "/home/romero/Documentos/IC/Drivers-Selenium/Navegadores/chromedriver");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");		
		WebDriver driver = new ChromeDriver(options);
		//driver.get("https://jqueryui.com/menu/");
		driver.get("https://www.google.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/iframe")));
		List<WebElement> Elements = ElementsPage.getElements(driver);	
		
		
		percorreElementos(Elements, driver, js);
		
		driver.close();
		//1- Aceitando as ações em div(getElements), foi notado uma maior coleita de dados mas ao mesmo tempo dados repetidos.
	}
	
	public static void percorreElementos(List<WebElement> Elements,WebDriver driver, JavascriptExecutor js)
	{
		WebElement atual = null;
		Actions acao = new Actions(driver);
		String mainWindowHandle = driver.getCurrentUrl();
		PreventDefaultClick.PreventDefault(js);
		try {
			System.out.println("Elements INICIO:" + Elements.size());
			for(int i = 0; i < Elements.size(); i++) {
				atual = null;			
				atual = Elements.get(i);
				if(atual.getSize().height > 0 && atual.getSize().width > 0) {
					//MutationScript.ScriptPorElemento(js, atual);
					acao.moveToElement(atual).pause(1000);
					acao.build().perform();					
					if (screenshot.getMudancasElemento(driver, js, i, mainWindowHandle)) {
						Elements = ElementsPage.getElements(driver);
					}
					acao.click().pause(1000);
					acao.build().perform();
					if (screenshot.getMudancasElemento(driver, js, i, mainWindowHandle)) {
						Elements = ElementsPage.getElements(driver);
						System.out.println("Elements MUDOU:" +Elements.size()+ " I: " + i);
					}	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
