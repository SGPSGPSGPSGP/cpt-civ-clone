package civilizationclone.GUI;

import civilizationclone.*;
import civilizationclone.Tile.*;
import civilizationclone.Unit.SettlerUnit;
import civilizationclone.Unit.UnitType;
import civilizationclone.Unit.WarriorUnit;
import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GamePane extends Pane {

    //A pane class that handles everything that is game-related
    private int resX, resY;
    private ZoomMap zoomMap;
    private CityMenu cityMenu;
    private NextTurnPane nextButton;
    private StatusBarPane statusBar;

    //game data
    private GameMap gameMap;
    private ArrayList<Player> playerList;
    private Player currentPlayer;

    public GamePane(GameMap gameMap, ArrayList<Player> playerList, int resX, int resY) {

        this.gameMap = gameMap;
        this.playerList = playerList;
        this.resX = resX;
        this.resY = resY;
        this.setPrefHeight(resY);
        this.setPrefWidth(resX);

        currentPlayer = playerList.get(0);
        
        //Reset these later to just settler and warrior
        for(Player player: playerList){
            do{
            Point p = new Point((int) (Math.random() * gameMap.getSize()), (int) (Math.random() * gameMap.getSize() - 1));
            if((gameMap.getTile(p) instanceof Plains || gameMap.getTile(p) instanceof Hills || gameMap.getTile(p) instanceof Desert) && (gameMap.getTile(p.x, p.y+1) instanceof Plains || 
                    gameMap.getTile(p.x, p.y+1) instanceof Hills || gameMap.getTile(p.x, p.y+1) instanceof Desert) && !gameMap.getTile(p).hasUnit() && !gameMap.getTile(p.x, p.y+1).hasUnit()){
                    player.addUnit(new SettlerUnit(player, p));
                    player.addUnit(new WarriorUnit(player, new Point(p.x, p.y+1)));
                    break;
                }
            }while(true);
        }
        
        
        playerList.get(0).startTurn();
        playerList.get(0).setCurrentGold(100);

        zoomMap = createFalseMap(gameMap.getMap());
        zoomMap.setCurrentPlayer(currentPlayer);
        
        
        nextButton = new NextTurnPane(currentPlayer, resX, resY, this);
        statusBar = new StatusBarPane(currentPlayer, resX, resY, zoomMap);
        
        getChildren().add(zoomMap);
        getChildren().add(nextButton);
        getChildren().add(statusBar);
        
        
        setOnMouseClicked((MouseEvent e) -> {
            nextButton.updateText();
            statusBar.updateTexts();
        });
    }
    
    public void nextTurn(){
        if(playerList.indexOf(currentPlayer)==playerList.size()-1){
            currentPlayer = playerList.get(0);
        }else{
            currentPlayer = playerList.get(playerList.indexOf(currentPlayer)+1);
        }
        System.out.println(currentPlayer);
        
        nextButton.setCurrentPlayer(currentPlayer);
        statusBar.setCurrentPlayer(currentPlayer);
        zoomMap.setCurrentPlayer(currentPlayer);
        
        currentPlayer.startTurn();
    }

    public void addCityMenu(City c) {
        cityMenu = new CityMenu(c, resX, resY, zoomMap);
        getChildren().add(cityMenu);
    }

    public void removeCityMenu() {
        if (getChildren().contains(cityMenu)) {
            getChildren().remove(cityMenu);
        }
        cityMenu = null;

        zoomMap.enableDragging(true);
    }

    private ZoomMap createFalseMap(Tile[][] original) {

        //creates a copy of the actual tile map, with the sides adjusted according to X-resolution to a copied version for scrolling effect
        //first calculate how much spare is needed on both sides, should be according to scene size
        int spareSize = resX / 100 + 1;

        //original = new GameMap(GameMap.MapSize.SMALL, 1).getMap();
        Tile[][] copy = new Tile[original.length][original[0].length + spareSize * 2];

        //copy the back of the original to the front spare, then front of the original to the back spare
        //first copy the back to front
        for (int x = 0; x < copy.length; x++) {
            for (int y = 0; y < spareSize; y++) {
                copy[x][y] = original[x][original[0].length - spareSize + y];
            }
        }
        //then copy the original chunk
        for (int x = 0; x < copy.length; x++) {
            for (int y = 0; y < original[0].length; y++) {
                copy[x][y + spareSize] = original[x][y];
            }
        }
        //then copy the front of the orignial to the back spare
        for (int x = 0; x < copy.length; x++) {
            for (int y = 0; y < spareSize; y++) {
                copy[x][y + spareSize + copy.length] = original[x][y];
            }
        }

        ZoomMap pane = new ZoomMap(40, resX, resY, spareSize, copy);

        return pane;

    }

    public ZoomMap getZoomMap() {
        return zoomMap;
    }
}
