package main;

import org.openqa.selenium.JavascriptExecutor;

public class PreventDefaultClick {
	public static void PreventDefault(JavascriptExecutor Driver) {
		String script =				
				"document.body.addEventListener('click', function (ev) {"
				+ "ev.preventDefault();"
				+ "});";
		Driver.executeScript(script);
	}
}
