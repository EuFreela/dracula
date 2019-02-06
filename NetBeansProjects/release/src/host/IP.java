/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package host;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author legend
 */
public class IP {
    
    private String ipLocal;
    private String ipExternal;
    private String lines;
    private Matcher mat;

    public IP() {  
        this.ipLocal = null;
        this.ipExternal = null;
        this.lines = null;
        this.ipMachine();
    }
    
    public void ipMachine()
    { 					 		 
        try
        {			
            InetAddress end = InetAddress.getLocalHost();
            this.ipLocal = end.getHostAddress().toString();		
            
            URL url = new URL("http://checkip.dyndns.org/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            BufferedReader page = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String meuIP = page.readLine();
	    this.ipExternal = meuIP.substring(meuIP.indexOf(": ") + 2, meuIP.lastIndexOf("</body>"));
            this.ipExternal = this.ipExternal.replace("-",".");
            page.close();
            
       } catch(MalformedURLException e1){
                   System.exit(1);
       }catch(IOException e2){
                   System.exit(1);
       }
    }
    
    public String getIpLocal() {
        return ipLocal;
    }

    public String getIpExternal() {
        return ipExternal.replace(".","-");
    }

    public void setIpLocal(String ipLocal) {
        this.ipLocal = ipLocal;
    }

    public void setIpExternal(String ipExternal) {
        this.ipExternal = ipExternal;
    }
    
}
