package controller;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Model;
import view.MainView;

public class Controller {
	private Model model;
	private MainView view;

	public void refreshTable() {
		try {
			view.refreshTable(model.getProducts());
		} catch (Exception e) {
			final JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "Error al conectarse con la base de datos", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteProduct(int id) {
		if (JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar el producto?", "Confirmar",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			try {
				model.deleteProduct(id);
			} catch (Exception e) {
				final JPanel panel = new JPanel();
				JOptionPane.showMessageDialog(panel, "Error al eliminar el producto", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			refreshTable();
		}
	}

	public void insertProduct(String name, float quantity, float amount) {
		try {
			model.insertProduct(name, quantity, amount);
		} catch (Exception e) {
			final JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "Error al añadir el nuevo producto", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		refreshTable();
	}

	public void setViewVisible(boolean isVisible) {
		view.setVisible(isVisible);
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setView(MainView view) {
		this.view = view;
	}
}
