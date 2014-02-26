import static java.lang.System.out;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = null;
		String deviceID = null;
		String stringy = "http://int.mysnapsync.com/api/getWidget?auth_token=aUwWjx5cAlW5xjjtA9yJoPltlSro1I6hAS3pKWlzLXD9HxIte5ccRv69RBfk%2FlzNMA4MhS7pz%2FxbGQQxq7%2FNYGiR7qyeJnSiCyDiyHoKOuBxwSof9xj00Q%3D%3D&amp;path=%2Fd37cb78f%2Fmnt%2Fsdcard%2F&amp;action=delete&amp;target=%2Fd37cb78f%2Fmnt%2Fsdcard%2FYoga+Rave+So+What+Project%21+Krishna+Govinda+Live.mp3&amp;whoAmI=";
		if (stringy.indexOf("action=delete&") > 0) {
			try {
				stringy = URLDecoder.decode(stringy, "UTF-8").toString();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
    		filePath = stringy.substring(stringy.indexOf("target=") + 8, stringy.indexOf("&amp;whoAmI="));
    		deviceID = filePath.substring(1, filePath.indexOf('/'));
    		filePath = filePath.substring(filePath.indexOf('/')+1);
    		filePath = filePath.substring(filePath.indexOf('/'));
    		
    		System.out.println("DeviceID = " + deviceID);
    		System.out.println("FilePath = " + filePath);
    	}
	}

}
