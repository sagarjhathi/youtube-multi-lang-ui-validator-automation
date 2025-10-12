package main.java.yt_multi_lang_ui_validator.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import main.java.yt_multi_lang_ui_validator.base.BasePage;
import main.java.yt_multi_lang_ui_validator.lingua.LinguaHelper;
import main.java.yt_multi_lang_ui_validator.logger.LoggerUtility;

public class YtInnerPage  extends BasePage{

	private final  Logger log=LoggerUtility.getLogger(YtInnerPage.class);

	
	public By youtuberMainInfoInnerPage=By.xpath("//div[@id='above-the-fold']//div[@id='top-row']");
	
	By settingButtonUnderVideoPlayerInnerPage=By.xpath("//button[@class='ytp-button ytp-settings-button']");
	
}
