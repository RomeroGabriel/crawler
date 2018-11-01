package main;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

//propriedades: tamanho, localização, quantos filhos tem
//arquivo csv
public class screenshot {

	public static void getMudancas(WebDriver site, JavascriptExecutor Driver) {
		String comando = "return window.mudancas;";
		List<WebElement> elementos = (List<WebElement>) Driver.executeScript(comando);
		WebElement ele = null;
		for(int i = 0; i < elementos.size(); i++){
			try 
			{
				ele = null;
				ele = elementos.get(i);
				String nome = "Elemento"+i;
				Screenshot foto = new AShot().coordsProvider(new WebDriverCoordsProvider()).
						shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2)).
						takeScreenshot(site, ele);
				ImageIO.write(foto.getImage(), "PNG", new File("/home/romero/Imagens/"+nome));
				String textProperties = "";
				textProperties += "\nSize: "+ ele.getSize().toString() +"\n";
				textProperties += "Location: "+ ele.getLocation().toString() +"\n";
				
				FileWriter writer = null;
				
				try
				{
					writer = new FileWriter("/home/romero/Imagens/arq.csv");
					writer.append(textProperties);
				}
				catch(Exception e) { }
				finally
				{
					try
					{
						writer.flush();
						writer.close();
					}
					catch(Exception e) { }
				}
			}
			catch(Exception e)
			{
				System.out.println();
				System.out.println("errou "+ e.getMessage());
				System.out.println("Elemento: "+i);
				System.out.println("*******************************");
			}						
		}
	}
}
