package GUI;

import java.io.File;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Library.DBMaster;

public class InfoGUI {
	private Button Back = new Button();
	private Button Update = new Button();
	private Scene CustomerScene;
	private Stage stage;
	private DBMaster dbm;
	private RadioButton UName = new RadioButton("User Name");
	private RadioButton UPass = new RadioButton("Password");
	private RadioButton FName = new RadioButton("First Name");
	private RadioButton LName = new RadioButton("Last Name");
	private RadioButton Email = new RadioButton("Email");
	private RadioButton Address = new RadioButton("Shipping Address");
	private TextField UNameTf = new TextField();
	private PasswordField UPassTf = new PasswordField();
	private TextField FNameTf = new TextField();
	private TextField LNameTf = new TextField();
	private TextField EmailTf = new TextField();
	private TextField AddressTf = new TextField();
	private String[] data = new String[6];
	private Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private Pattern usernamePattern = Pattern.compile("^[a-z][a-z0-9_-]{3,15}$");
	private Pattern namePattern = Pattern.compile("[aA-zZ ']+$");


	public InfoGUI( Stage primaryStage, Scene s) throws ClassNotFoundException, SQLException {
		dbm = DBMaster.getDBMaster();
		CustomerScene = s;
		stage = primaryStage;
		InfoPage();
	}
	
	public void InfoPage(){
		
		Group group = new Group();
		Scene scene = new Scene(group, 980, 630);
		GridPane gridPane = new GridPane();
		
		//Add btns to GUI
		FillGUI(gridPane);
		
		//Decide what to do with each btn click
		AddFunctionality();
		RadioBtnFunctionality();
		AddImage(group);
		group.getChildren().add(gridPane);
		gridPane.setPadding(new Insets(49, 49, 56, 280));
		gridPane.setAlignment(Pos.CENTER);

		gridPane.setVgap(10);
		gridPane.setHgap(2);
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
		

		UName.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(UName, 1, 2);

		UPass.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(UPass, 1, 5);
		
		FName.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(FName, 1, 8);
		
		LName.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(LName, 1, 11);
		
		Email.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Email, 1, 14);

		Address.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Address, 1, 17);
		
		
		UNameTf.setVisible(false);
		gp.add(UNameTf, 3, 2);

		UPassTf.setVisible(false);
		gp.add(UPassTf, 3, 5);
		
		FNameTf.setVisible(false);
		gp.add(FNameTf, 3, 8);
		
		LNameTf.setVisible(false);
		gp.add(LNameTf, 3, 11);
		
		EmailTf.setVisible(false);
		gp.add(EmailTf, 3, 14);
		
		AddressTf.setVisible(false);
		gp.add(AddressTf, 3, 17);
		
		Update.setText("Update Info");
		Update.setPrefSize(200, 35);
		gp.add(Update, 3, 20);
		
		Back.setText("< Back");
		Back.setPrefSize(119, 35);
		gp.add(Back, 1, 21);
		
		Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		Update.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		gp.setAlignment(Pos.BOTTOM_CENTER);
		gp.setPadding(new Insets(49, 49, 56, 280));

	}
	public void RadioBtnFunctionality(){
		
		UName.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(UName.isSelected()){
	        		UNameTf.setVisible(true);
	            }
	            else{
	            	UNameTf.clear();
	        		UNameTf.setVisible(false);
	            }
	        }
	    });
		UPass.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(UPass.isSelected()){
	            	UPassTf.setVisible(true);
	            }
	            else{
	            	UPassTf.clear();
	            	UPassTf.setVisible(false);
	            }
	        }
	    });
		FName.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(FName.isSelected()){
	            	FNameTf.setVisible(true);
	            }
	            else{
	            	FNameTf.clear();
	            	FNameTf.setVisible(false);
	            }
	        }
	    });
		LName.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(LName.isSelected()){
	            	LNameTf.setVisible(true);
	            }
	            else{
	            	LNameTf.clear();
	            	LNameTf.setVisible(false);
	            }
	        }
	    });
		Email.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(Email.isSelected()){
	            	EmailTf.setVisible(true);
	            }
	            else{
	            	EmailTf.clear();
	            	EmailTf.setVisible(false);
	            }
	        }
	    });
		Address.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(Address.isSelected()){
	            	AddressTf.setVisible(true);
	            }
	            else{
	            	AddressTf.clear();
	            	AddressTf.setVisible(false);
	            }
	        }
	    });
	}
	public void AddFunctionality(){
		
		Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				stage.setScene(CustomerScene);
				stage.show();
			}
		});
		Update.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(emptyTextFields())
					showAlert("Update Failed","Please Fill All Selected Fields");
				else if(checkPattern()){
					
				}
				else{
					addData();
					try {
						
						dbm.updateInfo(data);
						showAlert("Update Success","Info Updated");
						stage.setScene(CustomerScene);
						stage.show();

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						showAlert("Update Failed","Username or Email already Used");
					}
					
				}
			}
		});
	}
	
	private boolean checkPattern(){
		
		if(( UName.isSelected()&& !usernamePattern.matcher(UNameTf.getText().trim()).matches())){
			showAlert("Update Failed","Invalid Username");

			return true;

		}
				
				if (FName.isSelected()&& !namePattern.matcher(FNameTf.getText().trim()).matches()){
					showAlert("Update Failed","Invalid First Name");

					return true;

				}
				if (LName.isSelected()&& !namePattern.matcher(LNameTf.getText().trim()).matches()){
					showAlert("Update Failed","Invalid Last Name");

					return true;

				}
				if (Email.isSelected()&& !emailPattern.matcher(EmailTf.getText().trim()).matches()){
					showAlert("Update Failed","Invalid Email");

					return true;

				}
				
		return false;
		
	}
	private void addData(){
		if(!UName.isSelected())
			data[0]="";
		else
			data[0]=UNameTf.getText().trim();
		if(!UPass.isSelected())
			data[1]="";
		else
			data[1]=UPassTf.getText().trim();
		if(!Email.isSelected())
			data[2]="";
		else
			data[2]=EmailTf.getText().trim();
		if(!FName.isSelected())
			data[3]="";
		else
			data[3]=FNameTf.getText().trim();
		if(!LName.isSelected())
			data[4]="";
		else
			data[4]=LNameTf.getText().trim();
		if(!Address.isSelected())
			data[5]="";
		else
			data[5]=AddressTf.getText().trim();
	}
	private boolean emptyTextFields(){
		if(( UName.isSelected()&& UNameTf.getText().trim().isEmpty()
				|| (UPass.isSelected()&& UPassTf.getText().trim().isEmpty())
				|| (FName.isSelected()&& FNameTf.getText().trim().isEmpty())
				|| (LName.isSelected()&& LNameTf.getText().trim().isEmpty())
				|| (Email.isSelected()&& EmailTf.getText().trim().isEmpty())
				|| (Address.isSelected()&& AddressTf.getText().trim().isEmpty()))
				)
			return true;
		return false;
	}
	private void showAlert(String title , String msg){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(msg);
		alert.show();
	}

}
