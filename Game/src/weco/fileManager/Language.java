package weco.fileManager;

import java.util.HashMap;

public class Language {

	public static HashMap<LanguageType, String> string = new HashMap<LanguageType, String>();
	
	public static void setupLanguage(){
		string.put(new LanguageType("en", "ship0"), "Light Armored Carrier, Type 2xL09");
	}
	
}
