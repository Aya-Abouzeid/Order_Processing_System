package GUI;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Library.DBMaster;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	private Label ISBN2 = new Label("     ISBN");
	private Label ISBN3 = new Label("     ISBN");
	private Label quantity = new Label("   Quantity");
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
	private TextField ISBNTf2 = new TextField();
	private TextField ISBNTf3 = new TextField();
	private TextField quantityTf = new TextField();
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
	private Button addBook = new Button();
	private Button modify = new Button();
	private Button order = new Button();
	private Button confirm = new Button();
	private Button promote = new Button();
	private Stage stage;
	private Scene CustomerScene;
	private Pattern namePattern = Pattern.compile("[aA-zZ ']+$");
	private ObservableList<String> categories = 
		    FXCollections.observableArrayList(
		        "Science",
		        "Art",
		        "Religion",
		        "History",
		        "Geography"
		    );
	private final ComboBox comboBox = new ComboBox(categories);

	
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
		gridPane.setPadding(new Insets(20, 30, 40, 30));
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
		
		comboBox.setValue("");
		gp.add(comboBox, 3, 20);	
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
		gp.add(order, 6, 17);
		order.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		ISBN3.setStyle("-fx-font: normal bold 32px 'serif' ");
		ISBN3.setPadding(new Insets(0, 0, 5, 5));
		gp.add(ISBN3,5, 23);
		gp.add(ISBNTf3,6 , 23);
		
		
		confirm.setText("Confirm Order");
		confirm.setPrefSize(200, 35);
		gp.add(confirm , 6, 26);
		confirm.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		Label UID = new Label("        UID: ");
		UID.setStyle("-fx-font: normal bold 32px 'serif' ");
		UID.setPadding(new Insets(0, 0, 5, 5));
		gp.add(UID,5, 2);
		gp.add(UIDTf,6 , 2);
		
		ISBN2.setStyle("-fx-font: normal bold 32px 'serif' ");
		ISBN2.setPadding(new Insets(0, 0, 5, 5));
		gp.add(ISBN2,5, 11);
		gp.add(ISBNTf2,6 , 11);
		
		quantity.setStyle("-fx-font: normal bold 32px 'serif' ");
		quantity.setPadding(new Insets(0, 0, 5, 5));
		gp.add(quantity,5, 14);
		gp.add(quantityTf,6 , 14);
		
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
	order.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			if(quantityTf.getText().trim().isEmpty() || ISBNTf2.getText().trim().isEmpty()  )
				showAlert("Order Failed","Order Info Missing");
			else{
				try {
					int success = dbm.placeOrder(ISBNTf2.getText().trim(), Integer.valueOf(quantityTf.getText().trim()));
					if(success <= 0){
						showAlert("Order Failed","Invalid Book Info 44");
					}
					else {
						showAlert("Order Success","Order is Placed");
					}
					ISBNTf2.setText("");
					quantityTf.setText("");

				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					showAlert("Order Failed","Invalid Book Info 55");
					ISBNTf2.setText("");
					quantityTf.setText("");
				}
			}
		}
	});
	confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			if(ISBNTf3.getText().trim().isEmpty())
				showAlert("Confirmation Failed","Please Insert ISBN");
			else{
				try {
					int success = dbm.confirmOrder(ISBNTf3.getText().trim());
				System.out.println(success);
					if(success <= 0){
						showAlert("Confirmation Failed","Invalid ISBN 1212");
					}
					else {
						showAlert("Confirmation Success","Books Added to Stock");
					}
					ISBNTf3.setText("");

				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					showAlert("Confirmation Failed","Invalid ISBN  3423");
					ISBNTf3.setText("");
				}
			}
		}
	});
	promote.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			if(UIDTf.getText().trim().isEmpty())
				showAlert("Promotion Failed","Please Insert User ID");
			else{
				try {
					int success = dbm.promoteUser(Integer.valueOf(UIDTf.getText().trim()));
				System.out.println(success);
					if(success <= 0){
						showAlert("Promotion Failed","Invalid User ID 678");
					}
					else {
						showAlert("Promotion Success","User Promoted");
					}
					UIDTf.setText("");

				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					showAlert("Promotion Failed","Invalid User ID 523");
					UIDTf.setText("");
				}
			}
			
		}
	});
	addBook.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
