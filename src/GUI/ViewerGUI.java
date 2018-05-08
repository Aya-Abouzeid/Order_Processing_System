package GUI;

import java.awt.Font;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Library.DBMaster;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewerGUI {
	private DBMaster dbm;
	private Scene MainScene;
	private Stage stage;
	private ResultSet resultSet;
    private TableView<String> table = new TableView();

	public ViewerGUI( Stage primaryStage , Scene s) throws ClassNotFoundException, SQLException {
		dbm = DBMaster.getDBMaster();
		MainScene = s;
		 stage = primaryStage;
		 trial();
	}
	public void trial()
	{
		Group group = new Group();
		Scene scene = new Scene(group, 980, 630);

		GridPane gridPane = new GridPane();
		
		final ObservableList<String> data =
	            FXCollections.observableArrayList("Amira", "Nabil");
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("ISBN");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory("ISBN"));
 
        TableColumn lastNameCol = new TableColumn("PID");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory("PID"));
 
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory("email"));
 
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        table.setItems(data);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        //vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
       
        Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}
	});
	}
	public void ViewerPage(ResultSet rs){
		TableColumn firstNameCol = new TableColumn("ISBN");
        TableColumn lastNameCol = new TableColumn("PID");
       // TableColumn emailCol = new TableColumn("Email");
        
        table.getColumns().addAll(firstNameCol, lastNameCol);
        
		resultSet = rs;
	}

}
