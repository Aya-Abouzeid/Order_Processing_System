package GUI;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InfoGUI {
	
	protected Scene MainScene;
	protected Scene CustomerScene;
	protected Stage stage;

	public InfoGUI( Stage primaryStage, Scene s) {
		stage = primaryStage;
		MainScene = s;
		InfoPage();
	}
	
	public void InfoPage(){
		
		Group group = new Group();
		Scene scene = new Scene(group, 980, 630);
		CustomerScene = scene;
		GridPane gridPane = new GridPane();
		
		//Add btns to GUI
		FillGUI(gridPane);
		
		//Decide what to do with each btn click
		AddFunctionality();

		group.getChildren().add(gridPane);
		gridPane.setPadding(new Insets(49, 49, 56, 280));
		gridPane.setAlignment(Pos.CENTER);

		gridPane.setVgap(35);
		gridPane.setHgap(7);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}

		});
 }
	public void FillGUI(GridPane gp){
	}
	public void AddFunctionality(){
	}

}
