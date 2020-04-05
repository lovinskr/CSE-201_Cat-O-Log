
/*
 * Help from:
 * http://www.java2s.com/Tutorials/Java/JavaFX/0590__JavaFX_ComboBox.htm 
 * 
 * 
 * color options:
 * https://www.w3schools.com/colors/colors_names.asp 
 *
 *
 *	This is the front end of the Cat-o-log. 
 *
 *
 * The line order can NOT be altered AT ALL
 * 
 * the Stage primaryStage holds the search bar and Vbox root 
 * 		which holds the grid that holds the titledPane filter and 
 * 		row that holds sortby, login, and signup  
 * 		
 * 
 * add in species biome 
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

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
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
import javafx.scene.Node;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUI extends Application 
{
	private int width = 500;
	private int height = 500;
	int loginAttempts = 0;
	private boolean isLoggedIn = false; 
	VBox root = new VBox(); 
	Boolean[] checked = new Boolean[10]; // the list of check boxes checked or not

	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)  
	{
		// sets up the stage that holds all the parts 
		primaryStage.setTitle("Cat-o-log");
		root.getChildren().addAll(); 
		root.setSpacing(10);
		root.setPadding(new Insets(10)); 
		
		
		// search bar 
		TextField searchBar = makeTF();
		searchBar.setPromptText("Search the Cat-o-log");

		// submit the search button 
		Button submit = makeButton("Search");
		submit.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent e) 
			{
				// will return animals that include the word(s) entered
			}
		});
		
		// adding them directly to root keeps them at the top 
		root.getChildren().add(searchBar); 
		root.getChildren().add(submit);

		// drop down sortBy
		String[] sortBy = { "Name", "Number of Limbs", "Preferred Biome", "Lifespan", "Diet", "Species",
				"Method of Travel", "Cold Blooded", "Warm Blooded" };
		ComboBox<String> sortByDropDown = makeDropDown(sortBy);
		sortByDropDown.setPromptText("Sort By");
		sortByDropDown.setMaxHeight(20);
		sortByDropDown.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				
			}
		});
		
		
		// the check boxes that will be filters
		CheckBox chkColdBlooded = makeChkBox("Cold Blooded");
		chkColdBlooded.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkColdBlooded.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkWarmBlooded = makeChkBox("Warm Blooded");
		chkWarmBlooded.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkWarmBlooded.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkFlies = makeChkBox("Flies");
		chkFlies.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkFlies.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkSlithers = makeChkBox("Slithers");
		chkSlithers.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkSlithers.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkWalk = makeChkBox("Walk");
		chkWalk.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkWalk.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkSwim = makeChkBox("Swims");
		chkSwim.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkSwim.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkFish = makeChkBox("Fish");
		chkFish.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkFish.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkBird = makeChkBox("Birds");
		chkBird.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkBird.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkReptiles = makeChkBox("Reptiles");
		chkReptiles.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkReptiles.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkInsect = makeChkBox("Insect");
		chkInsect.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				chkInsect.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkAmphibians = makeChkBox("Amphibians");
		chkAmphibians.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkAmphibians.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkArthropods = makeChkBox("Arthropods");
		chkArthropods.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				chkArthropods.setStyle("-fx-color: LightSkyBlue");
			}
		});

		CheckBox chkVertebrates = makeChkBox("Vertebrates");
		chkVertebrates.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkVertebrates.setStyle("-fx-color: LightSkyBlue");
				if(chkVertebrates.isSelected() == false)
					chkVertebrates.setStyle("-fx-color: MediumAquaMarine");
			}
		});
		
		/*
		 * 
		 * 
		 */

		// Lifespan range
		CheckBox chkLifespan = makeChkBox("Lifespan");
		TextField lifeLimitL = makeTF(); // lower limb limit
		lifeLimitL.setPromptText("Min Years");
		lifeLimitL.setMaxHeight(20);
		TextField lifeLimitU = makeTF(); // upper limb limit
		lifeLimitU.setPromptText("Max Years");
		lifeLimitU.setMaxHeight(20); 
		chkLifespan.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{

			}
		});

		// have to include a text box to get the limb range
		CheckBox limbLimits = makeChkBox("Limit Limbs");
		TextField limbLimitL = makeTF(); // lower limb limit
		limbLimitL.setPromptText("Min Limbs"); 
		limbLimitL.setMaxHeight(20);
		TextField limbLimitU = makeTF(); // upper limb limit
		limbLimitU.setPromptText("Max Limbs");
		limbLimitU.setMaxHeight(20);
		limbLimits.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				System.out.println("Uses the entered limb max and min as boundries");
			}
		});
		
		
		
		// diet drop down
		// can make a couple of check boxes later
		String[] dietOptions = { "Carnivore", "Omnivore", "Herbavore" };
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

		
		 /* 
		 * Filter Column is grid column 0
		 * the big grid's holds the titledPane 
		 * that holds a smaller grid that holds the check boxes 
		 */
		
		TitledPane tp = new TitledPane();
		tp.setText("Filter");
		GridPane checkBoxes = new GridPane();
		checkBoxes.setVgap(4);
		checkBoxes.setPadding(new Insets(5, 5, 5,5));
		checkBoxes.add(chkColdBlooded, 0, 0);
		checkBoxes.add(chkWarmBlooded, 0, 1);
		checkBoxes.add(chkFlies, 0, 2);
		checkBoxes.add(chkSlithers, 0, 3);
		checkBoxes.add(chkWalk, 0, 4);
		checkBoxes.add(chkMammal, 0, 5);
		checkBoxes.add(chkFish, 0, 6);
		checkBoxes.add(chkBird, 0, 7);
		checkBoxes.add(chkReptiles, 0, 8);
		checkBoxes.add(chkInsect, 0, 9);
		checkBoxes.add(chkAmphibians, 0, 10);
		checkBoxes.add(chkArthropods, 0, 11);
		checkBoxes.add(chkVertebrates, 0, 12);
		checkBoxes.add(dietDropDown, 0, 13);
		checkBoxes.add(lifeLimitL, 0, 14);
		checkBoxes.add(lifeLimitU, 0, 15);
		checkBoxes.add(chkLifespan, 0, 16);
		checkBoxes.add(limbLimitL, 0, 17);
		checkBoxes.add(limbLimitU, 0, 18);
		checkBoxes.add(limbLimits, 0, 19);

		tp.setExpanded(false);
		tp.setContent(checkBoxes);
		
		/*
		 * permanent search bar header 
		 * 
		 */
		
		root.setStyle("-fx-background-color: MediumAquaMarine;");
		
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: LightSkyBlue;"); // background color
		grid.setVgap(10); // gap between rows
		grid.setHgap(10); // gap between columns
		grid.setPadding(new Insets(10, 10, 10, 10)); // pads edges
		
		 
		TextField UTF = makeTF(); // username
		UTF.setPromptText("Username");
		TextField PTF = makeTF(); // password
		PTF.setPromptText("Password");
		Button login = makeButton("Login");
		login.setOnAction(new EventHandler<ActionEvent>() 
		{
			 
			@Override
			public void handle(ActionEvent event) 
			{
				String username = UTF.getText(); 
				String password = PTF.getText(); 
				// check with accounts class 
				Accounts temp = new Accounts(username, password); 
				try 
				{ 
					Boolean hasAccount = temp.canLogin();
					if(hasAccount == true && isLoggedIn == false)
					{
						loggedIn(); 
						Stage dialog = new Stage();
		                dialog.initModality(Modality.APPLICATION_MODAL);
		                dialog.initOwner(primaryStage);
		                
		                StackPane popup = new StackPane();
		                popup.setStyle("-fx-background-color: AZURE");
		                Text text = new Text("You are logged in!");
		               
		                popup.getChildren().add(text);
		                 
		                Scene dialogScene = new Scene(popup, 400, 200);
		                dialog.setScene(dialogScene);
		                dialog.show();
		                
		                // get rid of tfs and change login to logout 
		                login.setText("Logout");
		                
					}
					if(hasAccount == true && isLoggedIn == true)
					{
						loggedOut(); 
						// make tfs show up and change back to login 
						login.setText("Login");
					}
					else
					{ 
						counter();
						Stage dialog = new Stage();
		                dialog.initModality(Modality.APPLICATION_MODAL);
		                dialog.initOwner(primaryStage);
		                
		                StackPane popup = new StackPane();
		                popup.setStyle("-fx-background-color: AZURE");
		                Text text = new Text("Your password or username were incorrect.");
		                if(loginAttempts > 1)
		                {
		                	text = new Text("Your password or username were incorrect."
		                				  + "You have tried " + loginAttempts + " times.");
		                }
		                popup.getChildren().add(text);
		                 
		                Scene dialogScene = new Scene(popup, 400, 200);
		                dialog.setScene(dialogScene);
		                dialog.show(); 
					}
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			 
		});
		
		
		Button signUp = makeButton("Sign Up"); 
		signUp.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				Scene loginScene = new Scene(root); 
				primaryStage.getScene().setRoot(loginScene.getRoot());
				
			}
		});

		
		grid.addRow(0, UTF, PTF, login, signUp);
		grid.addColumn(0, sortByDropDown, tp);
		
		
		
		ScrollPane sp = new ScrollPane(grid);
		sp.setPannable(true); 
		grid.prefWidthProperty().bind(root.widthProperty());
		grid.prefHeightProperty().bind(root.heightProperty());
		
		
		root.getChildren().add(grid);
		root.getChildren().add(sp);
		
        Scene startPage = new Scene(root, 500, 500);
		primaryStage.setScene(startPage);
		primaryStage.show();
	}

	CheckBox makeChkBox(String boxName) 
	{
		CheckBox temp = new CheckBox(boxName);
		temp.setMaxHeight(5);
		temp.setStyle(
			      "-fx-color: MediumAquaMarine; " // make this color match the animal front page
			    + "-fx-font-size: 12;"
			    + ";"
			);
		//temp.prefHeightProperty().bind(root.heightProperty());
		//temp.prefWidthProperty().bind(root.widthProperty());

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
		temp.setMaxHeight(50);
		
		temp.getText(); // use this to access text
		temp.prefHeightProperty().bind(root.heightProperty());
		temp.prefWidthProperty().bind(root.widthProperty());

		return temp;
	}
	
	/* exists only because javafx can't handle a button counter 
	 * increments the login attempt counter 
	 */
	void counter()
	{
		loginAttempts++; 
	}
	
	void loggedIn()
	{
		isLoggedIn = true; 
	}
	
	void loggedOut()
	{
		isLoggedIn = false; 
	}
	
	/*
	 * OTHER SCENES AND PAGES ECT 
	 * 
	 */
	void makeAnimalFrontPageSection(Animal weirdDog) 
	{
		GridPane shownAnimals = new GridPane();
		
		
		
		
	}

	void nextAnimalPage() 
	{
		
	}

}
