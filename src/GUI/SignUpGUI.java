package GUI;

import java.io.File;
import java.sql.SQLException;
import GUI.CustomerGUI;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Library.*;

public class SignUpGUI extends GUI{
	private Stage stage;
	private Button Back = new Button();
	private Scene MainScene;
	private Button Register = new Button();
	private TextField UNameTf = new TextField();
	private PasswordField UPassTf = new PasswordField();
	private TextField UFNameTf = new TextField();
	private TextField ULNameTf = new TextField();
	private TextField EmailTf = new TextField();
	private TextField AddressTf = new TextField();
	private DBMaster dbm;

	public SignUpGUI( Stage primaryStage , Scene s) throws ClassNotFoundException, SQLException {
		dbm = dbm.getDBMaster();
		MainScene = s;
		stage = primaryStage;
		SignUpPage();
	}
	private void SignUpPage(){
	    Group group = new Group();

		Scene scene = new Scene(group, 980, 630);

		GridPane gridPane = new GridPane();
		FillGUI(gridPane);
		AddFuncionality();
		AddImage( group);
		group.getChildren().add(gridPane);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(2);
		gridPane.setPadding(new Insets(49, 49, 56, 190));

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}

		});
 }
 private void AddImage(Group group){
		File file = new File("books2.jpg");
		Image background = new Image(file.toURI().toString());
     ImageView img = new ImageView(background);
     img.setPreserveRatio(true);
     group.getChildren().add(img);
	}
 private void FillGUI(GridPane gp){
		Label UName = new Label("User Name: ");
		UName.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(UName, 1, 2);

		Label UPass = new Label("Password: ");
		UPass.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(UPass, 1, 5);
		
		Label FName = new Label("First Name: ");
		FName.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(FName, 1, 8);
		
		Label LName = new Label("Last Name: ");
		LName.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(LName, 1, 11);
		
		Label Email = new Label("Email: ");
		Email.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Email, 1, 14);
		
		Label Address = new Label("Shipping Address: ");
		Address.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Address, 1, 17);
		
		gp.add(UNameTf, 3, 2);
		
		gp.add(UPassTf, 3, 5);
		
		gp.add(UFNameTf, 3, 8);
		
		gp.add(ULNameTf, 3, 11);
		
		gp.add(EmailTf, 3, 14);
		
		gp.add(AddressTf, 3, 17);
		
		Register.setText("Register");
		Register.setPrefSize(140, 35);
		gp.add(Register, 3, 20);
		
		Back.setText("< Back");
		Back.setPrefSize(119, 35);
		gp.add(Back, 1, 23);
		
		Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		Register.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		gp.setAlignment(Pos.BOTTOM_CENTER);
		gp.setPadding(new Insets(49, 49, 56, 280));

}
 
	private void AddFuncionality(){
		
		Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				stage.setScene(MainScene);
				stage.show();
			}
		});
		Register.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(!EmptyTextFields()){
					int Success = -1;
					try {
						Success = dbm.register(UNameTf.getText(),UPassTf.getText(),EmailTf.getText(),
								UFNameTf.getText() , ULNameTf.getText(),AddressTf.getText());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
							if(Success != -1) {
								ShowAlert("Register Success","Welcome, " + UNameTf.getText());
								try {
									CustomerGUI startCustomer = new CustomerGUI(stage, MainScene);
								} catch (ClassNotFoundException | SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else
								ShowAlert("Register Error","User Already Exists");
				}else {
					ShowAlert("Error Info Missing" ,"Please Fill All The Information" );
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
		if(UNameTf.getText().trim().isEmpty()|| UPassTf.getText().trim().isEmpty()
				||UFNameTf.getText().trim().isEmpty() || ULNameTf.getText().trim().isEmpty()
				|| EmailTf.getText().trim().isEmpty() || AddressTf.getText().trim().isEmpty())
			return true;
		return false;
	}
}