package Logger;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;



public class LogRegister {
	//codigo fuente del escritor de archivo:http://chuwiki.chuidiang.org/index.php?title=Lectura_y_Escritura_de_Ficheros_en_Java
	//escribe una cadena en un archivo de log
	private static void escribir(String cadena) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("./logs/log.log", true);
			pw = new PrintWriter(fichero);

			pw.println(cadena);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				System.out.println("Error: No se ha podido registar el log");
			}
		}
	}
	    
	private static String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
    public static void error(Class<?> c, String msg, Exception e) {
    	System.out.println(e.getMessage());
    	escribir("ERROR [ " + c.getName() + "] ("+getDate()+"): " + msg + ":\n" + e.getStackTrace());
    }
    
    public static void fatal(Class<?> c, String msg, Exception e) {
    	System.out.println(e.getMessage());
    	escribir("FATAL [ " + c.getName() + "] ("+getDate()+"): " + msg + ":\n" + e.getStackTrace());
    }
    
    public static void info(Class<?> c, String msg) {
    	System.out.println(msg);
    	escribir("INFO [ " + c.getName() + "] ("+getDate()+"):  " + msg);
    }
}
