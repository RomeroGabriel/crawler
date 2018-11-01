package main;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ning.http.client.ProxyServer;

import net.lightbody.bmp.BrowserMobProxy;

public class projeto {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		System.setProperty("webdriver.chrome.driver", "/home/romero/Documentos/Faculdade/IC/Drivers-Selenium/Navegadores/chromedriver");
		
		//BrowserMobProxy server = new BrowserMobProxy();  // package net.lightbody.bmp.proxy

	    //server.start();
	    //server.setCaptureHeaders(true);
	    // Blacklist google analytics
	    //server.blacklistRequests("https?://.*\\.google-analytics\\.com/.*", 410);
	    // Or whitelist what you need
	    //server.whitelistRequests("https?://*.*.yoursite.com/.*. https://*.*.someOtherYourSite.*".split(","), 200);

	    //Proxy proxy = server.seleniumProxy(); // Proxy is package org.openqa.selenium.Proxy

	    // configure it as a desired capability
	    //DesiredCapabilities capabilities = new DesiredCapabilities();
	    //capabilities.setCapability(CapabilityType.PROXY, proxy);

		
		WebDriver site = new ChromeDriver();
		//site.get("https://jqueryui.com/menu/");
		site.get("https://www.google.com/");
		site.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) site;
		List<WebElement> Elements = ElementsPage.getElements(site, js);
		WebElement atual = null;
		Actions acao = new Actions(site);
		
		MutationScript.Script(js);
		
		for(int i = 0; i < Elements.size(); i++) {
			atual = null;			
			atual = Elements.get(i);
			acao.moveToElement(atual).pause(300);
			acao.build().perform();
		}
		screenshot.getMudancas(site, js);
		site.close();
	}
}
