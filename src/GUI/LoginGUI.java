package GUI;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

import Library.DBMaster;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LoginGUI {
	private Stage stage;
	private Scene MainScene;
	private Button Back = new Button();
	private Button Login = new Button();
	private TextField UNameTf = new TextField();
	private PasswordField passwordField = new PasswordField();
	private DBMaster dbm;
	public LoginGUI( Stage primaryStage ,Scene s) throws ClassNotFoundException, SQLException {
		dbm = dbm.getDBMaster();
		MainScene = s;
		stage = primaryStage;
		LoginPage();
	}
 public void LoginPage(){
		Group group = new Group();
		Scene scene = new Scene(group, 980, 630);
		GridPane gridPane = new GridPane();
		FillGUI(gridPane);
		AddFuncionality();
		AddImage(group);
		group.getChildren().add(gridPane);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}

		});
		
 }
 public void AddImage(Group group){
		File file = new File("Books2.jpg");
		Image background = new Image(file.toURI().toString());
     ImageView img = new ImageView(background);
     img.setPreserveRatio(true);
     group.getChildren().add(img);
	}
 public void FillGUI(GridPane gp){
	 		Label UName = new Label("User Name: ");
			UName.setStyle("-fx-font: normal bold 32px 'serif' ");
			gp.add(UName, 8, 2);

			Label UPass = new Label("Password: ");
			UPass.setStyle("-fx-font: normal bold 32px 'serif' ");
			gp.add(UPass, 8, 5);
			
			gp.add(UNameTf, 12, 2);;
			gp.add(passwordField, 12, 5);
			
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
		Login.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				if(!EmptyTextFields()){
					int Success = dbm.signIn(UNameTf.getText(),passwordField.getText());
							if(Success != -1) {
								ShowAlert("Login Success","Welcome, " + UNameTf.getText());
								try {
									CustomerGUI StartLogin = new CustomerGUI(stage , MainScene);
								} catch (ClassNotFoundException | SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else
								ShowAlert("Register Error","User Already Exists");
				}else {
					ShowAlert("Error Info Missing" ,"User Name or Password is Missing" );
				}
			}
		});
		
	}
	
	private void ShowAlert(String title , String msg){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(msg);
		alert.show();
	}
	private boolean EmptyTextFields(){
		if(UNameTf.getText().trim().isEmpty()|| passwordField.getText().trim().isEmpty())
			return true;
		return false;
	}
}
