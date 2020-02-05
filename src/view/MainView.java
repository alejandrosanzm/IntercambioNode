package view;

import javax.swing.JFrame;

import controller.Controller;
import model.ShoppingList;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class MainView {
	private Controller controller;
	private JFrame frame;
	private JTable table;
	private JButton btnDelete;
	private JButton btnRefresh;
	private JPanel panel_1;
	private JPanel panel_2;
	private JTextField txtName;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JPanel panel_3;
	private JButton btnSave;

	public MainView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 634, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "quantity", "Amount", "Total" }));
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1);

		btnDelete = new JButton("Delete");
		panel_1.add(btnDelete);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if ((int) table.getModel().getValueAt(table.getSelectedRow(), 0) != 0) {
					controller.deleteProduct((int) table.getModel().getValueAt(table.getSelectedRow(), 0));
				}
			}
		});

		btnRefresh = new JButton("Refresh");
		panel_1.add(btnRefresh);
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.refreshTable();
			}
		});

		panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 4, 0, 0));

		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setToolTipText("Name");
		panel_2.add(txtName);
		txtName.setColumns(10);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		spinner.setToolTipText("Quantity");
		panel_2.add(spinner);

		spinner_1 = new JSpinner();
		spinner_1.setToolTipText("Amount");
		spinner_1.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		panel_2.add(spinner_1);

		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_3);

		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.insertProduct(txtName.getText(), (float) spinner.getValue(), (float) spinner_1.getValue());
				txtName.setText("Name");
				spinner.setValue(0);
				spinner_1.setValue(0);
			}
		});
		panel_3.add(btnSave);
		frame.setVisible(false);
	}

	public void refreshTable(HashMap<Integer, ShoppingList> model) {
		DefaultTableModel newModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		newModel.addColumn("ID");
		newModel.addColumn("Name");
		newModel.addColumn("Quantity");
		newModel.addColumn("Amount");
		newModel.addColumn("Total");

		for (ShoppingList sl : model.values()) {
			newModel.addRow(new Object[] { sl.getId(), sl.getName(), sl.getQuantity(), sl.getAmount(),
					(sl.getAmount() * sl.getQuantity()) });
		}
		table.setModel(newModel);
		newModel.fireTableDataChanged();
	}

	public void setVisible(boolean isVisible) {
		frame.setVisible(isVisible);
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public JTable getTable() {
		return table;
	}
}
