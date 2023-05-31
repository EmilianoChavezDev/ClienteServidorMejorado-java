package Views;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Map;

import Beans.Contacto.Contacto;
import Beans.Informacion.*;
import Client.Cliente;
import Logger.LogRegister;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Views.Interfaces.IMenuItemAction;
import Views.Interfaces.IReadInputs;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7411655004869184229L;

	private final int ancho = 720;
	private final int alto = 480;

	// lee los inputs y crea a partir de ellos un contacto nuevo con sus
	// informaciones
	private IReadInputs<Contacto> leerInputs = (p) -> {
		Component[] c = p.getComponents();// se obtiene los componentes del panel

		Contacto newContacto = new Contacto();

		boolean bandera= true;
		for (int i = 0; i < p.getComponentCount(); i++) {
			if (((JPanel) c[i]).getComponentCount() == 2) {// igual a 2 porque en cada panel que contiene una fila hay
															// un text field y un input

				Component[] contenido = ((JPanel) c[i]).getComponents();// se obtienen los componentes de cada panel

				JLabel l = (JLabel) contenido[0];
				JTextField t = (JTextField) contenido[1];
				
				if ("Nombre: ".equalsIgnoreCase(l.getText())) {
					newContacto.setNombre(t.getText());

				} else if ("Apellido: ".equalsIgnoreCase(l.getText())) {
					newContacto.setApellido(t.getText());

				} else {
					Informacion info = new Informacion();
					info.setDescripcion(l.getText().substring(0, l.getText().length() - 2));
					info.setValor(t.getText());
						newContacto.getAllInfos().put(info.getDescripcion(), info);
					

				}
				bandera=bandera && (t.getText().length()>1);
			}
		}
		return bandera ? newContacto : null;
	};

	// reemplaza una JPanel en un JScrollPane
	private IMenuItemAction<JPanel> accionReemplazar = (JScrollPane contenedor, JPanel nuevo) -> {
		contenedor.getViewport().setView(nuevo);
		return nuevo;
	};

	private IMenuItemAction<JPanel> accionReemplazarLista = (JScrollPane contenedor, JPanel nuevo) -> {
		JPanel p = createListPanel(Cliente.getContactos());
		contenedor.getViewport().setView(p);
		return p;
	};

	public VentanaPrincipal() {
		setTitle("JScrollablePanel Test");
		setLayout(new BorderLayout());

		JScrollPane spCentral = new JScrollPane();

		JPanel panel = createPanelNewContact();
		JPanel menuBar = createMenuBar(spCentral);

		spCentral.setHorizontalScrollBar(null);
		// spCentral = new JScrollPane(panel);
		spCentral.getViewport().setView(panel);
		add(BorderLayout.CENTER, spCentral);
		add(BorderLayout.NORTH, menuBar);

		setSize(ancho, alto);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel createMenuBar(JScrollPane contenedor) {
		JPanel p = new JPanel();
		JMenuBar mb = new JMenuBar();

		JMenu mNuevo = new JMenu("Nuevo");
		JMenuItem itemNewContacto = new JMenuItem("Contacto");

		itemNewContacto.addMouseListener(
				(MouseAdapter) (accionReemplazar(contenedor, createPanelNewContact(), accionReemplazar)));

		mNuevo.add(itemNewContacto);

		JMenu mBuscar = new JMenu("Buscar");
		JMenuItem itemSearchContacto = new JMenuItem("Contacto");
		itemSearchContacto
				.addMouseListener((MouseAdapter) (accionReemplazar(contenedor, createSearchPanel(), accionReemplazar)));

		mBuscar.add(itemSearchContacto);

		JMenu mListar = new JMenu("Listar");
		JMenuItem itemListContactos = new JMenuItem("Contactos");
		itemListContactos.addMouseListener((MouseAdapter) (accionReemplazar(contenedor, null, accionReemplazarLista)));
		mListar.add(itemListContactos);

		mb.add(mNuevo);
		mb.add(mBuscar);
		mb.add(mListar);

		p.add(mb);
		return p;
	}

	private JPanel createPanelNewContact() {
		JPanel panel = new JPanel();
		// panel.setLayout(new GridLayout(100, 0, 10, 10));

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(createInput("Nombre"));

		panel.add(createInput("Apellido"));

		JPanel pnBtnAddInput = new JPanel();
		JButton btnAdd = new JButton("+");
		pnBtnAddInput.add(btnAdd);
		panel.add(pnBtnAddInput);

		btnAdd.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo campo: ");
				if (nombre != null && nombre.length() > 1) {
					panel.add(createInput(nombre), panel.getComponentCount() - 2);// menos 2 porque se va a colocar
																					// antes del los botones de guardar
																					// y agregar
					panel.updateUI();
				} else if (nombre != null) {
					JOptionPane.showMessageDialog(null,
							"El nombre del campo tiene que tener mas de un caracter por lo menos.",
							"Error al crear campo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		JPanel panelBoton = new JPanel();
		JButton btnGuardar = new JButton("Guardar");
		panelBoton.add(btnGuardar);
		panel.add(panelBoton);

		btnGuardar.addMouseListener(eventPressed(panel, leerInputs));

		return panel;
	}

	private MouseAdapter eventPressed(JPanel p, IReadInputs<Contacto> collback) {

		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Contacto c = collback.readInput(p);
				if (c != null) {
					if (Cliente.addContacto(c)) {
						Component[] componentes = p.getComponents();
						for (int i = componentes.length - 2; i >= 0; i--) {
							JPanel panelInput = (JPanel) componentes[i];
							if (panelInput.getComponentCount() == 2) {
								Component[] inputLabel = panelInput.getComponents();
								JLabel label = (JLabel) inputLabel[0];
								JTextField txt = (JTextField) inputLabel[1];
								if (i >= 0 && i <= 1) {
									txt.setText("");
								} else {
									p.remove(i);
								}
								p.updateUI();
								LogRegister.info(VentanaPrincipal.class, label.getText());
								LogRegister.info(VentanaPrincipal.class, txt.getText());
							}
						}
						LogRegister.info(VentanaPrincipal.class,"Se ha guardado el contacto");
						JOptionPane.showMessageDialog(null, "Se ha guardado el contacto");
					} else {
						LogRegister.info(VentanaPrincipal.class,"Error al guardar el contacto");
						JOptionPane.showMessageDialog(null, "Error al guardar el contacto");
					}
				}else {
					JOptionPane.showMessageDialog(null, "No puede dejar campos vacios");
				}
			}
		};
	}

	private MouseAdapter accionReemplazar(JScrollPane contenedor, JPanel nuevo, IMenuItemAction<JPanel> collback) {

		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				collback.actionPressed(contenedor, nuevo);
			}
		};
	}

	private JPanel createInput(String nameInput) {
		JPanel elemento = new JPanel();
		JLabel label = new JLabel(nameInput + ": ");
		JTextField txt = new JTextField(30);

		elemento.add(label);
		elemento.add(txt);

		return elemento;

	}

	private JPanel createListPanel(Map<Integer, Contacto> contacts) {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());

		// panel para mostrar info de contacto seleccionado
		JPanel infoSeleccion = new JPanel();

		// Lista de contactos
		// List<Contacto> l = new ArrayList<Contacto>();

		Map<Integer, Contacto> mapContactos = contacts;

		JPanel panelLista = new JPanel();

		JList<Contacto> lista = new JList<Contacto>();
		lista.setVisibleRowCount(15);
		JScrollPane scroll = new JScrollPane(lista);
		panelLista.add(scroll);

		Contacto[] array = new Contacto[mapContactos != null ? mapContactos.size() : 0];

		lista.setListData(mapContactos != null ? mapContactos.values().toArray(array) : new Contacto[0]);

		// evento al seleccionar un elemento de la lista
		lista.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				Contacto a = lista.getSelectedValue();

				// JOptionPane.showMessageDialog(null, a);
				infoSeleccion.removeAll();
				JTextArea info = new JTextArea(mappearContacto(a));

				JButton btnEliminar = new JButton("Eliminar");
				btnEliminar.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						// si se elimina el contacto se actualiza la lista y elimina el text area y el
						// boton de eliminacion
						if (Cliente.removeContacto(a)) {
							LogRegister.info(VentanaPrincipal.class, "Se a eliminado el contacto");
							JOptionPane.showMessageDialog(null, "Se a eliminado el contacto");
							Map<Integer, Contacto> contactos = Cliente.getContactos();
							Contacto[] contactArray = new Contacto[contactos.size()];

							lista.setListData(contactos.values().toArray(contactArray));
							lista.updateUI();
							infoSeleccion.removeAll();
							infoSeleccion.updateUI();

						} else {
							LogRegister.info(VentanaPrincipal.class, "Error al eliminar el contacto");
							JOptionPane.showMessageDialog(null, "Error al eliminar el contacto");
						}
					}
				});

				JButton btnAddInfo = new JButton("Agregar Info");
				btnAddInfo.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {

						String descripcion = JOptionPane.showInputDialog(null, "Ingrese la descripcion de la nueva informacion");
						if(descripcion.length() > 0) {
							String valor = JOptionPane.showInputDialog(null, "Ingrese el valor de la nueva informacion");
							if(valor.length() > 0) {
								Informacion newInfo = new Informacion();
								newInfo.setDescripcion(descripcion);
								newInfo.setValor(valor);
								a.getAllInfos().put(descripcion, newInfo);
								//se modifica el contacto 
								Cliente.modifyContacto(a);
								//se obtiene la nueva lista de contactos y se actualiza la lista de la interfaz
								Map<Integer, Contacto> contactos = Cliente.getContactos();
								Contacto[] contactArray = new Contacto[contactos.size()];

								lista.setListData(contactos.values().toArray(contactArray));
								lista.updateUI();
								infoSeleccion.removeAll();
								infoSeleccion.updateUI();

							}else {
								JOptionPane.showMessageDialog(null, "No se puede crear una informacion con un valor vacio");
							}
						}else {
							JOptionPane.showMessageDialog(null, "No se puede crear una informacion sin una descripcion");
						}
						
					}
				});

				infoSeleccion.add(info);
				infoSeleccion.add(btnEliminar);
				infoSeleccion.add(btnAddInfo);
				panelPrincipal.updateUI();
			}
		});

		panelPrincipal.add(panelLista, BorderLayout.NORTH);
		panelPrincipal.add(infoSeleccion, BorderLayout.SOUTH);

		return panelPrincipal;
	}

	private JPanel createSearchPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel resultados = new JPanel();

		JPanel panelBuscador = new JPanel();

		// Contenido panel buscador
		JTextField txt = new JTextField(30);
		JButton boton = new JButton("Buscar");
		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (txt.getText().length() > 0) {
					Map<Integer, Contacto> contactosEncontrados = Cliente.findContactoByName(txt.getText().trim());
					// resultados.add(createListPanel(contactosEncontrados));

					// JPanel panelPrincipal = new JPanel();
					// panelPrincipal.setLayout(new BorderLayout());

					// panel para mostrar info de contacto seleccionado
					// JPanel infoSeleccion = new JPanel();

					// Lista de contactos
					// List<Contacto> l = new ArrayList<Contacto>();

					// Map<Integer, Contacto> mapContactos = contacts;

					JPanel panelLista = new JPanel();

					JList<Contacto> lista = new JList<Contacto>();
					lista.setVisibleRowCount(15);
					JScrollPane scroll = new JScrollPane(lista);
					panelLista.add(scroll);

					Contacto[] array = new Contacto[contactosEncontrados != null ? contactosEncontrados.size() : 0];

					lista.setListData(contactosEncontrados != null ? contactosEncontrados.values().toArray(array)
							: new Contacto[0]);
					resultados.removeAll();
					resultados.add(panelLista);
					panel.updateUI();
					// evento al seleccionar un elemento de la lista
					lista.addListSelectionListener(new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {

							Contacto a = lista.getSelectedValue();

							StringBuilder sb = new StringBuilder();
							sb.append("Nombre: ");
							sb.append(a.getNombre());
							sb.append("\nApellido: ");
							sb.append(a.getApellido());
							sb.append("\nInformaciones: ");
							if (a.getAllInfos().size() > 0) {
								for (Map.Entry<String, Informacion> info : a.getAllInfos().entrySet()) {
									sb.append("\n\t");
									sb.append(info.getValue().getDescripcion());
									sb.append(": ");
									sb.append(info.getValue().getValor());
								}
							} else {
								sb.append("\n\tSin informaciones");
							}

							JOptionPane.showMessageDialog(null, sb.toString());
						}
					});

					panel.updateUI();
				}
			}
		});

		panelBuscador.add(txt);
		panelBuscador.add(boton);

		panel.add(panelBuscador, BorderLayout.NORTH);
		panel.add(resultados, BorderLayout.SOUTH);

		panel.updateUI();
		return panel;
	}

	private String mappearContacto(Contacto c) {
		if (c != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Nombre: ");
			sb.append(c.getNombre());
			sb.append("\nApellido: ");
			sb.append(c.getApellido());
			sb.append("\nInformaciones: ");
			Collection<Informacion> infos = c.getAllInfos().values();
			infos.forEach((Informacion i) -> {
				sb.append("\n\t");
				sb.append(i.getDescripcion());
				sb.append(": ");
				sb.append(i.getValor());
			});

			return sb.toString();
		}
		return "";
	}

}
