/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package host;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 *
 * @author legend
 */
public class Machine extends IP{
    
    private String OS, osVersion;
    private String osUser, osHome, osNameMachine;
    private String osProperties;  
    
    public Machine() 
    {
        this.OS = System.getProperty("os.name");
        this.osVersion = System.getProperty("os.version");
        this.osUser = System.getProperty("user.name");
        this.osHome = System.getProperty("user.home");        
        this.osProperties = System.getProperties().toString();
        
        try {
            this.osNameMachine = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {}
    }

    public String getOS() {
        return OS;
    }

    public String getOSversion() {
        return osVersion;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getOsUser() {
        return osUser;
    }

    public String getOsHome() {
        return osHome;
    }

    public String getOsNameMachine() {
        return osNameMachine;
    }

    public String getOsProperties() {
        return osProperties;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public void setOsUser(String osUser) {
        this.osUser = osUser;
    }

    public void setOsHome(String osHome) {
        this.osHome = osHome;
    }

    public void setOsNameMachine(String osNameMachine) {
        this.osNameMachine = osNameMachine;
    }

    public void setOsProperties(String osProperties) {
        this.osProperties = osProperties;
    }
    
    
   
}
