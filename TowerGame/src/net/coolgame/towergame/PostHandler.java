package net.coolgame.towergame;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostHandler {
	
	
	public PostHandler(){
	}

	static String targetURL = "http://towergame.thoeisen.dk";
	private String cookies = null;
	protected String readCookies(HttpURLConnection con) {
		StringBuilder cookieBuffer = null;
		String        cookieField  = null;
		String        headerName   = null;
	 
		for (int i = 1; (headerName = con.getHeaderFieldKey(i)) != null; i++) {
			if (headerName.toLowerCase().equals("HTTP_HEADER_SET_COOKIE")) {
				cookieField = con.getHeaderField(i);
				cookieField = cookieField.substring(0, 
					cookieField.indexOf(";"));
	 
				if (cookieBuffer != null) {
					cookieBuffer.append("; ");
				} else {
					cookieBuffer = new StringBuilder();
				}
				cookieBuffer.append(cookieField);
			}
		}
	 
		if (cookieBuffer != null) {
			return cookieBuffer.toString();
		} else {
			return null;
		}
	}
	
	
	public String excutePost(String urlParameters)
	  {
	    URL url;
	    HttpURLConnection connection = null;  
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/x-www-form-urlencoded");
				
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	      connection.setRequestProperty("Content-Language", "en-US");  
				
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.writeBytes (urlParameters);
	      wr.flush ();
	      wr.close ();

	      //Get Response	
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String cookies = readCookies(connection);
	      if (cookies != null){
	    	  this.cookies=cookies;
	      }
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();
	      return response.toString();

	    } catch (Exception e) {

	      e.printStackTrace();
	      return null;

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	  }
	
	
	public static String login(String username, String password){
		
		PostHandler PH = new PostHandler();
		String accepted = PH.excutePost("username="+username+"&password="+password);
		
		if (accepted == "success"){
			
		}else{
			
		}
		
		
		return "lol";
		
	}
	
	
	
	
	
	
}

