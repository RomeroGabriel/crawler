package main;

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
	
	public Crawler(WebDriver driver, String url, SaveImagens save) {
		this.driver = driver;
		this.url = url;
		this.js = (JavascriptExecutor) driver;
		this.acao = new Actions(driver);
		this.save = save;
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
					CheckUpdates(position);
					this.acao.click().pause(500);
					this.acao.build().perform();
					CheckUpdates(position);
				}
			}
		}
		catch(Exception e)
		{
			//System.out.println(e.getMessage());
			Execute(position + 1);
		}
	}
	
	public void CheckUpdates(int position) {
		//if(SaveImagens.verificaJanela(this.driver, this.url, this.js)) {
		if(this.save.verificaJanela(this.driver, this.js)) {
			int mutation = this.save.getMudancasElemento(driver, js, position);
			if(mutation > 0) {
				System.out.println("Eba tem mutation");
			}
		}
	}
	
//						if(mutation > 0) {
//							JSONObject array = new JSONObject();
//							array.put("Ação", "Mouse houver");
//							array.put("Heigth", atual.getSize().getHeight());
//							array.put("Width", atual.getSize().getWidth());
//							array.put("Texto", atual.getText());
//							
//							JSONObject jsonObj = new JSONObject();
//							jsonObj.put("Elemento " + i, array);
//							String texto = jsonObj.toJSONString();
							//String texto = "-------------------- \n Elemento: " + i +"\n";
//							texto += "Ação: Mouse houver \n";
//							texto += "Heith: " + atual.getSize().getHeight() + "\n";
//							texto += "Width: " + atual.getSize().getWidth() + "\n";
//							texto += "Texto: " + atual.getText() +"\n";
//							texto += "Localização x: " + atual.getLocation().getX() +"\n";
//							texto += "Localização y: " + atual.getLocation().getY() +"\n";
//							texto += "Mutações do elemento \n";
//							texto += screenshot.getInformacoesElemento(js);
//							texto += "\n";
//							w.writeJS(texto);
//						}
//					}										
}