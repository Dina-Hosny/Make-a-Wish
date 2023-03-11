package client;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public abstract class HomeBase extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final ImageView imageView;
    protected final MenuBar menuBar;
    protected final Menu menu;
    protected final MenuItem menuItem;
    protected final MenuItem menuItem0;
    protected final Menu menu0;
    protected final MenuItem menuItem1;
    protected final MenuItem menuItem2;
    protected final Menu menu1;
    protected final MenuItem menuItem3;
    protected final MenuItem menuItem4;
    protected final Menu menu2;
    protected final MenuItem menuItem5;
    protected final Menu menu3;
    protected final MenuItem menuItem6;
    protected final Button button;
    protected final Button button0;

    public HomeBase() {

        anchorPane = new AnchorPane();
        imageView = new ImageView();
        menuBar = new MenuBar();
        menu = new Menu();
        menuItem = new MenuItem();
        menuItem0 = new MenuItem();
        menu0 = new Menu();
        menuItem1 = new MenuItem();
        menuItem2 = new MenuItem();
        menu1 = new Menu();
        menuItem3 = new MenuItem();
        menuItem4 = new MenuItem();
        menu2 = new Menu();
        menuItem5 = new MenuItem();
        menu3 = new Menu();
        menuItem6 = new MenuItem();
        button = new Button();
        button0 = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(479.0);
        setPrefWidth(739.0);
        setStyle("-fx-background-color: LINEN;");

        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        imageView.setAccessibleRole(javafx.scene.AccessibleRole.INCREMENT_BUTTON);
        imageView.setFitHeight(485.0);
        imageView.setFitWidth(745.0);
        imageView.setLayoutX(-5.0);
        imageView.setLayoutY(-2.0);
        imageView.setOnDragDetected(this::switchToScene2);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("../../../../Downloads/christmas-novena-prayer.jpg").toExternalForm()));

        AnchorPane.setBottomAnchor(menuBar, 459.0);
        menuBar.setBlendMode(javafx.scene.effect.BlendMode.LIGHTEN);
        menuBar.setPrefHeight(24.0);
        menuBar.setPrefWidth(739.0);
        menuBar.setStyle("-fx-border-color: LINEN; -fx-background-color: BISQUE;");

        menu.setText("WishList");

        menuItem.setMnemonicParsing(false);
        menuItem.setText("Wishes");

        menuItem0.setMnemonicParsing(false);
        menuItem0.setText("Collected");

        menu0.setMnemonicParsing(false);
        menu0.setText("Freinds");

        menuItem1.setMnemonicParsing(false);
        menuItem1.setText("Pending");

        menuItem2.setMnemonicParsing(false);
        menuItem2.setText("Freinds");

        menu1.setMnemonicParsing(false);
        menu1.setText("Notifications");

        menuItem3.setMnemonicParsing(false);
        menuItem3.setText("Pendiing");

        menuItem4.setMnemonicParsing(false);
        menuItem4.setText("Pendiing");

        menu2.setText("Transfer");

        menuItem5.setMnemonicParsing(false);
        menuItem5.setText("Close");

        menu3.setText("Help");

        menuItem6.setMnemonicParsing(false);
        menuItem6.setText("About");

        button.setLayoutX(374.0);
        button.setLayoutY(376.0);
        button.setMnemonicParsing(false);
        button.setOnAction(this::switchToScene2);
        button.setPrefHeight(42.0);
        button.setPrefWidth(160.0);
        button.setStyle("-fx-region-background: LINEN;");
        button.setText("Register");
        button.setTextFill(javafx.scene.paint.Color.DARKSALMON);
        button.setFont(new Font("FreeMono Bold Oblique", 24.0));

        button0.setLayoutX(375.0);
        button0.setLayoutY(431.0);
        button0.setMnemonicParsing(false);
        button0.setOnAction(this::switchToScene1);
        button0.setPrefHeight(42.0);
        button0.setPrefWidth(160.0);
        button0.setStyle("-fx-region-background: LINEN;");
        button0.setText("Login");
        button0.setTextFill(javafx.scene.paint.Color.DARKSALMON);
        button0.setFont(new Font("FreeMono Bold Oblique", 24.0));

        anchorPane.getChildren().add(imageView);
        menu.getItems().add(menuItem);
        menu.getItems().add(menuItem0);
        menuBar.getMenus().add(menu);
        menu0.getItems().add(menuItem1);
        menu0.getItems().add(menuItem2);
        menuBar.getMenus().add(menu0);
        menu1.getItems().add(menuItem3);
        menu1.getItems().add(menuItem4);
        menuBar.getMenus().add(menu1);
        menu2.getItems().add(menuItem5);
        menuBar.getMenus().add(menu2);
        menu3.getItems().add(menuItem6);
        menuBar.getMenus().add(menu3);
        anchorPane.getChildren().add(menuBar);
        anchorPane.getChildren().add(button);
        anchorPane.getChildren().add(button0);
        getChildren().add(anchorPane);

    }

    protected abstract void switchToScene2(javafx.scene.input.MouseEvent mouseEvent);

    protected abstract void switchToScene2(javafx.event.ActionEvent actionEvent);

    protected abstract void switchToScene1(javafx.event.ActionEvent actionEvent);

}
