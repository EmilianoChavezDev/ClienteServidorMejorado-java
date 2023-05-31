package Server.Thread;

/*
import java.io.*;
import java.net.Socket;

import Service.Usuario.IUsuarioService;

public class Hilo extends Thread {
	private final Socket socketCliente;
	private final ObjectInputStream in;
	private ObjectOutputStream ou;
	private Integer numHilo;

	// private final IUsuarioService userService;

	public Hilo(Socket socket, Integer numHilo) throws IOException {
		this.socketCliente = socket;

		this.in = new ObjectInputStream(this.socketCliente.getInputStream());
		this.ou = new ObjectOutputStream(this.socketCliente.getOutputStream());
		this.numHilo = numHilo;
	}

	public void run() {

	}
}
*/
import java.net.*;
import java.util.ArrayList;

import Beans.Contacto.Contacto;
import Configurations.Configurations;
import Files.FilesAdmin;
import Logger.LogRegister;
import Service.Contacto.ContactoServiceImpl;

import java.io.*;

public class Hilo extends Thread {
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Socket socketCliente;
	private ContactoServiceImpl contactImpl = null;
	public static ArrayList<Thread> listaHilos= null;

	public Hilo(Socket unSocketCliente, ContactoServiceImpl contactoImpl, ArrayList<Thread> hilosActivos) {
		contactImpl = contactoImpl;
		listaHilos = hilosActivos;
		try {
			socketCliente = unSocketCliente;

			entrada = new ObjectInputStream(socketCliente.getInputStream());
			salida = new ObjectOutputStream(socketCliente.getOutputStream());

			this.start();
		} catch (IOException e) {
			LogRegister.fatal(Hilo.class,"Conexión :" + e.getMessage() , e);
		}
	}


	public void run() {
		try {
			LogRegister.info(Hilo.class,"Puerto cliente de salida: " + socketCliente.getPort());
			LogRegister.info(Hilo.class,"Puerto servidor de entrada: " + socketCliente.getLocalPort());

			if (socketCliente.getLocalPort() == Configurations.getPortCreate()) {
				try {
					Contacto dato = (Contacto) entrada.readObject();
					LogRegister.info(Hilo.class,"Dato recibido: " + dato);
					
					dato.setId(Contacto.getIdGenerator());
					contactImpl.agregar(dato);

					FilesAdmin.resumeWrite(dato);
					salida.writeObject(dato);
					Thread.sleep(Configurations.getDelay());
					listaHilos.remove(this);
				} catch (ClassNotFoundException | InterruptedException e) {
					// TODO Auto-generated catch block
					LogRegister.fatal(Hilo.class,e.getMessage(),e);
				}

			} else if (socketCliente.getLocalPort() == Configurations.getPortModify()) {
				try {
					Contacto datos = (Contacto) entrada.readObject();
					contactImpl.actualizar(datos);
					FilesAdmin.reWrite(contactImpl.getContactos());
					salida.writeObject(datos);
					listaHilos.remove(this);
				} catch (ClassNotFoundException e) {
					LogRegister.fatal(Hilo.class,e.getMessage(),e);
				}

			} else if (socketCliente.getLocalPort() == Configurations.getPortErease()) {
				try {
					Contacto datos = (Contacto) entrada.readObject();
					contactImpl.eliminar(datos);
					FilesAdmin.reWrite(contactImpl.getContactos());
					salida.writeObject(datos);
					listaHilos.remove(this);
				} catch (ClassNotFoundException e) {
					LogRegister.fatal(Hilo.class,e.getMessage(),e);
				}

			} else if (socketCliente.getLocalPort() == Configurations.getByName()) {
				try {
					String datos = (String) entrada.readObject();
					
					salida.writeObject(contactImpl.findByName(datos));
					listaHilos.remove(this);
				} catch (ClassNotFoundException e) {
					LogRegister.fatal(Hilo.class,e.getMessage(),e);
				}
			}
			
			else if (socketCliente.getLocalPort() == Configurations.getPortAll()) {
				try {
					String datos = (String) entrada.readObject();
					salida.writeObject(contactImpl.getContactos());
					Thread.sleep(Configurations.getDelay());
					listaHilos.remove(this);
				} catch (ClassNotFoundException e) {
					LogRegister.fatal(Hilo.class,e.getMessage(),e);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("No se pudo esperar linea 128 de hilo");
				}

			}
			LogRegister.info(Hilo.class, "Termino el hilo con el puerto: " + socketCliente.getLocalPort());
			socketCliente.close();
		} catch (EOFException e) {
			LogRegister.fatal(Hilo.class,e.getMessage(),e);
		} catch (IOException e) {
			LogRegister.fatal(Hilo.class,e.getMessage(),e);
		}
	}

}
