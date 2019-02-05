/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;


import com.mysql.jdbc.Statement;
import host.Hosts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author legend
 */
public class DBO {

    private Connection connection;
    private String password, user, database, port, url, drive;
    private String error, success;
    private Statement stm;
    private String created_at, updated_at;
    private boolean respect;

    public DBO() {
        this.port = "3306";
        this.user = "venom";
        this.password = "senha312";
        this.database = "dbdracula";
        this.url = "jdbc:mysql://192.168.1.102";
        this.drive = "com.mysql.jdbc.Driver";
        this.connection = null;
        this.created_at = this.datFormat();
        this.updated_at = this.datFormat();
    }
    
    private String datFormat()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);        
    }
    
    public Boolean openConnection()
    {        
        boolean conn = false;
        
        if(this.connection == null)
        {            
            try 
            {
                Class.forName(this.drive);
                this.connection = (Connection) DriverManager.getConnection( this.url+":"+this.port+"/"+this.database,this.user,this.password );
                conn = true;                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
                this.error = String.valueOf(ex);
            }
            
        }
        return conn;
    }
    
    /***************************************************************************
    | GETTERS
    */
    
    public String getConnString() {
        return this.url+":"+this.port+"/"+this.database+","+this.user+","+this.password;
    } 

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
    
    public Connection getConnection() {
        return connection;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public String getDatabase() {
        return database;
    }

    public String getPort() {
        return port;
    }

    public String getUrl() {
        return url;
    }

    public String getDrive() {
        return drive;
    }

    public String getError() {
        return error;
    }
    
    public Statement getStm() {
        return stm;
    }

    
    /***************************************************************************
    | SETTERS
     * @throws java.sql.SQLException
    */
    public void closeConncection()
    {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStm(Statement stm) {
        this.stm = stm;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }
    
    //************************************************************************
          
    public Boolean setSql(String sql, int value)
    {        
        this.success = "";
        boolean ret = false;
        try 
        {          
            this.stm = (Statement) this.connection.createStatement();
            
            switch (value) 
            {
                case 1://SELECT
                    ResultSet rs = this.stm.executeQuery(sql);
                    if(rs.next())
                        this.success += rs.getInt(1);
                break;
                
                case 2://UPDATE
                    this.stm.executeUpdate(sql);
                break;
                    
                case 3://DELETE
                break;

            }
                              
            ret = true;
            
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }
        
        return ret;
        
    }         
    
    public String getServerIP()
    {
        String ip = null;
        
        try 
        {          
            this.stm = (Statement) this.connection.createStatement();
            String sql = "SELECT ip FROM server";
         
            ResultSet rs = this.stm.executeQuery(sql);
            if(rs.next())
                ip = rs.getString("ip");
        } catch (SQLException ex) {}
        
        return ip;
    }
    
    public int getServerPort()
    {
        int port = 0;
        
        try 
        {          
            this.stm = (Statement) this.connection.createStatement();
            String sql = "SELECT port FROM server";
         
            ResultSet rs = this.stm.executeQuery(sql);
            if(rs.next())
                port = rs.getInt("port");
        } catch (SQLException ex) {}
        
        return port;
    }
    
    public int getServerReload()
    {
        int status = 0;
        
        try 
        {          
            this.stm = (Statement) this.connection.createStatement();
            String sql = "SELECT status FROM reload";
         
            ResultSet rs = this.stm.executeQuery(sql);
            if(rs.next())
                status = rs.getInt("status");
        } catch (SQLException ex) {}
        
        return status;
    }
    
    public int getServerTimeTask()
    {
        int time = 0;
        
        try 
        {          
            this.stm = (Statement) this.connection.createStatement();
            String sql = "SELECT time FROM task";
         
            ResultSet rs = this.stm.executeQuery(sql);
            if(rs.next())
                time = rs.getInt("time");
        } catch (SQLException ex) {}
        
        return time;
    }
    
    public int LastId(String table)
    {
        int last = 0;
        
        try 
        {          
            this.stm = (Statement) this.connection.createStatement();
            String sql = "SELECT MAX(id) FROM hosts";
         
            ResultSet rs = this.stm.executeQuery(sql);
            int id = 0;
            if(rs.next())
                last = rs.getInt(1);
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }
        
        return last;
    }
    
    /**************************************************************************/
    //HOSTS/MACHINE
    public Boolean getExistHost(String IP)
    {
        String ip = "";
        try 
        {            
            this.stm = (Statement) this.connection.createStatement();
            String sql = "SELECT * FROM hosts WHERE ip='"+IP+"'";
          
            ResultSet rs = this.stm.executeQuery(sql);
            while ( rs.next() ) 
                ip = rs.getString("ip");
            //this.closeConncection();
            
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }
        boolean valor = ip.equalsIgnoreCase(IP);
        //System.out.print("valor:"+valor+"\n");
        return valor;
    }
    
    public Boolean getExistMachine(int id)
    {
        int sId = 0;
        boolean valor = false;
        try 
        {            
            this.stm = (Statement) this.connection.createStatement();
            String sql = "SELECT * FROM machine WHERE hosts_id="+id;
          
            ResultSet rs = this.stm.executeQuery(sql);
            while ( rs.next() ) 
                sId = rs.getInt("hosts_id");
            
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }       
        
        if(sId == id) 
            valor = true;
        
        return valor;
    }
    
    public Hosts getHost(String IP)
    {
        Hosts host = new Hosts();
        try 
        {            
            //this.openConnection();
            this.stm = (Statement) this.connection.createStatement();
            String sql = "SELECT * FROM hosts WHERE ip='"+IP+"'";
          
            ResultSet rs = this.stm.executeQuery(sql);
            while ( rs.next() ){
                host.setId(rs.getInt("id"));
                host.setIpLocal(rs.getString("ip"));
                host.setOS(rs.getString("os"));
                host.setOsVersion(rs.getString("osversion"));
                host.setOsUser(rs.getString("osuser"));
                host.setOsHome(rs.getString("oshome"));
                host.setOsNameMachine(rs.getString("machine"));
                host.setOsProperties(rs.getString("osproperties"));
            }
            //this.closeConncection();
            
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }
        
        return host;
    }
    
    public Boolean insertHosts(Hosts host)
    {
        boolean ret = false;       
        
        if( !this.getExistHost(host.getIpExternal()) )
        {        
            try 
            {            
               // this.openConnection();
                this.stm = (Statement) this.connection.createStatement();
                
                //HOSTS
                String hosts = "INSERT INTO hosts (online,ip,created_at,updated_at) VALUES(?,?,?,?)";
                try (PreparedStatement pstmt = this.connection.prepareStatement(hosts)) {
                    pstmt.setInt(1, 1);
                    pstmt.setString(2, host.getIpExternal());
                    pstmt.setString(3, this.getCreated_at());
                    pstmt.setString(4, this.getUpdated_at());
                    pstmt.executeUpdate();
                    pstmt.close();
                }
                
                
                //this.closeConncection();
                ret = true;

            } catch (SQLException ex) {
                //System.out.println(ex.getMessage());
            }

        }
        return ret;
    
    }
    
    public Boolean insertMachine(Hosts host)
    {
        boolean ret = false;
                 
        if( this.getExistMachine(this.LastId("hosts")) == false )
        {
            try 
            {
                this.stm = (Statement) this.connection.createStatement();
                //MACHINE
                String machine = "INSERT INTO machine (os,osversion,osuser,oshome,osproperties,machine,created_at,updated_at,hosts_id) VALUES(?,?,?,?,?,?,?,?,?)";

                try (PreparedStatement pstmt = this.connection.prepareStatement(machine)) {
                    pstmt.setString(1, host.getOS());
                    pstmt.setString(2, host.getOSversion());
                    pstmt.setString(3, host.getOsUser());
                    pstmt.setString(4, host.getOsHome());
                    pstmt.setString(5, host.getOsProperties());
                    pstmt.setString(6, host.getOsNameMachine());
                    pstmt.setString(7, this.getCreated_at());
                    pstmt.setString(8, this.getUpdated_at());
                    pstmt.setInt(9, this.LastId("hosts"));
                    pstmt.executeUpdate();
                    pstmt.close();
                }

                ret = true;

            } catch (SQLException ex) {
                //System.out.println(ex.getMessage());
            }
        }
        return ret;
        
    }
   
        
}
