package main;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MutationScript {
	public static void ScriptPorElemento(JavascriptExecutor js, WebElement Element) {
		String scriptPorElemento = JSCode.SCRIPT_BY_ELEMENT;
		//copiar código do inspetor, e verificar no console log
		//add class no elemento já capturado
		js.executeScript(scriptPorElemento, Element);
	}
}