//			 if (!validateCategory())
//				showAlert("Adding Failed","Please Specify Valid Category");
//
//		else 
			if(emptyTextFields("add") || (date == null) )
				showAlert("Add Failed","Please Fill All Fields");
			else{
				if(addData()) {
					
				}
				else if(!validateAuthors())
					showAlert("Adding Failed","Please Specify Valid Authors");
				else {
				try {
					int success = dbm.addBook(data , authorsList);
					if(success != -1) {
					showAlert("Success","New Book Added");
					clearFields();}
					else{
						showAlert("Adding Failed","Error, Invalid Book's Info 99");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					showAlert("Adding Failed","Error, Invalid Book's Info 56");
				}
				}
			}
		}
	});
	modify.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			 if (!validateCategory())
				showAlert("Modify Failed","Please Specify Valid Category");

		else if( AuthorsTf.getText().trim() == ",")
				showAlert("Modify Failed","Please Specify Valid Authors");
		else if(emptyTextFields("modify"))
				showAlert("Modify Failed","Please Specify ISBN along with one additional attribute");
			else{
				addData();
				if(!validateAuthors())
					showAlert("Modify Failed","Please Specify Valid Authors");
				else{
				try {
					int success = dbm.modifyBook(data);
					if (success > 0){
						showAlert("Success","Book Modified");
						clearFields();
					}
					else{
						showAlert("Modify Failed","Error, Double Check Book's Info 1");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					showAlert("Modify Failed","Error, Double Check Book's Info 2");
				}
			}
			}
		}
	});
}
private boolean validateAuthors(){
	if(!AuthorsTf.getText().trim().isEmpty()){
		for(int i =0 ; i<authorsList.length ; i++){
		if(!namePattern.matcher(authorsList[i].trim()).matches()){
				return false;
		}
		authorsList[i] = authorsList[i].trim();
		}
	}
	return true;
}
private boolean validateCategory(){
		if(!CategoryTf.getText().trim().isEmpty() && !namePattern.matcher(CategoryTf.getText().trim()).matches())
			return false;
	
	return true;
}

private boolean addData(){
	
authorsList = AuthorsTf.getText().trim().split(",");	
	if(ISBNTf.getText().trim().isEmpty())
		data[0]="";
	else
		data[0]=ISBNTf.getText().trim();
	
	if(PIDTf.getText().trim().isEmpty())
		data[1]="";
	else if(!PIDTf.getText().trim().matches("\\d+")) {
		showAlert("Adding Failed","Publisher ID should be Integer");
		return true;
	}
	else {
		data[1]=PIDTf.getText().trim();}
	
	if(TitleTf.getText().trim().isEmpty())
		data[2]="";
	else
		data[2]=TitleTf.getText().trim();
	
	if(date != null && date.toString().isEmpty())
		data[3]="";
	else if (date != null)
		data[3]=date.toString();
	else
		data[3] = "";
	
	if(PriceTf.getText().trim().isEmpty())
		data[4]="";
	else {
		try {
		     Float.parseFloat(PriceTf.getText().trim());
				data[4]=PriceTf.getText().trim();
		}
		catch (NumberFormatException ex) {
			showAlert("Adding Failed","Price should be a Number");
			return true;		}
	}
	
	if(comboBox.getValue().toString().isEmpty())
		data[5]="";
	else
		data[5]=comboBox.getValue().toString();
	
	if(ThresholdTf.getText().trim().isEmpty())
		data[6]="";
	else if(!ThresholdTf.getText().trim().matches("\\d+")) {
		showAlert("Adding Failed","Threshold should be Integer");
		return true;
	}
	else
		data[6]=ThresholdTf.getText().trim();
	
	if(StockTf.getText().trim().isEmpty())
		data[7]="";
	else if(!StockTf.getText().trim().matches("\\d+")) {
		showAlert("Adding Failed","Stock should be Integer");
		return true;
	}
	else if(Integer.valueOf(StockTf.getText().trim()) < Integer.valueOf(ThresholdTf.getText().trim())) {
		showAlert("Adding Failed","Stock must be larger than threshold");
		return true;
	}
	else
		data[7]=StockTf.getText().trim();
	
	return false;
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
			|| (comboBox.getValue().toString().isEmpty())
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
	comboBox.setValue("");
	AuthorsTf.setText("");
	ThresholdTf.setText("");
	StockTf.setText("");
	datePicker.setValue(null);
}
}
