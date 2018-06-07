/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civilizationclone.GUI;

import civilizationclone.GameMap;
import civilizationclone.GameMap.MapSize;
import civilizationclone.Network.*;
import civilizationclone.Player;
import civilizationclone.TechType;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUIMain extends Application {

    private Stage stage;
    private GamePane gamePane;
    private Server server;
    private ClientSocket clientSocket;

    @Override
    public void start(Stage primaryStage) {
        
        stage = primaryStage;

        int resX = 1200;
        int resY = 800;

        primaryStage.setResizable(false);

        //primaryStage.setScene(new Scene(new TitleMenu(resX, resY, primaryStage), resX, resY));
        //primaryStage.setScene(new Scene(startWithoutMenu(), 1200, 1000));
        initializeTextLink();

        primaryStage.setTitle("Alex Yang's Colonization II");
        primaryStage.show();

    }

    public void initializeTextLink() {

        Scanner scan = new Scanner(System.in);
        int choice = 0;

        System.out.println("Are you a host? 1/0");
        choice = scan.nextInt();
        scan.nextLine();

        if (choice == 1) {
            server = new Server();
            System.out.println("Do you wish to start the game?");
            
        } else {
            System.out.println("Please enter server information.");
            System.out.print("IP: ");
            String ip = scan.nextLine();
            System.out.print("Port: ");
            choice = scan.nextInt();
            scan.nextLine();

            clientSocket = new ClientSocket();
            clientSocket.connect(ip, choice);
        }
    }
    
    public void startMultiplayerGame(){
        
    }

    public Pane startWithoutMenu() {

        //intialize a game without going into title menu, strictly for testing
        GameMap gameMap = new GameMap(MapSize.MEDIUM, 400);
        ArrayList<Player> p = new ArrayList<Player>();
        p.add(new Player("Joseph Stalin"));
        //p.add(new Player("Winston Churchill"));

        //   p.add(new Player("Adolf Hitler"));
        //   p.add(new Player("Franklin Roosevelt"));
        //   p.add(new Player("Mao Zedong"));
        //   p.add(new Player("Bennito Mussolini"));
        testingStats(p.get(0));
        //testingStats(p.get(1));

        // gamePane = new GamePane(gameMap, p, 1200, 1000, true, false);
        return gamePane;
    }

    public void testingStats(Player p) {
        p.setTechIncome(10);
        p.setCurrentGold(100000000);

        for (TechType t : TechType.values()) {
            p.addTech(t);
        }
    }

}
