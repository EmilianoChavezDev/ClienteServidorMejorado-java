package Files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import Beans.Contacto.Contacto;
//contactos.txt
//ID=1;NOMBRE=JUAN;APELLIDO;INFORMACIONES=[domicilio=su casa//domicilio=su casa]

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Beans.Informacion.Informacion;
import Configurations.Configurations;
import Logger.LogRegister;

public class FilesAdmin {

	private static File file;
	private static FileWriter fw;
	private static BufferedWriter bw;

	static {
		try {
			file = new File("contactos.txt");
			if (file.createNewFile()) {
				LogRegister.info(FilesAdmin.class, "El archivo no existía y fue creado");
			}
		} catch (Exception e) {
			LogRegister.fatal(FilesAdmin.class, e.getMessage(), e);
		}

	}

	public static Map<Integer, Contacto> readFile() {
		// leer
		String NOMBRE_TAG = "NOMBRE";
		String APELLIDO_TAG = "APELLIDO";
		String INFORMACIONES_TAG = "INFORMACIONES";
		File f = new File(Configurations.getContactsFile());
		Map<Integer, Contacto> contactos = new HashMap<Integer, Contacto>();
		try {
			Scanner sc = new Scanner(f);

			while (sc.hasNextLine()) {
				Contacto contacto = new Contacto();

				String[] kVs = sc.nextLine().split(";");
				for (int i = 0; i < kVs.length; i++) {
					String[] kv = kVs[i].split("=");
					if (kv[0].equalsIgnoreCase("id")) {
						contacto.setId(Integer.parseInt(kv[1]));
						// es para tener actualizado el nuemro de id con el cual se va a crear un
						// usuario
						/*
						 * if(Integer.parseInt(kv[1]) > nuevoId){
						 * Contacto.setIdGenerator(Integer.parseInt(kv[1])); }
						 */
						Contacto.setIdGenerator(Integer.parseInt(kv[1]));

					} else if (kv[0].equalsIgnoreCase(NOMBRE_TAG)) {
						contacto.setNombre(kv[1]);

					} else if (kv[0].equalsIgnoreCase(APELLIDO_TAG)) {
						contacto.setApellido(kv[1]);

					} else if (kv[0].equalsIgnoreCase(INFORMACIONES_TAG)) {
						String informaciones[] = kv[1].substring(1, kv[1].length() - 1).split("//");
						for (int j = 0; j < informaciones.length; j++) {
							if (informaciones[j].length() > 0) {
								String kv2[] = informaciones[j].split(":");
								Informacion inf = new Informacion();
								inf.setDescripcion(kv2[0]);
								inf.setValor(kv2[1]);
								contacto.getAllInfos().put(inf.getDescripcion(), inf);
							}
						}

					}
				}
				contactos.put(contacto.getId(), contacto);

			}
			return contactos;
		} catch (FileNotFoundException e) {
			LogRegister.fatal(FilesAdmin.class, "ERROR FILE: " + e.getMessage(), e);
		} catch (ArrayIndexOutOfBoundsException e) {
			LogRegister.fatal(FilesAdmin.class, "Error array: " + e.getMessage(), e);
		} catch (Exception e) {
			LogRegister.fatal(Configurations.class, "Error inesperado", e);
		}
		return null;
	}

	private static void write(boolean reWrite, Map<Integer, Contacto> listaUsuario) {
		try {
			fw = new FileWriter(Configurations.getContactsFile(), reWrite);
			bw = new BufferedWriter(fw);
			for (Map.Entry<Integer, Contacto> esteContacto : listaUsuario.entrySet()) {
				Integer key = esteContacto.getKey();
				Contacto val = esteContacto.getValue();
				bw.write("ID=" + key + ";NOMBRE=" + val.getNombre() + ";APELLIDO=" + val.getApellido()
						+ ";INFORMACIONES=[" + val.infoToString() + "]\n");
			}
			bw.flush();
		} catch (Exception e) {
			LogRegister.fatal(Configurations.class, "Ha ocurrido un error en algun punto al registrar el log", e);
		}
	}
	/*
	 *	Para escribir al final del archivo un nuevo contacto 
	 */
	private static void write(boolean reWrite, Contacto contacto) {
		try {
			fw = new FileWriter(Configurations.getContactsFile(), reWrite);
			bw = new BufferedWriter(fw);
			
			Integer key = contacto.getId();
			Contacto val = contacto;
			bw.write("ID=" + key + ";NOMBRE=" + val.getNombre() + ";APELLIDO=" + val.getApellido() + ";INFORMACIONES=["
					+ val.infoToString() + "]\n");

			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Ha ocurrido un error en algun punto al registrar el log");
		}
	}

	public static void reWrite(Map<Integer, Contacto> listaUsuario) {
		write(false, listaUsuario);
	}

	public static void resumeWrite(Contacto contacto) {
		write(true, contacto);
	}

	
}