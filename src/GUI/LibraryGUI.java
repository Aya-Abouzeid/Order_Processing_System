package GUI;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Library.DBMaster;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class LibraryGUI {
	private Button Back = new Button();
	private Button Search = new Button();
	private Scene managerScene;
	private Label ISBN = new Label("ISBN");
	private Label PID = new Label("PID");
	private Label Title = new Label("Title");
	private Label Year = new Label("Year");
	private Label Authors = new Label("Authors separated by ','");
	private Label Price = new Label("Price");
	private TextField UIDTf = new TextField();
	private Label Category = new Label("Category");
	private Label Stock = new Label("Stock");
	private Label Threshold = new Label("Threshold");
	private TextField ISBNTf = new TextField();
	private TextField PIDTf = new TextField();
	private TextField AuthorsTf = new TextField();
	private TextField TitleTf = new TextField();
	private TextField PriceTf = new TextField();
	private TextField CategoryTf = new TextField();
	private TextField StockTf = new TextField();
	private TextField ThresholdTf = new TextField();
	private DatePicker datePicker = new DatePicker();
	private String[] data = new String[8];
	private DBMaster dbm;
	private  LocalDate date;
	private String[] authorsList;

	Button addBook = new Button();
	Button modify = new Button();
	Button order = new Button();
	Button confirm = new Button();
	Button promote = new Button();
	Stage stage;
	Scene CustomerScene;
	
	public LibraryGUI( Stage primaryStage, Scene s) throws ClassNotFoundException, SQLException {
		dbm = DBMaster.getDBMaster();
		stage = primaryStage;
		CustomerPage();
		managerScene = s;
	}
	public void CustomerPage(){
		// String path;
			
		Group group = new Group();
		Scene scene = new Scene(group, 980, 610);
		CustomerScene = scene;
		GridPane gridPane = new GridPane();
		
		//Add btns to GUI
		FillGUI(gridPane);
		
		//Decide what to do with each btn click
		//AddFunctionality();
		addFunctionality();
		AddImage(group);
		group.getChildren().add(gridPane);
		gridPane.setPadding(new Insets(49, 49, 56, 30));
		gridPane.setAlignment(Pos.TOP_LEFT);

		gridPane.setVgap(3);
		gridPane.setHgap(7);
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


		ISBN.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(ISBN, 1, 2);

		PID.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(PID, 1, 5);
		
		Authors.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Authors, 1, 8);
				
		Title.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Title, 1, 11);
		
		Year.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Year, 1, 14);
		
		Price.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Price, 1, 17);

		Category.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Category, 1, 20);
		Stock.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Stock, 1, 23);
		Threshold.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Threshold, 1, 26);
		
		
		gp.add(ISBNTf, 3, 2);

		gp.add(PIDTf, 3, 5);
		
		gp.add(AuthorsTf, 3, 8);

		gp.add(TitleTf, 3, 11);
		
		gp.add(datePicker, 3, 14);
		
		gp.add(PriceTf, 3, 17);
		
		gp.add(CategoryTf, 3, 20);	
		gp.add(StockTf, 3, 23);
		gp.add(ThresholdTf, 3, 26);
		
		addBook.setText("Add Book");
		addBook.setPrefSize(200, 35);
		gp.add(addBook, 3, 29);
		addBook.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		
		modify.setText("Modify Book");
		modify.setPrefSize(200, 35);
		gp.add(modify, 3, 32);
		modify.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		order.setText("Order Book");
		order.setPrefSize(200, 35);
		gp.add(order, 6, 29);
		order.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		confirm.setText("Confirm Order");
		confirm.setPrefSize(200, 35);
		gp.add(confirm , 6, 32);
		confirm.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		Label UID = new Label("        UID: ");
		UID.setStyle("-fx-font: normal bold 32px 'serif' ");
		UID.setPadding(new Insets(0, 0, 5, 5));
		gp.add(UID,5, 2);
		gp.add(UIDTf,6 , 2);
		
		promote.setText("Promote customer");
		promote.setPrefSize(200, 35);
		gp.add(promote , 6, 5);
		promote.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 20px 'serif' ;");

		
		Back.setText("< Back");
		Back.setPrefSize(119, 35);
		gp.add(Back, 1, 32);
		Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		
		gp.setAlignment(Pos.TOP_LEFT);
		gp.setPadding(new Insets(0, 0, 5, 5));
		

} 
	

