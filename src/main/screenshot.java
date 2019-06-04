package main;

import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class screenshot {
	
	
	public static void getMudancasElemento(WebDriver driver, JavascriptExecutor js, int NumElement){
		@SuppressWarnings({"unchecked"})
		List<WebElement> Mutations = (List<WebElement>) js.executeScript("return window.MutationElement;");
		String dataElement = js.executeScript("return window.ElementData;").toString();
		
		System.out.println("Element data:");
		System.out.println(dataElement);
		WebElement ele = null;
		for(int i = 0; i < Mutations.size(); i++)
		{
			ele = null;		
			String nome = "Elemento:"+ NumElement +"Mutacao"+i;
			try 
			{
				ele = Mutations.get(i);
				Screenshot foto = 
					new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, ele);
				ImageIO.write(foto.getImage(), "PNG", new File("/home/romero/Imagens/IC/Google/"+nome));			
			}
			catch(Exception e)
			{
				System.out.println("*******************************\n"+ e.getMessage() + "\n");
				System.out.println();
			}
		}
	}
	
	public static boolean verificaJanela(WebDriver driver, String mainWindowHandle, JavascriptExecutor js)
	{
		String actualWindowHandle = driver.getCurrentUrl();
		if(!mainWindowHandle.equals(actualWindowHandle)) {
			js.executeScript("window.history.go(-1)");
			return false;
		}
		return true;
	}
}
