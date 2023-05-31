package Views.Panels;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NuevoContacto extends JPanel {
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;

	/**
	 * Create the panel.
	 */
	public NuevoContacto(Component padre) {
		setBackground(new Color(128, 255, 128));
		this.setPreferredSize(new Dimension(655, 481));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(253, 5, 415, 476);
		add(scrollPane);
		
		JPanel panel_1_1 = new JPanel();
		scrollPane.setViewportView(panel_1_1);
		panel_1_1.setLayout(new BoxLayout(panel_1_1, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_1_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_1_1.add(textField_1);
		
		JPanel panel_1_2 = new JPanel();
		scrollPane.setViewportView(panel_1_2);
		panel_1_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel_1_2.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_1_2.add(textField_2);
		
		JPanel panel_1_3 = new JPanel();
		scrollPane.setViewportView(panel_1_3);
		panel_1_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		panel_1_3.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel_1_3.add(textField_3);
		
		JPanel panel_1_4 = new JPanel();
		scrollPane.setViewportView(panel_1_4);
		panel_1_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		panel_1_4.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		panel_1_4.add(textField_4);
		
		JPanel panel_1_5 = new JPanel();
		scrollPane.setViewportView(panel_1_5);
		panel_1_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		panel_1_5.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		panel_1_5.add(textField_5);
		
		JPanel panel_1_6 = new JPanel();
		scrollPane.setViewportView(panel_1_6);
		panel_1_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		panel_1_6.add(lblNewLabel_6);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		panel_1_6.add(textField_6);
		
		JPanel panel_1_7 = new JPanel();
		scrollPane.setViewportView(panel_1_7);
		panel_1_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		panel_1_7.add(lblNewLabel_7);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		panel_1_7.add(textField_7);
		
		JPanel panel_1_8 = new JPanel();
		scrollPane.setViewportView(panel_1_8);
		panel_1_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		panel_1_8.add(lblNewLabel_8);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		panel_1_8.add(textField_8);
		
		JPanel panel_1_9 = new JPanel();
		scrollPane.setViewportView(panel_1_9);
		panel_1_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		panel_1_9.add(lblNewLabel_9);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		panel_1_9.add(textField_9);
		
		JPanel panel_1_10 = new JPanel();
		scrollPane.setViewportView(panel_1_10);
		panel_1_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		panel_1_10.add(lblNewLabel_10);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		panel_1_10.add(textField_10);
		
		JPanel panel_1_11 = new JPanel();
		scrollPane.setViewportView(panel_1_11);
		panel_1_11.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		panel_1_11.add(lblNewLabel_11);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		panel_1_11.add(textField_11);
		
		JPanel panel_1_12 = new JPanel();
		scrollPane.setViewportView(panel_1_12);
		panel_1_12.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		panel_1_12.add(lblNewLabel_12);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		panel_1_12.add(textField_12);
		
		JPanel panel_1_13 = new JPanel();
		scrollPane.setViewportView(panel_1_13);
		panel_1_13.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_13 = new JLabel("New label");
		panel_1_13.add(lblNewLabel_13);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		panel_1_13.add(textField_13);
		
		JPanel panel_1_14 = new JPanel();
		scrollPane.setViewportView(panel_1_14);
		panel_1_14.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_14 = new JLabel("New label");
		panel_1_14.add(lblNewLabel_14);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		panel_1_14.add(textField_14);
		
		JPanel panel_1_15 = new JPanel();
		scrollPane.setViewportView(panel_1_15);
		panel_1_15.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_15 = new JLabel("New label");
		panel_1_15.add(lblNewLabel_15);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		panel_1_15.add(textField_15);
		
		Button button = new Button("New button");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		button.setBounds(42, 308, 70, 22);
		add(button);

	}
}
