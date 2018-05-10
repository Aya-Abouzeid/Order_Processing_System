package GUI;

import java.io.File;
import java.sql.SQLException;
import Library.DBMaster;
import Library.Database;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Library.DBMaster;

public class GUI extends Application {
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		GridPane gridPane = new GridPane();
		Group group = new Group();
		scene = new Scene(group, 980, 610);
		AddImage(group);
		
		addBtn(gridPane, primaryStage);
		group.getChildren().add(gridPane);
		primaryStage.setTitle("Order Processing System");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private void AddImage(Group group){
		File file = new File("Library2.jpg");
		Image background = new Image(file.toURI().toString());
        ImageView img = new ImageView(background);
        img.setPreserveRatio(true);
        img.setFitWidth(1000);
        img.setFitHeight(800);
        group.getChildren().add(img);
	}
	private void addBtn(GridPane gridPane, Stage primaryStage) {

	
		Button Login = new Button();
		Login.setText("Login");
		Login.setPrefSize(150, 35);

		Button SignUp = new Button();
		SignUp.setText("SignUp");
		SignUp.setPrefSize(150, 35);

		
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
				
					try {
						LoginGUI StartLogin = new LoginGUI(primaryStage , scene);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		});
		
		SignUp.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
					try {
						SignUpGUI StartSignUp = new SignUpGUI(primaryStage , scene);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});

	}

	public void startApp(String args[]) throws ClassNotFoundException, SQLException {
		launch(args);
	}

}
