package main;

import org.openqa.selenium.JavascriptExecutor;

public class MutationScript {
	public static void Script(JavascriptExecutor Driver) {
		String script =
				"window.AddedNodes = [];"+
				"window.Attributes = [];"+
				"var observer = new MutationObserver(function(mutations) {" +
						"mutations.forEach(function(mutation){"+
							"if(mutation.addedNodes.length > 0){"+
								"mutation.addedNodes.forEach(function(currentValue, currentIndex, listObj) {"+
									"if(currentValue.nodeType == 1){"+
										"window.AddedNodes.push(currentValue);"+
									"}"+
								"});"+
							"}"+
							"if(mutation.type == 'attributes'){"+
								"if(mutation.attributeName == 'aria-hidden' && mutation.oldValue == 'true' && mutation.target.attributes['aria-hidden'].value == 'false'){"+
									//"window.mudancas.push({'filho': mutation.target, 'pai': mutation.target.previousSibling})"+
									"window.Attributes.push(mutation.target);"+
									"window.Attributes.push(mutation.target.previousSibling);"+
								 "}"+
							"}"+
						"});"+
					"});"+
				"var observerConfig = { childList: true, subtree:true, attributes: true, attributeOldValue: true, characterData: true };"+
				"var targetNode = document.body;"+
				"observer.observe(targetNode, observerConfig);";
		Driver.executeScript(script);
	}
}
