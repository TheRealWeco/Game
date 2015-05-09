package weco.fileManager;

import java.util.HashMap;

import weco.data.LanguageType;

public class Language {

	public static HashMap<LanguageType, String> string = new HashMap<LanguageType, String>();
	
	public static void setupLanguage(){
		string.put(new LanguageType("en", "ship0"), "Light Armored Carrier, Type 2xL09");
		string.put(new LanguageType("en", "ship1"), "Light Armored Small Carrier, Type 8xG0F");
		string.put(new LanguageType("en", "ship2"), "Medium Armored Fighter, Type 0xV4E");
		string.put(new LanguageType("en", "ship3"), "Smiley");
	}

	
}
