
/*
 * Help from:
 * http://www.java2s.com/Tutorials/Java/JavaFX/0590__JavaFX_ComboBox.htm 
 * 
 * 
 * background color options:
 * https://www.w3schools.com/colors/colors_names.asp 
 * 
 * 
 */


import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage; 

public class GUI extends Application
{
	int width = 500; 
	int height = 500; 
	StackPane root = new StackPane();
	Boolean[] checked = new Boolean[10]; // the list of checkboxes checked or not 
	
	public static void main(String[] args)
	{ 
		launch(args);  
	}
	 
	@Override 
	public void start(Stage primaryStage)
	{ 
		/*
		 * DO NOT CHANGE ORDER !!!!
		 * 
		 * 
		 * 
		 */
		primaryStage.setTitle("Cat-o-log");
		
		root.setStyle("-fx-background-color: LightSkyBlue;"); // background color
		Scene startPage = new Scene(root, width, height); 
	    
		
		// a mini abstract earth  
		// fix later and put by login 
		Circle C = new Circle(15,15,15);  
        RadialGradient gradient1 = new RadialGradient(
        	0, 		 // focus angle
            .01, 	 // focus distance
            0, 	 // centerX
            0, 	 // centerY
            75,  	 // radius
            false,   // proportional 
            CycleMethod.NO_CYCLE,  
            new Stop(0, Color.GREEN), 
            new Stop(1, Color.BLUE)
            );  
        C.setFill(gradient1); 
        C.setTranslateY(-150);
        C.setTranslateX(270);
        
		
		// search bar 
		TextField searchBar = new TextField(); 
		searchBar.setPromptText("Search the Cat-o-log");
		searchBar.setMaxHeight(50);
		searchBar.getText(); // use this to access text 
		searchBar.prefHeightProperty().bind(root.heightProperty());
	    searchBar.prefWidthProperty().bind(root.widthProperty());
	    
	    // submit search button 
	    Button submit = makeButton("Search"); 
	    submit.setOnAction(new EventHandler<ActionEvent>() 
	    {
	    	@Override
	    	public void handle(ActionEvent e) 
	    	{
	    		// will return animals that include the word(s) entered 
	    	}
	    });
	    
	    // login 
		// just there to look pretty for now
		Button login = makeButton("Login"); 
		login.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	        	
	        }
		}); 
		
		// drop down sortBy  
		String[] sortBy = {"Name", "Number of Legs", "Preferred Biome", "Lifespan",
				 "Diet", "Species", "Method of Travel", "Cold Blooded", 
			 "Warm Blooded"};
		ComboBox<String> sortByDropDown = makeDropDown(sortBy);
		sortByDropDown.setPromptText("Sort By");
		sortByDropDown.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	        	// sort by sortByDropDown.getValue() 
	        }
		}); 
		
		
		// the check boxes that will be filters 
		CheckBox chkColdBlooded =  makeChkBox("Cold Blooded");
		chkColdBlooded.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            
	        }
		});
		
		CheckBox chkWarmBlooded = makeChkBox("Warm Blooded");
		chkWarmBlooded.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Filter includes only warm blooded animals");
	        }
		});
		
		// have to include a text box to get the limb range 
		CheckBox limbLimits = makeChkBox("Limit Limbs");
		TextField limbLimitL = new TextField(); // lower limb limit 
		limbLimitL.setPromptText("Minimum Number of Limbs");
		TextField limbLimitU = makeTF(); // upper limb limit
		limbLimitU.setPromptText("Maximum Number of Limbs");
		
		limbLimits.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Uses the entered limb max and min as boundries");
	        }
		});
		
		CheckBox chkflies = makeChkBox("Flies");
		chkflies.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Animals that fly");
	        }
		});
		
		CheckBox chkSlithers = makeChkBox("Slithers");
		chkSlithers.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Animals that slither");
	        }
		});
		
		CheckBox chkWalk = makeChkBox("Walk");
		chkWalk.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Animals that walk");
	        }
		});
		
		CheckBox chkSwim = makeChkBox("Swims");
		chkSwim.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Animals that swim");
	        }
		});
		// Lifespan range
		CheckBox chkLifespan = makeChkBox("Lifespan");
		TextField lifeLimitL = makeTF(); // lower limb limit 
		TextField lifeLimitU = makeTF(); // upper limb limit
		
		chkLifespan.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            
	        }
		});
		
		// diet drop down 
		// can make a couple of check boxes later 
		String[] dietOptions = {"Carnivore", "Omnivore", "Herbavore"}; 
		ComboBox<String> dietDropDown = makeDropDown(dietOptions);
		dietDropDown.setMaxHeight(20);
		dietDropDown.setPromptText("Diet");
		CheckBox chkMammal = makeChkBox("Mammals");
		chkMammal.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Mammals");
	        }
		});
		
		CheckBox chkFish = makeChkBox("Fish");
		chkFish.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Fish");
	        }
		});
		
		CheckBox chkBird = makeChkBox("Birds"); 
		chkBird.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Birds");
	        }
		});
		
		CheckBox chkReptiles = makeChkBox("Reptiles");
		chkReptiles.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Reptiles");
	        }
		});
		
		CheckBox chkInsect = makeChkBox("Insect");
		chkInsect.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Insects");
	        }
		});
		
		CheckBox chkAmphibians = makeChkBox("Amphibians");
		chkAmphibians.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Amphibians");
	        }
		});
		
		CheckBox chkInvertebratesJointedLegs = makeChkBox("Invertebrates with Jointed Legs");
		chkInvertebratesJointedLegs.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Invertebrates with Jointed Legs");
	        }
		});
		
		CheckBox chkInvertebratesNoJointedLegs = makeChkBox("Invertebrates No Jointed Legs");
		chkInvertebratesNoJointedLegs.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Invertebrates with No Jointed Legs");
	        }
		});
		
		// the grid holds the accordion that holds a smaller grid that holds the checkboxes
		TitledPane tp = new TitledPane(); 
		tp.setText("Filters");
		GridPane checkBoxes = new GridPane(); 
		checkBoxes.setVgap(4);
		checkBoxes.setPadding(new Insets(5, 5, 5, 5));
		checkBoxes.add(chkColdBlooded, 0, 0);
		checkBoxes.add(chkWarmBlooded, 0, 1);
		checkBoxes.add(chkflies, 0, 2);
		checkBoxes.add(chkSlithers, 0, 3);
		checkBoxes.add(chkWalk, 0, 4);
		checkBoxes.add(chkMammal, 0, 5);
		checkBoxes.add(chkFish, 0, 6);
		checkBoxes.add(chkBird, 0, 7);
		checkBoxes.add(chkReptiles, 0, 8);
		checkBoxes.add(chkInsect, 0, 9);
		checkBoxes.add(chkAmphibians, 0, 10);
		checkBoxes.add(chkInvertebratesJointedLegs, 0, 11);
		checkBoxes.add(chkInvertebratesNoJointedLegs, 0, 12);
		checkBoxes.add(dietDropDown, 0, 13);
		checkBoxes.add(lifeLimitL, 0, 14);
		checkBoxes.add(lifeLimitU, 0, 15);
		checkBoxes.add(chkLifespan, 0, 16);
		tp.setExpanded(false);
		tp.setContent(checkBoxes);
		
		
		/* 
		 * !!!!!!!!!!!!!!!
		 * PLEASE DON'T MESS WITH THE CODE LINE ORDER
		 * THE GRID CAN BE EASILY FCKED UP !!!!
		 * !!!!!!!!!!!!!!!!!!
		 * 
		 * Filter Column is grid column 0
		*/
		// add filters to this column 
		// sets up for where the filters and such will be added and organized later in the code  
		GridPane grid = new GridPane(); 
		grid.setVgap(10); // gap between rows 
		grid.setHgap(10); // gap between columns
		grid.setPadding(new Insets(10, 10, 10, 10)); // pads edges 
		
		
		grid.addRow(0, searchBar, submit, sortByDropDown, login);
		grid.addColumn(0, tp); 
		
		root.getChildren().add(grid); 
		primaryStage.setScene(startPage);
		primaryStage.show(); 
	}
	
	CheckBox makeChkBox(String boxName)
	{
		CheckBox temp = new CheckBox(boxName);
		temp.setMaxHeight(20);
		temp.prefHeightProperty().bind(root.heightProperty());
		temp.prefWidthProperty().bind(root.widthProperty());
		
		
		return temp; 
	}
	
	
	Button makeButton(String str)
	{
		Button temp = new Button(str);
		 temp.setMaxHeight(50); 
		 // submit.setMaxWidth(500); need to find a way to set limits better
		 temp.prefHeightProperty().bind(root.heightProperty());
		 temp.prefWidthProperty().bind(root.widthProperty());
		 
		return temp; 
	}
	
	ComboBox<String> makeDropDown(String[] values)
	{ 
		ComboBox temp = new ComboBox(FXCollections.observableArrayList(values)); 
		
		temp.setMaxHeight(50);
		temp.prefHeightProperty().bind(root.heightProperty());
		temp.prefWidthProperty().bind(root.widthProperty());
		
		return temp; 
	}
	
	TextField makeTF()
	{
		TextField temp = new TextField(); 
		temp.setPromptText("Maximum Number of Years");
		temp.setMaxHeight(20);
		temp.setMaxWidth(100);
		temp.getText(); // use this to access text 
		temp.prefHeightProperty().bind(root.heightProperty());
		temp.prefWidthProperty().bind(root.widthProperty());
		
		return temp; 
	}
	
	void makeAnimalFrontPageSection(Animal weirdDog)
	{
		
	}
	
	void makeScroll()
	{
		
	}
	
}
