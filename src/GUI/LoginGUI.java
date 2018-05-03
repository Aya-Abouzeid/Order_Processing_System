package GUI;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginGUI {
	protected Stage stage;
	protected Scene MainScene;
	protected Button Back = new Button();

	public LoginGUI( Stage primaryStage ,Scene s) {
		MainScene = s;
		stage = primaryStage;
		LoginPage();
	}
 public void LoginPage(){
	// String path;
		
		Group group = new Group();

		Scene scene = new Scene(group, 980, 630);

		GridPane gridPane = new GridPane();
		FillGUI(gridPane);
		AddFuncionality();
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
			gp.add(UName, 8, 2);

			Label UPass = new Label("Password: ");
			UPass.setStyle("-fx-font: normal bold 32px 'serif' ");
			gp.add(UPass, 8, 5);
			
			
			
			
			TextField UNameTf = new TextField();
			gp.add(UNameTf, 12, 2);
			
			PasswordField passwordField = new PasswordField();
			gp.add(passwordField, 12, 5);
			
			Button Login = new Button();
			Login.setText("Login");
			Login.setPrefSize(119, 35);
			gp.add(Login, 12, 7);
			
			Back.setText("< Back");
			Back.setPrefSize(119, 35);
			gp.add(Back, 1, 9);
			
			Login.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
			Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

			
			
			gp.setAlignment(Pos.CENTER);
			gp.setVgap(40);
			gp.setHgap(2);
			gp.setPadding(new Insets(49, 49, 56, 190));

 }
	public void AddFuncionality(){
		
		Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				stage.setScene(MainScene);
				stage.show();
			}
		});
		
	}
}