public void addFunctionality(){
	
	datePicker.setOnAction(event -> {
	     date = datePicker.getValue();
	});
	Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			stage.setScene(managerScene);
			stage.show();
		}
	});
	Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			stage.setScene(managerScene);
			stage.show();
		}
	});
	promote.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			if(UIDTf.getText().trim() == "")
				showAlert("Promotion Failed","Please Insert User ID");
			else{
				try {
					int success = dbm.promoteUser(Integer.valueOf(UIDTf.getText().trim()));
				System.out.println(success);
					if(success <= 0){
						showAlert("Promotion Failed","Invalid User ID");
					}
					else {
						showAlert("Promotion Success","User Promoted");
					}
					UIDTf.setText("");

				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	});
	addBook.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			if(emptyTextFields("add") || (date == null) )
				showAlert("Add Failed","Please Fill All Fields");
			else{
				addData();
				try {
					dbm.addBook(data , authorsList);
					showAlert("Success","New Book Added");
					clearFields();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					showAlert("Adding Failed","Double Check Book's Info");
				}
			}
		}
	});
	modify.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			if( AuthorsTf.getText().trim() == ",")
				showAlert("Modify Failed","Please Specify Valid Authors");
		else if(emptyTextFields("modify"))
				showAlert("Modify Failed","Please Specify ISBN along with one additional attribute");
			else{
				addData();
				try {
					dbm.modifyBook(data);
					//ViewerGUI x2 = new ViewerGUI(stage,CustomerScene,x);
										

				} catch (SQLException e) {
					// TODO Auto-generated catch block
				//	showAlert("Update Failed","Username or Email already Used");
				}
				
			}
		}
	});
}

private void addData(){
	
authorsList = AuthorsTf.getText().trim().split(",");	
	if(ISBNTf.getText().trim() == "")
		data[0]="";
	else
		data[0]=ISBNTf.getText().trim();
	
	if(PIDTf.getText().trim() == "")
		data[1]="";
	else
		data[1]=PIDTf.getText().trim();
	
	if(TitleTf.getText().trim() == "")
		data[2]="";
	else
		data[2]=TitleTf.getText().trim();
	
	if(date != null && date.toString() == "")
		data[3]="";
	else
		data[3]=date.toString();
	
	if(PriceTf.getText().trim() == "")
		data[4]="";
	else
		data[4]=PriceTf.getText().trim();
	
	if(CategoryTf.getText().trim() == "")
		data[5]="";
	else
		data[5]=CategoryTf.getText().trim();
	
	if(ThresholdTf.getText().trim() == "")
		data[6]="";
	else
		data[6]=ThresholdTf.getText().trim();
	
	if(StockTf.getText().trim() == "")
		data[7]="";
	else
		data[7]=StockTf.getText().trim();
	
}
private boolean emptyTextFields(String type){
	if(type.equals("modify")&&(  ISBNTf.getText().trim().isEmpty()))
		return true;
	 
	if(type.equals("add")&& AuthorsTf.getText().trim() == "")
		 return true;
	if(type.equals("add")&& AuthorsTf.getText().trim() == ",")
		 return true;
	if(type.equals("add")&&(ISBNTf.getText().trim().isEmpty()
			|| (PIDTf.getText().trim().isEmpty())
			|| (TitleTf.getText().trim().isEmpty())
			|| (date != null && date.toString() == "")
			|| (PriceTf.getText().trim().isEmpty())
			|| (CategoryTf.getText().trim().isEmpty())
			|| (ThresholdTf.getText().trim().isEmpty())
			|| (StockTf.getText().trim().isEmpty())
			|| (AuthorsTf.getText().trim().isEmpty()))

			) return true;
	return false;
}
private void showAlert(String title , String msg){
	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle(title);
	alert.setHeaderText(msg);
	alert.show();
}

private void clearFields(){
	ISBNTf.setText("");
	PIDTf.setText("");
	TitleTf.setText("");
	PriceTf.setText("");
	CategoryTf.setText("");
	AuthorsTf.setText("");
	ThresholdTf.setText("");
	StockTf.setText("");
	datePicker.setValue(null);
}
}


/**
	1. Add new books
	2. Modify existing books
	3. Place orders for books
	4. Confirm orders
	5. Promote registered customers to have managers credentials
	6. View the following reports on sales
	a. The total sales for books in the previous month
	b. The top 5 customers who purchase the most purchase amount in descending order for the last
	three months
	c. The top 10 selling books for the last three months
	Assume that the system stores book sales and other related data for the last 3 months.*/
