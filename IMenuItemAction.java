package Views.Interfaces;

import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public interface IMenuItemAction<T> {
	
	T actionPressed(JScrollPane contenedor, JPanel nuevo);
}
