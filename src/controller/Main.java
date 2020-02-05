package controller;

import model.Model;
import view.MainView;

public class Main {

	public static void main(String[] args) {
		Controller controller = new Controller();
		Model model = new Model();
		MainView view = new MainView();
		controller.setModel(model);
		controller.setView(view);
		model.setController(controller);
		view.setController(controller);

		controller.refreshTable();
		controller.setViewVisible(true);
	}
}
