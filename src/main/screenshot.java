package main;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

//propriedades: tamanho, localização, quantos filhos tem
//arquivo csv
public class screenshot {

	public static void getMudancas(WebDriver site, JavascriptExecutor Driver, Actions acao) {
		String comando = "return window.Attributes;";
		String comando2 = "return window.AddedNodes;";
		List<WebElement> Attributes = (List<WebElement>) Driver.executeScript(comando);
		List<WebElement> AddedNodes = (List<WebElement>) Driver.executeScript(comando2);
		WebElement ele = null;
		WebElement pai = null;
		for(int i = 0; i < Attributes.size() && Attributes.size() % 2 == 0 ; i++)
		{
			try 
			{
				ele = null;
				pai = null;
				ele = Attributes.get(i);
				String nome = "Attributes:"+i;
				if(!ele.isDisplayed()) {
					pai = Attributes.get(i + 1);
					acao.click(pai).pause(800).build().perform();
				}
				Screenshot foto = 
						new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(site, ele);
				ImageIO.write(foto.getImage(), "PNG", new File("/home/romero/Imagens/"+nome));
			}
			catch(Exception e)
			{
				System.out.println("*******************************\n errou "+ e.getMessage() + "\n" + "Elemento: "+i);
				System.out.println();
			}						
		}
		
		for(int i = 0; i < AddedNodes.size(); i++)
		{
			try 
			{
				ele = null;
				pai = null;
				ele = AddedNodes.get(i);
				String nome = "AddedNodes:"+i;
				if(!ele.isDisplayed()) {
					pai = AddedNodes.get(i + 1);
					acao.click(pai).pause(800).build().perform();
				}
				Screenshot foto = 
						new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(site, ele);
				ImageIO.write(foto.getImage(), "PNG", new File("/home/romero/Imagens/"+nome));
			}
			catch(Exception e)
			{
				System.out.println("*******************************\n errou "+ e.getMessage() + "\n" + "Elemento: "+i);
				System.out.println();
			}						
		}
	}
}
