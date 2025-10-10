package main.java.pages;

import org.openqa.selenium.By;

import yt_multi_language_ui_validator.BasePage;

public class YtInnerPage  extends BasePage{

	public By youtuberMainInfoInnerPage=By.xpath("//div[@id='above-the-fold']//div[@id='top-row']");
	
	By settingButtonUnderVideoPlayerInnerPage=By.xpath("//button[@class='ytp-button ytp-settings-button']");
	
}
