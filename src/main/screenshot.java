package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.opencsv.CSVWriter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class screenshot {
	public static void getMudancas(WebDriver site, JavascriptExecutor Driver, Actions acao) throws IOException {
		List<WebElement> Attributes = (List<WebElement>) Driver.executeScript("return window.Attributes;");
		List<WebElement> AddedNodes = (List<WebElement>) Driver.executeScript("return window.AddedNodes;");
		WebElement ele = null;
		WebElement pai = null;
		FileWriter outputfile = new FileWriter(new File("/home/romero/Imagens/teste.csv")); 
		CSVWriter writer = new CSVWriter(outputfile);
		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] { "Elemento", "Tamanho", "Localização"});		
		
		for(int i = 0; i <= Attributes.size(); i += 2)
		{
			ele = null; pai = null;
			String nome = "Attributes:"+i;
			try 
			{
				ele = Attributes.get(i);
				if(!ele.isDisplayed()) {
					pai = Attributes.get(i + 1);
					acao.click(pai).pause(800).build().perform();
				}
				Screenshot foto = 
						new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(site, ele);
				ImageIO.write(foto.getImage(), "PNG", new File("/home/romero/Imagens/"+nome));
				data.add(new String[] { nome, ele.getSize().toString(), ele.getLocation().toString() });
				if(pai != null) pai.click();
			}			
			catch(Exception e)
			{
				System.out.println("*******************************\n"+ e.getMessage() + "\n" + "Attributes: "+i);
				System.out.println();
			}			
		}
		
		
		for(int i = 0; i < AddedNodes.size(); i++)
		{

			ele = null;		
			String nome = "AddedNodes:"+i;
			try 
			{
				ele = AddedNodes.get(i);
				Screenshot foto = 
						new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(site, ele);
				ImageIO.write(foto.getImage(), "PNG", new File("/home/romero/Imagens/"+nome));
				data.add(new String[] { nome, ele.getSize().toString(), ele.getLocation().toString() });
			}
			catch(Exception e)
			{
				System.out.println("*******************************\n"+ e.getMessage() + "\n" + "AddedNodes: "+i);
				System.out.println();
			}
		}
		writer.writeAll(data);
		writer.close();
	}
}
