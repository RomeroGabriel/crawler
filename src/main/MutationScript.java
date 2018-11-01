package main;

import org.openqa.selenium.JavascriptExecutor;

public class MutationScript {
	public static void Script(JavascriptExecutor Driver) {
		String script =
				"window.mudancas = [];"+
				"var observer = new MutationObserver(function(mutations) {" +
						"mutations.forEach(function(mutation){"+
							"if(mutation.addedNodes.length > 0){"+
								"mutation.addedNodes.forEach(function(currentValue, currentIndex, listObj) {"+
									"if(currentValue.nodeType == 1){"+
										"window.mudancas.push(currentValue);"+
									"}"+
								"});"+
							"}"+
							"if(mutation.type == 'attributes'){"+
								"console.log(mutation);" +
							"}"+
						"});"+
					"});"+
				"var observerConfig = { childList: true, subtree:true, attributes: true, attributeOldValue: true };"+
				"var targetNode = document.body;"+
				"observer.observe(targetNode, observerConfig);";
		Driver.executeScript(script);
	}
}
