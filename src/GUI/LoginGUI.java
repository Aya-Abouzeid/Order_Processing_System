package GUI;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class LoginGUI {
	Stage stage;
	public LoginGUI( Stage primaryStage) {
		
		stage = primaryStage;
		LoginPage();
		//graph = new double[number + 1][number + 1];
	}
 public void LoginPage(){
	// String path;
		
		Group group = new Group();

		Scene scene = new Scene(group, 980, 630);

		GridPane gridPane = new GridPane();
		FillGUI(gridPane);
		group.getChildren().add(gridPane);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}

		});
		
 }
 
 public void FillGUI(GridPane gp){
	 		Label UName = new Label("User Name: ");
			UName.setStyle("-fx-font: normal bold 32px 'serif' ");
			gp.add(UName, 1, 2);

			Label UPass = new Label("Password: ");
			UPass.setStyle("-fx-font: normal bold 32px 'serif' ");
			gp.add(UPass, 1, 5);
			
			
			
			
			TextField UNameTf = new TextField();
			gp.add(UNameTf, 3, 2);
			
			TextField UPassTf = new TextField();
			gp.add(UPassTf, 3, 5);
			
			Button Login = new Button();
			Login.setText("Login");
			Login.setPrefSize(119, 35);
			gp.add(Login, 20, 20);
			
			
			
			gp.setAlignment(Pos.BOTTOM_CENTER);
			gp.setPadding(new Insets(49, 49, 56, 280));

 }
	
}
