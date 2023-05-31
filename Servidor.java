package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Configurations.Configurations;
import Files.FilesAdmin;
import Logger.LogRegister;
import Server.Thread.Hilo;
import Service.Contacto.ContactoServiceImpl;

public class Servidor {

	private static ContactoServiceImpl contactoImpl = null;
	public static ArrayList<Thread> hilosActivos = new ArrayList<Thread>();

	static {
		contactoImpl = new ContactoServiceImpl();
		contactoImpl.setContactos(FilesAdmin.readFile());
	}

	public static void main(String args[]) {

		// contactoImpl.setContactos(FilesAdmin.readFile());

		Thread serverThreadCreateContact = new Thread(new Runnable() {

			@Override
			public void run() {
				ServerSocket ServerSocketObject;
				try {
					ServerSocketObject = new ServerSocket(Configurations.getPortCreate());

					while (true) {
						if (hilosActivos.size() >= Configurations.getProcessesLimit()) {
							try {
								LogRegister.info(Servidor.class,"Se ha llegado al limite de hilos, SocketCrear esperando");
								Thread.sleep(Configurations.getTimeToWait());
							} catch (InterruptedException e) {
								LogRegister.error(Servidor.class,
										"Se ha llegado al limite de hilos, y no se pudo esperar el tiempo indicado", e);
							}
						} else {
							try {
								Socket socketCliente = ServerSocketObject.accept();
								LogRegister.info(Servidor.class, "Hilo asignado a Crear Contacto");
								Hilo c = new Hilo(socketCliente, contactoImpl, hilosActivos);
								hilosActivos.add(c);
							} catch (IOException e) {
								try {
									ServerSocketObject.close();
								} catch (IOException e1) {
									LogRegister.fatal(Servidor.class, e.getMessage(), e);
								}
								LogRegister.fatal(Servidor.class, e.getMessage(), e);
							}
						}
					}
				} catch (IOException e2) {
					LogRegister.fatal(Servidor.class, e2.getMessage(), e2);
				}
			}
		});

		Thread serverThreadModifyContact = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					try (ServerSocket ServerSocketObject = new ServerSocket(Configurations.getPortModify())) {
						while (true) {
							if (hilosActivos.size() >= Configurations.getProcessesLimit()) {
								try {
									LogRegister.info(Servidor.class,"Se ha llegado al limite de hilos, SocketModificar esperando");
									Thread.sleep(Configurations.getTimeToWait());
								} catch (InterruptedException e) {
									LogRegister.error(Servidor.class,
											"Se ha llegado al limite de hilos, y no se pudo esperar el tiempo indicado",
											e);
								}
							} else {
								Socket socketCliente = ServerSocketObject.accept();
								LogRegister.info(Servidor.class, "Hilo asignado a Modificar Contacto");
								Hilo c = new Hilo(socketCliente, contactoImpl, hilosActivos);
								hilosActivos.add(c);
							}
						}
					}
				} catch (IOException e) {
					LogRegister.fatal(Servidor.class, e.getMessage(), e);
				}
			}
		});

		Thread serverThreadEreaseContact = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					try (ServerSocket ServerSocketObject = new ServerSocket(Configurations.getPortErease())) {
						while (true) {
							if (hilosActivos.size() >= Configurations.getProcessesLimit()) {
								try {
									LogRegister.info(Servidor.class,"Se ha llegado al limite de hilos, SocketBorrar esperando");
									Thread.sleep(Configurations.getTimeToWait());
								} catch (InterruptedException e) {
									LogRegister.error(Servidor.class,
											"Se ha llegado al limite de hilos, y no se pudo esperar el tiempo indicado",
											e);
								}
							} else {
								Socket socketCliente = ServerSocketObject.accept();
								LogRegister.info(Servidor.class, "Hilo asignado a Borrar Contacto");
								Hilo c = new Hilo(socketCliente, contactoImpl, hilosActivos);
								hilosActivos.add(c);
							}
						}
					}
				} catch (IOException e) {
					LogRegister.fatal(Servidor.class, e.getMessage(), e);
				}
			}
		});

		Thread serverThreadGetContact = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					try (ServerSocket ServerSocketObject = new ServerSocket(Configurations.getByName())) {
						while (true) {
							if (hilosActivos.size() >= Configurations.getProcessesLimit()) {
								try {
									LogRegister.info(Servidor.class,"Se ha llegado al limite de hilos, SocketMandarContacto esperando");
									Thread.sleep(Configurations.getTimeToWait());
								} catch (InterruptedException e) {
									LogRegister.error(Servidor.class,
											"Se ha llegado al limite de hilos, y no se pudo esperar el tiempo indicado",
											e);
								}
							} else {

								Socket socketCliente = ServerSocketObject.accept();
								LogRegister.info(Servidor.class, "Hilo asignado a Buscar Contacto");
								Hilo c = new Hilo(socketCliente, contactoImpl, hilosActivos);
								hilosActivos.add(c);
							}
						}
					}
				} catch (IOException e) {
					LogRegister.fatal(Servidor.class, e.getMessage(), e);
				}
			}
		});

		Thread serverThreadGetAllContacts = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					try (ServerSocket ServerSocketObject = new ServerSocket(Configurations.getPortAll())) {
						while (true) {
							LogRegister.info(Servidor.class,"Existen "+hilosActivos.size()+" hilos activos...");
							if (hilosActivos.size() >= Configurations.getProcessesLimit()) {
								try {
									LogRegister.info(Servidor.class,"Se ha llegado al limite de hilos, SocketMandarContactos esperando");
									Thread.sleep(Configurations.getTimeToWait());
								} catch (InterruptedException e) {
									LogRegister.error(Servidor.class,
											"Se ha llegado al limite de hilos, y no se pudo esperar el tiempo indicado",
											e);
								}
							} else {

								Socket socketCliente = ServerSocketObject.accept();
								LogRegister.info(Servidor.class, "Hilo asignado a Mandar Lista Contactos");
								Hilo c = new Hilo(socketCliente, contactoImpl, hilosActivos);
								hilosActivos.add(c);
							}
						}
					}
				} catch (IOException e) {
					LogRegister.fatal(Servidor.class, e.getMessage(), e);
				}
			}
		});

		serverThreadCreateContact.start();
		serverThreadModifyContact.start();
		serverThreadEreaseContact.start();
		serverThreadGetContact.start();
		serverThreadGetAllContacts.start();
	}

}
