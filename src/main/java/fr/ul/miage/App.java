package fr.ul.miage;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

	public void start(Stage primaryStage) {
		Parent root =  null;
		try {
			root = FXMLLoader.load(getClass().getResource("Baignoire.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		primaryStage.setTitle("TP2 - Programmation Objet Avancée");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		//Démarrer le menu
		launch(args);
	}

}
