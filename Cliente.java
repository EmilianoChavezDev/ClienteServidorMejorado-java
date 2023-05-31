package Client;

import java.net.*;
import java.util.List;
import java.util.Map;
import Beans.Contacto.Contacto;
import Configurations.Configurations;
import Logger.LogRegister;
import Views.VentanaPrincipal;

import java.io.*;

public class Cliente {
	private static Socket s;

	public static boolean addContacto(Contacto c) {
		try {

			s = new Socket(Configurations.getIP(), Configurations.getPortCreate());

			ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream());

			ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());

			LogRegister.info(Cliente.class, "Enviando contacto: " + c);

			salida.writeObject(c); // UTF es una codificación de Strings, ir a Sec. 4.3
			LogRegister.info(Cliente.class, "Esperando respuesta...");
			Contacto datos;
			try {

				datos = (Contacto) entrada.readObject();
				LogRegister.info(Cliente.class, "Recibido: " + datos);
				return true;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				LogRegister.fatal(Cliente.class,e.getMessage(), e);
			}

			s.close();
		} catch (UnknownHostException e) {
			LogRegister.fatal(Cliente.class, "Socket: " + e.getMessage(), e);
		} catch (EOFException e) {
			LogRegister.fatal(Cliente.class, "EOF: " + e.getMessage(), e);
		} catch (IOException e) {
			LogRegister.fatal(Cliente.class, "10: " + e.getMessage(), e);
		}
		return false;
	}

	public static boolean removeContacto(Contacto c) {
		try {

			s = new Socket(Configurations.getIP(), Configurations.getPortErease());

			ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream());

			ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());

			LogRegister.info(Cliente.class, "Enviando contacto: " + c);

			salida.writeObject(c); // UTF es una codificación de Strings, ir a Sec. 4.3

			Contacto datos;
			try {

				datos = (Contacto) entrada.readObject();
				LogRegister.info(Cliente.class, "Recibido: " + datos);
				return true;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				LogRegister.fatal(Cliente.class,e.getMessage(), e);
			}

			s.close();
		} catch (UnknownHostException e) {
			LogRegister.fatal(Cliente.class, "Socket: " + e.getMessage(), e);
		} catch (EOFException e) {
			LogRegister.fatal(Cliente.class, "EOF: " + e.getMessage(), e);
		} catch (IOException e) {
			LogRegister.fatal(Cliente.class, e.getMessage(), e);
		}
		return false;
	}

	public static boolean modifyContacto(Contacto c) {
		try {

			s = new Socket(Configurations.getIP(), Configurations.getPortModify());

			ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream());

			ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());

			LogRegister.info(Cliente.class, "Enviando contacto: " + c);

			salida.writeObject(c); // UTF es una codificación de Strings, ir a Sec. 4.3
			LogRegister.info(Cliente.class, "Esperando respuesta...");
			Contacto datos;
			try {

				datos = (Contacto) entrada.readObject();

				return true;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				LogRegister.fatal(Cliente.class,e.getMessage(), e);
			}

			s.close();
		} catch (UnknownHostException e) {
			LogRegister.fatal(Cliente.class, "Socket: " + e.getMessage(), e);
		} catch (EOFException e) {
			LogRegister.fatal(Cliente.class, "EOF: " + e.getMessage(), e);
		} catch (IOException e) {
			LogRegister.fatal(Cliente.class, "10: " + e.getMessage(), e);
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Integer, Contacto> findContactoByName(String nombre) {
		try {

			s = new Socket(Configurations.getIP(), Configurations.getByName());

			ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream());

			ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());

			

			salida.writeObject(nombre); // UTF es una codificación de Strings, ir a Sec. 4.3
			LogRegister.info(Cliente.class, "Esperando respuesta...");
			Map<Integer, Contacto> datos;
			try {

				datos = (Map<Integer, Contacto>) entrada.readObject();
				return datos;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				LogRegister.error(Cliente.class, "Error al tratar de convertir a contacto el objeto recibido en el cliente",e);
			}

			s.close();
		} catch (UnknownHostException e) {
			LogRegister.fatal(Cliente.class, "Socket: " + e.getMessage(), e);
		} catch (EOFException e) {
			LogRegister.fatal(Cliente.class, "EOF: " + e.getMessage(), e);
		} catch (IOException e) {
			LogRegister.fatal(Cliente.class, "10: " + e.getMessage(), e);
		}
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public static Map<Integer, Contacto> getContactos() {
		try {

			s = new Socket(Configurations.getIP(), Configurations.getPortAll());

			ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream());

			ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());

			salida.writeObject("Contactos"); // UTF es una codificación de Strings, ir a Sec. 4.3

			Map<Integer, Contacto> datos;
			try {

				datos = (Map<Integer, Contacto>) entrada.readObject();
				LogRegister.info(Cliente.class, "Recibido: " + datos);
				return datos;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				LogRegister.fatal(Cliente.class,e.getMessage(), e);
			}

			s.close();
		} catch (UnknownHostException e) {
			LogRegister.fatal(Cliente.class, "Socket: " + e.getMessage(), e);
		} catch (EOFException e) {
			LogRegister.fatal(Cliente.class, "EOF: " + e.getMessage(), e);
		} catch (IOException e) {
			LogRegister.fatal(Cliente.class, "10: " + e.getMessage(), e);
		}
		return null;
	}

	public static void main(String args[]) {
		new VentanaPrincipal();
	}
}
