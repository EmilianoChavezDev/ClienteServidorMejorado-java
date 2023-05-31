package Configurations;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import Logger.LogRegister;

public class Configurations {
    private static Properties prop;
    private static String IP;
    private static int PORTCREATE;
    private static int PORTMODIFY;
    private static int PORTEREASE;
    private static int PORTGETALL;
    private static int GETBYNAME;
    private static String FILECONTACTS;
    private static int PROCESSESLIMIT;
    private static int TIMER;
    private static int DELAY;
    
    static{
        InputStream input = null;
        try {
            prop = new Properties();
            input = new FileInputStream("config.properties");
            prop.load(input);
            IP= prop.getProperty("IP");
            PORTCREATE= convertirAInteger(prop.getProperty("port.create"));
            PORTMODIFY= convertirAInteger(prop.getProperty("port.modify"));
            PORTEREASE= convertirAInteger(prop.getProperty("port.erease"));
            FILECONTACTS= prop.getProperty("file.contacts");
            PROCESSESLIMIT=convertirAInteger(prop.getProperty("limit.processes"));
            TIMER= convertirAInteger(prop.getProperty("timer"));
            GETBYNAME = convertirAInteger(prop.getProperty("port.find.name"));
            PORTGETALL = convertirAInteger(prop.getProperty("port.get.all"));
            DELAY = convertirAInteger(prop.getProperty("deley"));
        } catch (FileNotFoundException e) {
        	LogRegister.fatal(Configurations.class, "Error al encontrar el archivo", e);
		}catch (IOException e) {
        	LogRegister.fatal(Configurations.class, e.getMessage(), e);
		}
    }
    private static int convertirAInteger(String dato) {
    	int numero=-1;
    	try {
    		numero= Integer.parseInt(dato);
    	} catch (NumberFormatException e) {
        	LogRegister.fatal(Configurations.class, e.getMessage(), e);
        }
    	return numero;
    }
    public static String getIP(){
        return IP;
    }
    public static int getPortCreate(){
        return PORTCREATE;
    }
    public static int getPortModify(){
        return PORTMODIFY;
    }
    public static int getPortErease(){
        return PORTEREASE;
    }
    public static String getContactsFile(){
        return FILECONTACTS;
    }
    public static int getProcessesLimit(){
        return PROCESSESLIMIT;
    }
    public static int getTimeToWait(){
        return TIMER;
    }
    public static int getByName(){
        return GETBYNAME;
    }
    public static int getPortAll(){
        return PORTGETALL;
    }
    public static int getDelay(){
        return DELAY;
    }
    public static void main(String a[]){
    }
}
