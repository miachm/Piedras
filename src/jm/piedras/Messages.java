package jm.piedras;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static String BUNDLE_NAME;
	private static ResourceBundle RESOURCE_BUNDLE;
	
	static
	{
		Locale a = Locale.getDefault();
		System.out.println(a.getDisplayLanguage());
		System.out.println(a.getLanguage());
		if (a.getDisplayLanguage().equals("español")) //español o catalan...
		{
			System.out.println("Seleccionado español");
			BUNDLE_NAME = "jm.piedras.messages-es";
			RESOURCE_BUNDLE = ResourceBundle
					.getBundle(BUNDLE_NAME);
		}
		else if(a.getDisplayLanguage().equals("català"))
		{
			System.out.println("Seleccionat català");
			BUNDLE_NAME = "jm.piedras.messages-ca";
			RESOURCE_BUNDLE = ResourceBundle
					.getBundle(BUNDLE_NAME);
		}
		else if(a.getLanguage().equals("it"))
		{
			System.out.println("Selezionato il italiana");
			BUNDLE_NAME = "jm.piedras.messages-it";
			RESOURCE_BUNDLE = ResourceBundle
					.getBundle(BUNDLE_NAME);
		}
		else
		{
			System.out.println("English selected");
			BUNDLE_NAME = "jm.piedras.messages";
			RESOURCE_BUNDLE = ResourceBundle
					.getBundle(BUNDLE_NAME);
		}
	}

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
