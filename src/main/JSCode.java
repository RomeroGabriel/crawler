package main;

import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;

public class JSCode {
	
	public final static String SCRIPT_BY_ELEMENT = 
	"window.MutationElement = [];"+
	"window.InformacoesElement = [];"+
	"window.AllXPath = [];"+
    "function getXPath (target) {" +
    "   var xpath = '', tagName, parent = target.parentElement," +
    "       index, children;" +
    "   while (parent != null) {" +
    "       tagName = target.tagName.toLowerCase();" +
    "       children = [].slice.call(parent.children);" +
    "       index = children.indexOf(target) + 1;" +
    "       xpath = '/' + tagName + '[' + index + ']' + xpath;" +
    "       target = parent;" +
    "       parent = target.parentElement;" +
    "   };" +
    "   return xpath;" +
    "}" +
	"window.observer = new MutationObserver(function(mutations) {" +
		"mutations.forEach(function(mutation, index){"+
			"let xpathMutation = getXPath(mutation.target);"+
			"if(!window.AllXPath.includes(xpathMutation)) {"+
				"window.AllXPath.push(xpathMutation);"+
				"mutation.target['xpath'] = xpathMutation;"+
				"window.MutationElement.push(mutation.target);"+
				"let info = { " +
				 	"'Mutacao': index, 'Tag': mutation.target.tagName, 'XPath': xpathMutation, " +
				 	"'Left': parseInt($(mutation.target).offset().left)," +
				 	"'Top': parseInt($(mutation.target).offset().top)," +
				 	"'InnetHTML': mutation.target.innerHTML.length,"+
				 	"'Height': parseInt($(mutation.target).offset().outerHeight())," +
				 	"'Width': parseInt($(mutation.target).offset().outerWidth())," +
				 "};"+
				"window.InformacoesElement.push(info);"+
			"}" +			
		"});"+
	"});"+
	"var observerConfig = { childList: true, subtree: true, attributes: true, characterData: false };"+
	"var targetNode = arguments[0];"+
	"window.observer.observe(targetNode, observerConfig);";	
	
	//https://github.com/watinha/collector/blob/master/src/main/java/edu/utfpr/xbi/collector/browsers/MobileBrowser.java
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
