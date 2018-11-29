package main;

import org.openqa.selenium.JavascriptExecutor;
//Stop propagation
public class PreventDefaultClick {
	public static void PreventDefault(JavascriptExecutor Driver) {
		String script =				
				"document.body.addEventListener('click', function (ev) {"
				+ "ev.preventDefault();"
				+ "ev.stopPropagation();"
				+ "});";
//				+"document.body.addEventListener('input', function (ev) {"
//				 	//+ "ev.stopPropagation();"
//				 	+ "ev.stopPropagation();"
//				+ "});";	
		Driver.executeScript(script);
	}
}
