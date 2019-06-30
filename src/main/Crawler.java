package main;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Crawler {
	
	private WebDriver driver;
	
	private String url;
	private Actions acao = null;
	private JavascriptExecutor js = null;
	private SaveImagens save;
	private Write write;
	
	public Crawler(WebDriver driver, String url, String path) throws IOException {
		this.driver = driver;
		this.url = url;
		this.js = (JavascriptExecutor) driver;
		this.acao = new Actions(driver);
		this.save = new SaveImagens(path, "https://www.google.com/", "Google");
		this.write = new Write(path);
	}
	
	public void Execute(int position) throws Exception {
		this.driver.get(this.url);
		List<WebElement> Elements = ElementsPage.getElements(this.driver);
		WebElement target = null;
		JSCode.setJquery(this.js);
		try {
			for(; position < Elements.size(); position++) {
				target = Elements.get(position);
				if(target.getSize().height > 0 && target.getSize().width > 0) {
					this.js.executeScript(JSCode.SCRIPT_BY_ELEMENT, target);
					this.acao.moveToElement(target).pause(500);
					this.acao.build().perform();
					CheckUpdates(position, target, "Mouse ouver");
					this.acao.click().pause(500);
					this.acao.build().perform();
					CheckUpdates(position, target, "Click");
				}
			}
			this.write.WriteJSON();
		}
		catch(Exception e)
		{
			//System.out.println(e.getMessage());
			Execute(position + 1);
		}
		finally
		{
			this.write.close();
		}
	}
	
	public void CheckUpdates(int position, WebElement target, String type) {
		if(this.save.verificaJanela(this.driver, this.js)) {
			int mutation = this.save.getMudancasElemento(driver, js, position);
			if(mutation > 0) 
				this.write.addObject(position, target, type, this.save.getInformacoesElemento(js));
		}
	}						
}