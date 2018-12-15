package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	public static void getMudancasElemento(WebDriver site, JavascriptExecutor Driver) throws IOException {
		List<WebElement> Mutations = (List<WebElement>) Driver.executeScript("return window.MutationElement;");
		WebElement ele = null;
		FileWriter outputfile = new FileWriter(new File("/home/romero/Imagens/Dados.csv")); 
		CSVWriter writer = new CSVWriter(outputfile);
		List<String[]> data = new ArrayList<String[]>();
		//data.add(new String[] { "Elemento", "Tamanho", "Localização"});	
		String[] header = { "Elemento", "Tamanho", "Localização"}; 
		writer.writeNext(header);
		Random r = new Random();
		
		for(int i = 0; i < Mutations.size(); i++)
		{
			ele = null;		
			String nome = "Mutation:"+r.nextInt((1000 - 2) + 1) + 2;
			try 
			{
				ele = Mutations.get(i);
				Screenshot foto = 
						new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(site, ele);
				ImageIO.write(foto.getImage(), "PNG", new File("/home/romero/Imagens/"+nome));
				//data.add(new String[] { nome, ele.getSize().toString(), ele.getLocation().toString() });
				writer.writeNext(new String[] { nome, ele.getSize().toString(), ele.getLocation().toString()}, true);
			}
			catch(Exception e)
			{
				System.out.println("*******************************\n"+ e.getMessage() + "\n" + "AddedNodes: "+i);
				System.out.println();
			}
		}
		//writer.writeAll(data);
		//writer.writeNext(data);
		//writer.close();
		fechaMutation(Driver);
	}
	
	public static void fechaMutation(JavascriptExecutor Driver)
	{
		String fecha = "window.observer.disconnect();";
		Driver.executeScript(fecha);
	}
}
