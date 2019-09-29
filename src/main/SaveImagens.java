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

public class SaveImagens {
	
	private String mainWindowHandle = null;
	private String path;
	private String namePage;
	
	public SaveImagens(String path, String mainWindowHandle, String namePage) {
		this.path = path;
		this.mainWindowHandle = mainWindowHandle;
		this.namePage = namePage;
	}
	
	public List<?> getInformacoesElemento(JavascriptExecutor js){
		return (List<?>)js.executeScript("return window.InformacoesElement;");
	}
	
	public int getMudancasElemento(WebDriver driver, JavascriptExecutor js, int NumElement){
		@SuppressWarnings({"unchecked"})
		List<WebElement> Mutations = (List<WebElement>) js.executeScript("return window.MutationElement;");
		WebElement ele = null;
		for(int i = 0; i < Mutations.size(); i++)
		{
			ele = null;		
			String nome = "Elemento: "+ NumElement +"Mutacao: "+i;
			try 
			{
				ele = Mutations.get(i);
				Screenshot foto = 
					new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, ele);
				ImageIO.write(foto.getImage(), "PNG", new File(this.path + this.namePage + "/" + nome));
			}
			catch(Exception e)
			{
				System.out.println();
			}
		}
		return Mutations.size();
	}
	
	public boolean verificaJanela(WebDriver driver, JavascriptExecutor js)
	{
		String actualWindowHandle = driver.getCurrentUrl();
		if(!this.mainWindowHandle.equals(actualWindowHandle)) {
			js.executeScript("window.history.go(-1)");
			return false;
		}
		return true;
	}
}
