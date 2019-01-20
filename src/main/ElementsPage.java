package main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementsPage {
	public static List<WebElement> getElements(WebDriver site){
		WebElement elem = site.findElement(By.tagName("body"));
		List<WebElement> elements = elem.findElements(By.cssSelector("*:not(script):not(style)"));
		return elements;
	}
}
