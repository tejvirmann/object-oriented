///////////////////////////////////////////////////////////////////////////////
//Assignment Name: Social Network A2
//Author: A-Team 15
//Member:
//Kang Fu, 001, kfu9@wisc.edu
//Jamal Moussa, 002, jmoussa@wisc.edu
//Suraj Joottu, 001, sjoottu@wisc.edu
//Tejvir Mann, 001, tsmann@wisc.edu
//Michael Her, 002, mvher2@wisc.edu
//Due Date: November 3, 2019
//Other Source Credits: None
//Known Bugs: None, to the best of my knowledge
///////////////////////////////////////////////////////////////////////////////

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Main JavaFX class that is the front-end of Bakla Network
 * 
 * @author A-Team 15
 */
public class Main extends Application {

	private static final int WINDOW_WIDTH = 1200; // width of the scene2
	private static final int WINDOW_HEIGHT = 750; // height of the scene2
	private static final String APP_TITLE = "Social Network"; // title name of the APP
	private static SocialNetwork socialNetwork = new SocialNetwork();
	private static Stage stage = new Stage();
	static BorderPane root = new BorderPane();
	static Canvas canvas;
	static GraphicsContext gc;

	private void alertMessage(String message) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("WARNING");
		info.setContentText(message);
		info.showAndWait();
	}
	
	/**
	 * 
	 * @param user
	 */
	static void reCenterDraw(Person user) {
		
	}
	
	/**
	 * 
	 * 
	 * @param isAdd
	 * @param scX
	 * @param scY
	 * @param deX
	 * @param deY
	 */
	static void drawEdge(boolean isAdd, double scX, double scY, double deX, double deY) {
		if (isAdd) {
			gc.setStroke(Color.BLUE);
			gc.strokeLine(scX, scY, deX, deY);
		} else {
			gc.setStroke(Color.WHITE);
			gc.strokeLine(scX, scY, deX, deY);
		}
	}
	
	/**
	 * 
	 * @param user
	 */
	static void drawNode(String user, boolean isAdd) {
		double d = 80.0; // diameter of the circle
		Person thisGuy = socialNetwork.graph.getAllVertices().get(user);
		gc = canvas.getGraphicsContext2D();
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent e) {
				if (e.getClickCount() > 1) {
					Text usName = new Text(user);
					double centerX = usName.getLayoutBounds().getCenterX();
					double centerY = usName.getLayoutBounds().getCenterY();
					
					if (isAdd) {
						gc.setFill(Color.color(Math.random(), Math.random(), Math.random()));
						gc.fillOval(e.getX(), e.getY(), d, d);
						gc.setFill(Color.color(Math.random(), Math.random(), Math.random()));
						gc.fillText(user, e.getX()+(d/2-centerX), e.getY()+(d/2-centerY));
						thisGuy.setX(e.getX()+(d/2-centerX));
						thisGuy.setY(e.getY()+(d/2-centerY));
					} else {
						double pX = thisGuy.getX();
						double pY = thisGuy.getY();
						gc.setFill(Color.color(1.0, 1.0, 1.0));
						gc.fillOval(pX-(d/2-centerX), pY-(d/2-centerY), d, d);
						//gc.fillText(user, e.getX()+(d/2-centerX), e.getY()+(d/2-centerY));
						for (Person friend: socialNetwork.graph.getAllVertices().get(user).getNeighbors()) {
							Main.drawEdge(isAdd, friend.getX(), friend.getY(),thisGuy.getX(), thisGuy.getY());
						}
					}
				}
			}
		});
	}

	/**
	 * Load the file or write to the file by updating the friendship hash map and
	 * the command list.
	 * 
	 * @param isLoad option of reading or writing file
	 * @return the load write button
	 */
	private Button setupLoadWriteButton(Boolean isLoad) {
		Button lwButton = new Button();
		if (isLoad)
			lwButton.setText("Upload file");
		else
			lwButton.setText("Write to file");
		lwButton.setPrefSize(100, 50);

		lwButton.setStyle("-fx-background-color: MediumSeaGreen;" + "-fx-font-size: 10pt");

		lwButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("All Files", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(Main.stage);
			if (selectedFile != null) {
				if (isLoad) {
					try {
						socialNetwork.loadFromFile(selectedFile);
					} catch (FileNotFoundException e1) {

					}
				} else {
					try {
						socialNetwork.saveToFile(selectedFile);
					} catch (FileNotFoundException e1) {
						this.alertMessage("No Such File!!");
					}
				}
			} else {
				this.alertMessage("Please Select a File");
			}
		});
		return lwButton;
	}

	/**
	 * Functionality of the add and remove button
	 * 
	 * @param isAdd    - to add a user
	 * @param isCenter - to have user set as center of visualizer
	 * @return HBox with the buttons
	 */
	private HBox addRemoveUserButton(Boolean isAdd, Boolean isCenter) {
		String text;
		if (isAdd) {
			text = "Add";
		} else if (isCenter) {
			text = "Set Center";
		} else {
			text = "Remove";
		}

		HBox UIButtons = new HBox();
		Button addUB = new Button(text);
		addUB.setPrefSize(100, 50);
		TextField user = new TextField();
		user.setPromptText("username");

		user.setPrefSize(100, 50);
		UIButtons.getChildren().addAll(addUB, user);

		addUB.setStyle("-fx-background-color: MediumSeaGreen;" + "-fx-font-size: 10pt");

		addUB.setOnAction(e -> {
			if (!this.checkInput(user.getText())) {
				this.alertMessage("Invalid Character");
			} else {
				if (isAdd) {
					socialNetwork.addUser(user.getText());
				} else if (isCenter) {
					socialNetwork.setCenter(user.getText());
				} else {
					socialNetwork.removeUser(user.getText());
				}
			}
		});
		return UIButtons;
	}

	private boolean checkInput(String in) {
		boolean pass = true;
		for (char ch : in.toCharArray()) {
			if (!Character.isLetter(ch) && !Character.isDigit(ch) && ch != '\'' && ch != '_') {
				pass = false;
				break;
			}
		}
		return pass;
	}

	/**
	 * Stores the buttons with text fields
	 * 
	 * @param isAdd - if user wants to add or remove person
	 * @return HBox that stores the buttons with text fields
	 */
	private HBox addRemoveFriendShip(Boolean isAdd) {
		String text;
		if (isAdd) {
			text = "Add";
		} else {
			text = "Remove";
		}
		HBox UIButtons = new HBox();
		Button addUB = new Button(text);
		addUB.setPrefSize(100, 50);

		TextField user1 = new TextField();
		user1.setPromptText("user1");

		TextField user2 = new TextField();
		user2.setPromptText("user2");

		user1.setPrefSize(100, 50);
		user2.setPrefSize(100, 50);

		addUB.setStyle("-fx-background-color: MediumSeaGreen;" + "-fx-font-size: 10pt");

		addUB.setOnAction(e -> {
			if (!this.checkInput(user1.getText()) || !this.checkInput(user2.getText())) {
				this.alertMessage("Invalid Character");
			} else {
				if (isAdd) {
					socialNetwork.addFriends(user1.getText(), user2.getText());
				} else {
					socialNetwork.removeFriends(user1.getText(), user2.getText());
				}
			}
		});

		UIButtons.getChildren().addAll(addUB, user1, user2);
		return UIButtons;
	}

	/**
	 * Creates a User Interface control element which here is a interface that allow
	 * user to enter user name and password
	 * 
	 * @return the user interface as a gridPane
	 */
	private GridPane LeftPanelUI() {

		GridPane grid = new GridPane();
		grid.setHgap(7.5); // set horizontal gap
		grid.setVgap(15); // set vertical gap

		// UIButtons.setSpacing(5.0); // horizontal space between buttons

		HBox addUserBox = addRemoveUserButton(true, false);
		addUserBox.setSpacing(5.0); // horizontal space between buttons

		HBox removeUserBox = addRemoveUserButton(false, false);
		removeUserBox.setSpacing(5.0);

		HBox centerUserBox = addRemoveUserButton(false, true);
		centerUserBox.setSpacing(5.0);

		HBox addFriendShipBox = addRemoveFriendShip(true);
		addFriendShipBox.setSpacing(5.0); // horizontal space between buttons

		HBox removeFriendShipBox = addRemoveFriendShip(false);
		removeFriendShipBox.setSpacing(5.0); // horizontal space between buttons

		Button loadButton = setupLoadWriteButton(true); // TODO: Call back end method
		Button writeButton = setupLoadWriteButton(false); // TODO: Call back end method
		
		Button exitButton = setupexitButton(true);

		grid.add(loadButton, 1, 4);
		grid.add(writeButton, 1, 6);
		grid.add(centerUserBox, 1, 8);
		grid.add(addUserBox, 1, 10);
		grid.add(removeUserBox, 1, 12);
		grid.add(addFriendShipBox, 1, 14);
		grid.add(removeFriendShipBox, 1, 16);
		
		grid.add(exitButton, 1, 18);
		return grid;
	}

	/**
	 * This is an override class which is used to call the other method and start
	 * generating the interface
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Text text1 = new Text(10, 30, "Control Panel");
		text1.setFill(Color.CHOCOLATE);
		text1.setFont(Font.font(java.awt.Font.SERIF, 25));

		Text text2 = new Text(500, 30, "Visualization Panel");
		text2.setFill(Color.DARKCYAN);
		text2.setFont(Font.font(java.awt.Font.SERIF, 25));
		root.getChildren().addAll(text1, text2);

		canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		root.getChildren().add(canvas);
		Main.root.setLeft(this.LeftPanelUI());
		primaryStage.setTitle(APP_TITLE);
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * The main method which is used to activate the entire program
	 * 
	 * @param args (not used here)
	 */
	public static void main(String[] args) {
		launch(args);
	}
}