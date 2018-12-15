package main;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MutationScript {
	public static void ScriptPorElemento(JavascriptExecutor Driver, WebElement Element) {
		String scriptPorElemento =
				"window.MutationElement = [];"+
				"window.observer = new MutationObserver(function(mutations) {" +
						"mutations.forEach(function(mutation){"+
							"window.MutationElement.push(mutation.target);"+
							"console.log(mutation);"+
						"});"+
					"});"+
				"var observerConfig = { childList: true, subtree:true, attributes: true, attributeOldValue: true, characterData: true, characterDataOldValue: true };"+
				"var targetNode = arguments[0];"+
				"window.observer.observe(targetNode, observerConfig);";
		Driver.executeScript(scriptPorElemento, Element);
	}
}
