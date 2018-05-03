package GUI;

import java.net.URISyntaxException;
import javafx.application.Application;
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
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GUI extends Application {
	protected Scene scene;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		GridPane gridPane = new GridPane();
		addBtn(gridPane, primaryStage);
		Group group = new Group();
		group.getChildren().add(gridPane);
		scene = new Scene(group, 980, 630);
		primaryStage.setTitle("Order Processing System");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public Scene getScene(){
		return scene;
	}
	public void addBtn(GridPane gridPane, Stage primaryStage) {

		//Label label = new Label("Number Of Nodes : ");

		//TextField tf = new TextField();

		Button Login = new Button();
		Login.setText("Login");
		Login.setPrefSize(119, 35);

		Button SignUp = new Button();
		SignUp.setText("SignUp");
		SignUp.setPrefSize(119, 35);

		
		gridPane.setVgap(35);
		gridPane.setHgap(7);
		gridPane.setAlignment(Pos.CENTER);

		gridPane.add(Login, 20, 5);
		gridPane.add(SignUp, 20, 7);

		Login.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		SignUp.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");


		gridPane.setAlignment(Pos.BOTTOM_CENTER);
		gridPane.setPadding(new Insets(49, 49, 56, 280));

		Login.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
					CustomerGUI StartLogin = new CustomerGUI(primaryStage , scene);
			}
		});
		
		SignUp.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
					SignUpGUI StartSignUp = new SignUpGUI(primaryStage);
			}
		});

	}

	public void startApp(String args[]) {
		launch(args);
	}

}
