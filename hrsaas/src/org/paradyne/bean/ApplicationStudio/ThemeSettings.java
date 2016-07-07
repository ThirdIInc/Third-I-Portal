/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import org.paradyne.lib.BeanBase;

/**
 * @author Pankaj_Jain
 * Date : 25/12/2008
 *
 */
public class ThemeSettings extends BeanBase {
	private String fontName;
	private String fontSize;
	private String fontColor;
	private String selFont;
	private String availFont;
	private String currentMenuId;
	private String mainMenuId;
	private String themeName;
	private String myTheme;
	
	public String getMainMenuId() {
		return mainMenuId;
	}
	public void setMainMenuId(String mainMenuId) {
		this.mainMenuId = mainMenuId;
	}
	public String getCurrentMenuId() {
		return currentMenuId;
	}
	public void setCurrentMenuId(String currentMenuId) {
		this.currentMenuId = currentMenuId;
	}
	public String getSelFont() {
		return selFont;
	}
	public void setSelFont(String selFont) {
		this.selFont = selFont;
	}
	public String getAvailFont() {
		return availFont;
	}
	public void setAvailFont(String availFont) {
		this.availFont = availFont;
	}
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	public String getFontColor() {
		return fontColor;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public String getMyTheme() {
		return myTheme;
	}
	public void setMyTheme(String myTheme) {
		this.myTheme = myTheme;
	}

	
}
