
/*
 * Help from:
 * http://www.java2s.com/Tutorials/Java/JavaFX/0590__JavaFX_ComboBox.htm 
 * 
 * 
 * 
 */


import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage; 

public class GUI extends Application
{
	int width = 500; 
	int height = 500; 
	public static void main(String[] args)
	{ 
		launch(args);  
	}
	
	@Override 
	public void start(Stage primaryStage)
	{
		// scene is on top of stage root connects 
		primaryStage.setTitle("Cat-o-log");
		StackPane root = new StackPane(); 
		Scene startPage = new Scene(root, width, height);
		GridPane grid = new GridPane(); 
		grid.setVgap(10);
		grid.setHgap(10);
		
		
		
		root.setStyle("-fx-background-color: green;");
		
		// search bar 
		TextField searchBar = new TextField(); 
		searchBar.setPromptText("Search");
		searchBar.setMaxHeight(50);
		searchBar.getText(); 
		searchBar.prefHeightProperty().bind(root.heightProperty());
	    searchBar.prefWidthProperty().bind(root.widthProperty());
		
		// just there to look pretty for now
		Button login = new Button("Login");
		login.setMaxHeight(50);
	    login.prefHeightProperty().bind(root.heightProperty());
	    login.prefWidthProperty().bind(root.widthProperty());
	    
		// put the login stuff here 
		login.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("This will be where you login in!");
	        }
		}); 
		
		// drop down sortBy  
		ComboBox<String> sortByDropDown = new ComboBox<String>();
		sortByDropDown.getItems().addAll("Name", "Number of Legs");
		sortByDropDown.setValue(null); 
		sortByDropDown.setMaxHeight(50);
		sortByDropDown.prefHeightProperty().bind(root.heightProperty());
		sortByDropDown.prefWidthProperty().bind(root.widthProperty());
		
		
		// the check boxes that will be filters 
		CheckBox chkColdBlooded = new CheckBox("Cold Blooded");
		chkColdBlooded.setMaxHeight(50);
		chkColdBlooded.prefHeightProperty().bind(root.heightProperty());
		chkColdBlooded.prefWidthProperty().bind(root.widthProperty());
		
		chkColdBlooded.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Filter includes only cold blooded animals");
	        }
		});
		
		
		Text T = new Text("Filters"); 
		
		// dont change the order of the code lines!!!! 
		grid.addColumn(0,T, chkColdBlooded); // add filters to this column
		grid.addRow(0, searchBar, sortByDropDown, login);
		root.getChildren().add(grid); 
	    
	    
		primaryStage.setScene(startPage); 
		primaryStage.show(); 
	}
	
}
