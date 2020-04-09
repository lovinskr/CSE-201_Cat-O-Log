
/*
 * This is the entire front end of the Cat-o-log. 
 * 
 * 
 * color options:
 * https://www.w3schools.com/colors/colors_names.asp 
 *
 *
 *
 *
 * The line order can NOT be altered AT ALL
 * 
 * the Stage primaryStage holds the search bar and Vbox root 
 * 		which holds the grid that holds the titledPane filter and 
 * 		row that holds sortby, login, and signup  
 * 
 * 
 * To Do:
 * make passwords private 
 * get animals to have their own page 
 * get animals on front page 
 * sign up fully working
 * search working 
 * filter working 
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
	Animal[] frontPage = new Animal[500];
	
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException  
	{
		// sets up the stage that holds all the parts 
		primaryStage.setTitle("Cat-o-log");
		root.getChildren().addAll(); 
		root.setSpacing(10);
		root.setPadding(new Insets(10)); 
		
		Catalog animalCatalog = new Catalog();
		frontPage = animalCatalog.getAnimals(); 
		
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

		/* drop down sortBy
		 * each is alphabetical as default 
		 * for example if method of travel is chosen then 
		 * it goes fly slither swim walk 
		 * 
		 * with each animal sorted by the travel method
		 * alphabetically first 
		 * 
		 * cold blooded then warm blooded if cold blooded is chosen 
		*/ 
		String[] sortBy = { "A-Z", "Z-A", "Number of Limbs", "Preferred Biome", "Lifespan", "Diet",
				"Travel Method", "Cold Blooded", "Warm Blooded" };
		ComboBox<String> sortByDropDown = makeDropDown(sortBy);
		sortByDropDown.setPromptText("Sort By");
		sortByDropDown.setMaxHeight(20);
		sortByDropDown.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				String chosen = sortByDropDown.getValue(); 
				if(chosen.contentEquals("A-Z"))
				{
					shownAnimals(animalCatalog.getAnimals());
				}
				else if(chosen.contentEquals("Z-A"))
				{
					
				}
				else if(chosen.contentEquals("Number of Limbs"))
				{
					
				}
				else if(chosen.contentEquals("Preffered Biome"))
				{
					
				}
				else if(chosen.contentEquals("Travel Method"))
				{
					
				}
				else if(chosen.contentEquals("Cold Blooded"))
				{
					shownAnimals(animalCatalog.filterAnimals("cold", 0));
				}
				else if(chosen.contentEquals("Warm Blooded"))
				{
					
				}
				else if(chosen.contentEquals("Lifespan"))
				{
					
				}
				else if(chosen.contentEquals("Diet"))
				{
					
				}
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
				shownAnimals(animalCatalog.filterAnimals("cold", 0));
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
				if(chkFish.isSelected() == false)
					chkFish.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Fish", 2));
			}
		});

		CheckBox chkBird = makeChkBox("Birds");
		chkBird.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkBird.setStyle("-fx-color: LightSkyBlue");
				if(chkBird.isSelected() == false)
					chkBird.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Birds", 2));
			}
		});

		CheckBox chkReptiles = makeChkBox("Reptiles");
		chkReptiles.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkReptiles.setStyle("-fx-color: LightSkyBlue");
				if(chkReptiles.isSelected() == false)
					chkReptiles.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Reptiles", 2));
			}
		});

		CheckBox chkInsect = makeChkBox("Insect");
		chkInsect.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				chkInsect.setStyle("-fx-color: LightSkyBlue");
				if(chkInsect.isSelected() == false)
					chkInsect.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Insect", 2));
			}
		});

		CheckBox chkAmphibians = makeChkBox("Amphibians");
		chkAmphibians.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkAmphibians.setStyle("-fx-color: LightSkyBlue");
				shownAnimals(animalCatalog.filterAnimals("Amphibians", 2));
				if(chkAmphibians.isSelected() == false)
					chkAmphibians.setStyle("-fx-color: MediumAquaMarine");
			}
		});

		CheckBox chkArthropods = makeChkBox("Arthropods");
		chkArthropods.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				chkArthropods.setStyle("-fx-color: LightSkyBlue");
				shownAnimals(animalCatalog.filterAnimals("Arthropods", 2));
				if(chkArthropods.isSelected() == false)
					chkArthropods.setStyle("-fx-color: MediumAquaMarine");
			}
		});

		CheckBox chkVertebrates = makeChkBox("Vertebrates");
		chkVertebrates.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkVertebrates.setStyle("-fx-color: LightSkyBlue");
				shownAnimals(animalCatalog.filterAnimals("Vertebrates", 2));
				if(chkVertebrates.isSelected() == false)
					chkVertebrates.setStyle("-fx-color: MediumAquaMarine");
			}
		});
		
		CheckBox chkTundra = makeChkBox("Tundra");
		chkTundra.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkTundra.setStyle("-fx-color: LightSkyBlue");
				if(chkTundra.isSelected() == false)
					chkTundra.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Tundra", 3));
			}
		});
		
		CheckBox chkTaiga = makeChkBox("Taiga");
		chkTaiga.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkTaiga.setStyle("-fx-color: LightSkyBlue");
				if(chkTaiga.isSelected() == false)
					chkTaiga.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Taiga", 3));
			}
		});
		
		CheckBox chkTDForest = makeChkBox("Temperate Deciduous Forest");
		chkTDForest.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkTDForest.setStyle("-fx-color: LightSkyBlue");
				if(chkTDForest.isSelected() == false)
					chkTDForest.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Temperate Deciduous Forest", 3));
			}
		});
		
		CheckBox chkScrubForest = makeChkBox("Scrub Forest");
		chkScrubForest.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkScrubForest.setStyle("-fx-color: LightSkyBlue");
				if(chkScrubForest.isSelected() == false)
					chkScrubForest.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Scrub Forest", 3));
			}
		});
		
		CheckBox chkGrassland = makeChkBox("Grassland");
		chkGrassland.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkGrassland.setStyle("-fx-color: LightSkyBlue");
				if(chkGrassland.isSelected() == false)
					chkGrassland.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Grassland", 3));
			}
		});
		
		CheckBox chkDesert = makeChkBox("Desert");
		chkDesert.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkDesert.setStyle("-fx-color: LightSkyBlue");
				if(chkDesert.isSelected() == false)
					chkDesert.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Desert", 3));
			}
		});
		
		CheckBox chkTempRForest = makeChkBox("Temperate Rain Forest");
		chkTempRForest.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkTempRForest.setStyle("-fx-color: LightSkyBlue");
				if(chkTempRForest.isSelected() == false)
					chkTempRForest.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Temperate Rain Forest", 3));
			}
		});
		
		CheckBox chkTropRForest = makeChkBox("Tropical Rain Forest");
		chkTropRForest.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkTropRForest.setStyle("-fx-color: LightSkyBlue");
				if(chkTropRForest.isSelected() == false)
					chkTropRForest.setStyle("-fx-color: MediumAquaMarine");
				shownAnimals(animalCatalog.filterAnimals("Tropical Rain Forest", 3));
			}
		});
		
		/*
		 * 
		 * 
		 */

		// Lifespan range
		CheckBox chkLifespan = makeChkBox("Average Lifespan");
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
				shownAnimals(animalCatalog.rangeAnimals(Integer.parseInt(lifeLimitL.getText()), Integer.parseInt(lifeLimitU.getText()), 0));
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
				shownAnimals(animalCatalog.rangeAnimals(Integer.parseInt(limbLimitL.getText()), Integer.parseInt(limbLimitU.getText()), 1));
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
		
		TitledPane biomesTP = new TitledPane(); 
		biomesTP.setText("Preferred Biome");
		GridPane biomesGP = new GridPane(); 
		biomesGP.setVgap(4);
		biomesGP.add(chkTundra, 0, 0);
		biomesGP.add(chkTaiga, 0, 1);
		biomesGP.add(chkTDForest, 0, 2);
		biomesGP.add(chkScrubForest, 0, 3);
		biomesGP.add(chkGrassland, 0, 4);
		biomesGP.add(chkDesert, 0, 5);
		biomesGP.add(chkTropRForest, 0, 6);
		biomesGP.add(chkTempRForest, 0, 7);
		biomesTP.setContent(biomesGP);
		biomesTP.setExpanded(false);
		
		TitledPane cwBloodedTP = new TitledPane();
		cwBloodedTP.setText("Blood Type");
		GridPane cwBloodedGP = new GridPane();
		cwBloodedGP.setVgap(4);
		cwBloodedGP.add(chkColdBlooded, 0, 1);
		cwBloodedGP.add(chkWarmBlooded, 0, 2);
		cwBloodedTP.setContent(cwBloodedGP);
		cwBloodedTP.setExpanded(false);
		
		
		TitledPane animalClassTP = new TitledPane();
		animalClassTP.setText("Class");
		GridPane animalClassGP = new GridPane(); 
		animalClassGP.setVgap(4);
		animalClassGP.add(chkMammal, 0, 0);
		animalClassGP.add(chkFish, 0, 1);
		animalClassGP.add(chkBird, 0, 2);
		animalClassGP.add(chkReptiles, 0, 3);
		animalClassGP.add(chkInsect, 0, 4);
		animalClassGP.add(chkAmphibians, 0, 5);
		animalClassGP.add(chkArthropods, 0, 6);
		animalClassGP.add(chkVertebrates, 0, 7);
		animalClassTP.setContent(animalClassGP); 
		animalClassTP.setExpanded(false);
		
		TitledPane travelsByTP = new TitledPane();
		travelsByTP.setText("Method of Travel");
		GridPane travelsByGP = new GridPane(); 
		travelsByGP.setVgap(4);
		travelsByGP.add(chkFlies, 0, 0);
		travelsByGP.add(chkSlithers, 0, 1);
		travelsByGP.add(chkWalk, 0, 2);
		travelsByGP.add(chkSwim, 0, 3);
		travelsByTP.setContent(travelsByGP);
		travelsByTP.setExpanded(false);
		
		GridPane checkBoxes = new GridPane();
		checkBoxes.setVgap(4);
		checkBoxes.setPadding(new Insets(5, 5, 5,5));
		checkBoxes.add(cwBloodedTP, 0, 0);
		checkBoxes.add(animalClassTP, 0, 1);
		checkBoxes.add(biomesTP, 0, 2);
		checkBoxes.add(travelsByTP, 0, 3);
		checkBoxes.add(dietDropDown, 0, 4);
		
		checkBoxes.add(lifeLimitL, 0, 5);
		checkBoxes.add(lifeLimitU, 0, 6);
		checkBoxes.add(chkLifespan, 0, 7);
		checkBoxes.add(limbLimitL, 0, 8);
		checkBoxes.add(limbLimitU, 0, 9);
		checkBoxes.add(limbLimits, 0, 10);

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
		ScrollPane sp = new ScrollPane(grid);
		sp.setPannable(true); 
		grid.prefWidthProperty().bind(root.widthProperty());
		grid.prefHeightProperty().bind(root.heightProperty());
		
		Button signUp = makeButton("Sign Up"); 
		signUp.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				signUpPopUp(); 
			}
		});
	
		TextField UTF = makeTF(); // username
		UTF.setPromptText("Username");
		TextField PTF = makeTF(); // password
		PTF.setPromptText("Password");
		Button login = makeButton("Login");
		GridPane constantSearchAndLogin = new GridPane();
		constantSearchAndLogin.addRow(1, UTF, PTF, login, signUp);
		root.getChildren().addAll(constantSearchAndLogin);
		login.setOnAction(new EventHandler<ActionEvent>() 
		{
			 
			@Override
			public void handle(ActionEvent event) 
			{
				String username = UTF.getText().trim(); 
				String password = PTF.getText().trim(); 
				// check with accounts class 
				try 
				{ 
					Accounts temp = new Accounts();
					boolean hasAccount = temp.hasAccount(username, password); 
					if(hasAccount == true && isLoggedIn == false)
					{
						loggedIn(); 
						login.setText("Logout");
						constantSearchAndLogin.getChildren().removeAll(UTF, PTF, signUp); 
		                
					}
					else if(hasAccount == true && isLoggedIn == true)
					{
						loggedOut(); 
						login.setText("login");
						constantSearchAndLogin.getChildren().remove(login);
						UTF.clear();
						PTF.clear();
						constantSearchAndLogin.addRow(0, UTF, PTF, login, signUp);
					}
					else
					{ 
						counter();
						Stage dialog = new Stage();
		                dialog.initModality(Modality.APPLICATION_MODAL);
		                dialog.initOwner(primaryStage);
		                
		                StackPane popup = new StackPane();
		                popup.setStyle("-fx-background-color: AZURE");
		                Text text = new Text("Your password or username was incorrect.");
		                if(loginAttempts > 1)
		                {
		                	text = new Text("Your password or username was incorrect."
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
		
		
		// eight columns under row 0 for the animals 
		Text name = new Text("Name");
		name.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		//name.setFill(Color.DARKCYAN);
		Text prefBiome = new Text("Preferred Biome");
		prefBiome.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text classAni = new Text("Class");
		classAni.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text numLi = new Text("Limb Number");
		numLi.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text mOT = new Text("Travel Method");
		mOT.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text bT = new Text("Blood Type");
		bT.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text d = new Text("Diet");
		d.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text lS = new Text("Average Lifespan");
		lS.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		grid.addRow(1, name, classAni, mOT, numLi, prefBiome, bT, d, lS);
		
		int gridRow = 2; 
		for(int c = 0; c < frontPage.length && frontPage[c] != null; c++)
		{
			Text tempName = new Text(frontPage[c].getName());
			Text tempClass = new Text(frontPage[c].getAnimalClass());
			String[] tm = frontPage[c].getTravelMethods();
			Text tempMOT = new Text(tm[0]);
			Text tempNL = new Text(String.valueOf(frontPage[c].getNumOfLimbs()));
			Text tempPB = new Text(frontPage[c].getPreferredBiome());
			Text tempBT = new Text(frontPage[c].getBloodType());
			Text tempD = new Text(frontPage[c].getDiet());
			Text tempLS = new Text(String.valueOf(frontPage[c].getAverageLifespan()));
			grid.addRow(gridRow, tempName, tempClass, tempMOT, tempNL, tempPB, tempBT, tempD, tempLS);
			
			gridRow++;
		}
		
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
	
	void signUpPopUp()
	{
		Stage signup = new Stage();
		StackPane signupSP = new StackPane();
        signupSP.setStyle("-fx-background-color: MediumAquaMarine");
        
        TextField newUsername = new TextField(); 
        newUsername.setPromptText("Username");
        TextField newPassword = new TextField(); 
        newPassword.setPromptText("Password");
        TextField newPasswordCheck = new TextField();
        newPasswordCheck.setPromptText("Re-Enter Password");
        TextField newFN = new TextField(); 
        newFN.setPromptText("First Name");
        TextField newLN = new TextField(); 
        newLN.setPromptText("Last Name");
        TextField newE = new TextField();
        newE.setPromptText("Email");
        
        Button signingUp = new Button("Sign Up");
        
        GridPane signUpGP = new GridPane(); 
        signUpGP.prefWidthProperty().bind(signupSP.widthProperty());
        signUpGP.prefHeightProperty().bind(signupSP.heightProperty());
        signUpGP.setVgap(10); // gap between rows
        signUpGP.setHgap(10); // gap between columns
        signUpGP.addRow(0, newFN, newLN);
        signUpGP.addRow(1, newE);
        signUpGP.addRow(2, newUsername);
        signUpGP.addRow(3, newPassword, newPasswordCheck);  
        signUpGP.addRow(4, signingUp);
        signupSP.getChildren().add(signUpGP);
        signUpGP.setAlignment(Pos.CENTER);
        signingUp.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				String p = newPassword.getText(); 
				String pc = newPasswordCheck.getText();
				String u = newUsername.getText(); 
				String e = newE.getText(); 
				// if the passwords do not match 
				if(p.equals(pc) == false)
		        {
					Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.initOwner(signup);
	                
	                StackPane popup = new StackPane();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Your passwords do not match.");
	                
	                popup.getChildren().add(text);
	                 
	                Scene dialogScene = new Scene(popup, 200, 200);
	                dialog.setScene(dialogScene);
	                dialog.show(); 
		        }
				// if the password textfield is empty 
				else if(p.isEmpty()) 
				{
					Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.initOwner(signup);
	                
	                StackPane popup = new StackPane();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Please enter in a password.");
	                
	                popup.getChildren().add(text);
	                 
	                Scene dialogScene = new Scene(popup, 200, 200);
	                dialog.setScene(dialogScene);
	                dialog.show(); 
				}
				// if the username textfield is empty 
				else if(u.isEmpty())
				{
					Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.initOwner(signup);
	                
	                StackPane popup = new StackPane();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Please enter in a username.");
	                
	                popup.getChildren().add(text);
	                 
	                Scene dialogScene = new Scene(popup, 200, 200);
	                dialog.setScene(dialogScene);
	                dialog.show(); 
				}
				else if(e.isEmpty())
				{
					Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.initOwner(signup);
	                
	                StackPane popup = new StackPane();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Please enter in an email.");
	                
	                popup.getChildren().add(text);
	                 
	                Scene dialogScene = new Scene(popup, 200, 200);
	                dialog.setScene(dialogScene);
	                dialog.show(); 
				}
				
			}
		});
        
        
        Scene dialogScene = new Scene(signupSP, 400, 400);
        
        signup.setScene(dialogScene);
        signup.show(); 
	}
	
	void shownAnimals(Animal[] frontPageAnimals)
	{
		frontPage = frontPageAnimals; 
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

}
