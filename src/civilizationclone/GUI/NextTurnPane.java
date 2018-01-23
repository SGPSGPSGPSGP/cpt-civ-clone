package civilizationclone.GUI;

import civilizationclone.Player;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NextTurnPane extends Group {

    private Rectangle rect;
    private Text text;
    private int resX;
    private int resY;
    private Player player;
    private GamePane gamePaneRef;
    private ZoomMap zMapRef;

    public NextTurnPane(Player player, int resX, int resY, GamePane gamePaneRef) {
        this.text = new Text();
        text.setFont(Font.font("Oswald"));
        this.resX = resX;
        this.resY = resY;
        this.player = player;
        this.rect = new Rectangle(0, 0, 311, 40);
        this.gamePaneRef = gamePaneRef;
        this.zMapRef = gamePaneRef.getZoomMap();
        this.setTranslateX(resX - 311);
        this.setTranslateY(resY - 316.5 - rect.getHeight());
        
        text.setFill(Color.BEIGE);
        updateText();
        
        rect.setStrokeWidth(5);
        rect.setStroke(Color.BEIGE);
        rect.setStrokeLineJoin(StrokeLineJoin.ROUND);
        rect.setFill(Color.DARKSLATEBLUE);

        getChildren().add(rect);
        getChildren().add(text);

        setOnMouseClicked((MouseEvent e) -> {

            clickEvent(e);
        });

    }
    
    public void clickEvent(MouseEvent e){
        
        if (this.player.canEndTurn() == 0) {
            gamePaneRef.nextTurn();
        }

        updateText();

        e.consume();
    }

    public void setCurrentPlayer(Player player) {
        this.player = player;
    }

    public void updateText() {
        if (player.canEndTurn() == 2) {
            text.setText("MUST SELECT CITY PROJECT");
        } else if (player.canEndTurn() == 1) {
            text.setText("A UNIT NEEDS ORDERS");
        } else if (player.canEndTurn() == 3) {
            text.setText("MUST SELECT REASEARCH");
        } else {
            text.setText("NEXT TURN");
        }

        text.setFill(Color.WHITE);
        text.setFont(Font.font("Oswald", 20));
        text.setTranslateX((rect.getWidth() - text.getLayoutBounds().getWidth()) / 2);
        text.setTranslateY(text.getLayoutBounds().getHeight() + 2);
        zMapRef.repaint();
    }
    
    void delete(){
        gamePaneRef.getChildren().remove(this);
    }

}