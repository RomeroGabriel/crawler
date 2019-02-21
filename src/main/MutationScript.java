package main;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MutationScript {
	public static void ScriptPorElemento(JavascriptExecutor js, WebElement Element) {
		String scriptPorElemento =
				"window.MutationElement = [];"+
				"window.observer = new MutationObserver(function(mutations) {" +
						"mutations.forEach(function(mutation){"+
							"console.log(mutation);"+
							"window.MutationElement.push(mutation.target);"+
						"});"+
					"});"+
				"var observerConfig = { childList: true, subtree: true, attributes: true, characterData: false };"+
				"var targetNode = arguments[0];"+
				"window.observer.observe(targetNode, observerConfig);";	
		
		js.executeScript(scriptPorElemento, Element);
	}
	
	public static void Teste(JavascriptExecutor js, WebElement Element) {
		String scriptPorElemento =
				"window.observer = new MutationObserver(function(mutations) {" +
						"console.log(mutations);"+
						"mutations.forEach(function(mutation){"+
						"});"+
					"});"+
				"var observerConfig = { childList: true, subtree: true, attributes: true, characterData: false, attributeOldValue: true };"+
				"var targetNode = document.body;"+
				"window.observer.observe(targetNode, observerConfig);";			
		js.executeScript(scriptPorElemento, Element);
	}
}
