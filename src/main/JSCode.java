package main;

import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;

public class JSCode {
	public final static String SCRIPT_BY_ELEMENT = 
	"window.MutationElement = [];"+
	"window.observer = new MutationObserver(function(mutations) {" +
		"mutations.forEach(function(mutation){"+
			"window.MutationElement.push(mutation.target);"+
			"console.log(mutation.target.tagName);"+
		"});"+
	"});"+
	"var observerConfig = { childList: true, subtree: true, attributes: true, characterData: false };"+
	"var targetNode = arguments[0];"+
	"window.observer.observe(targetNode, observerConfig);";	
	
	//classe com a injeção do jquery: https://github.com/watinha/collector/blob/master/src/main/java/edu/utfpr/xbi/collector/browsers/MobileBrowser.java
	//capturar as informações que tem no COLLECTOR_JS
	
	public static void setJquery(JavascriptExecutor js) throws Exception {
		Thread.sleep(7000);
		js.executeScript(IOUtils.toString(Thread.currentThread().
				getContextClassLoader().getResourceAsStream("js/jquery.js"), StandardCharsets.UTF_8));
		js.executeScript(IOUtils.toString(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("js/visibility.js"), StandardCharsets.UTF_8));
		Thread.sleep(3000);
	}
}
