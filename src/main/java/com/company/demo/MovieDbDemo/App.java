package com.company.demo.MovieDbDemo;
//https://github.com/nagillavenkatesh/hello-world.git
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    
    {
    	// Create a trust manager that does not validate certificate chains
    	TrustManager[] trustAllCerts = new TrustManager[] { 
    	    new X509TrustManager() {     
    	        public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
    	            return new X509Certificate[0];
    	        } 
    	        public void checkClientTrusted( 
    	            java.security.cert.X509Certificate[] certs, String authType) {
    	            } 
    	        public void checkServerTrusted( 
    	            java.security.cert.X509Certificate[] certs, String authType) {
    	        }
    	    } 
    	}; 

    	// Install the all-trusting trust manager
    	try {
    	    SSLContext sc = SSLContext.getInstance("SSL"); 
    	    sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
    	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    	} catch (GeneralSecurityException e) {
    	} 
    	// Now you can access an https URL without having the certificate in the truststore
    	try { 
    	    URL url = new URL("https://api.themoviedb.org/3/movie/550?api_key=3acf6fe0fbc640192e5d7584b6c63677"); 
    	} catch (MalformedURLException e) {
    	} 
    	String url = "https://api.themoviedb.org/3/movie/550?api_key=3acf6fe0fbc640192e5d7584b6c63677";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		/*BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());*/
		InputStream inputStream=obj.openStream();
		String jsonresponse=IOUtils.toString(inputStream);
		System.out.println(jsonresponse);
		JSONObject jsonObj = new JSONObject(jsonresponse);
		System.out.println(jsonObj.get("adult"));
		System.out.println(jsonObj.get("backdrop_path"));
		System.out.println(jsonObj.get("genres"));
		//imdb_id
		System.out.println(jsonObj.get("imdb_id"));
		//"title":"Fight Club","video":false,"vote_average":8.2,"vote_count":7013
		System.out.println(jsonObj.get("title"));
		System.out.println(jsonObj.get("video"));
		System.out.println(jsonObj.get("vote_average"));
		System.out.println(jsonObj.get("vote_count"));
		
    }
}
