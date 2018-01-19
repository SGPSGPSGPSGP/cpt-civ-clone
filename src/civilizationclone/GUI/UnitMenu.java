
package civilizationclone.GUI;

import civilizationclone.City;
import civilizationclone.Unit.BuilderUnit;
import civilizationclone.Unit.MilitaryUnit;
import civilizationclone.Unit.SettlerUnit;
import civilizationclone.Unit.Unit;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UnitMenu extends Pane{
    
    Unit unit;
    UnitOption opt1;
    UnitOption opt2, opt3, opt4;
    ZoomMap zmapRef;
    Canvas infoDisplay;
    GraphicsContext gc;
    Text statusText;
    Text opt1T, opt2T, opt3T, opt4T;
    
    //TODO fix the menu options, right now the code is sloppy
    public UnitMenu(Unit unit, ZoomMap zmapRef) {
        this.unit = unit;
        
        if(unit instanceof SettlerUnit){
            opt1 = new UnitOption(65, -60, 250, 40, "Move");
            opt2 = new UnitOption(120, 0, 250, 40, "Move");
            opt3 = new UnitOption(120, 60, 250, 40, "Settle");
            opt4 = new UnitOption(65, 120, 250, 40, "Kill");
        }else if(unit instanceof BuilderUnit){
            opt1 = new UnitOption(65, -60, 250, 40, "Move");
            opt2 = new UnitOption(120, 0, 250, 40, "Improve");
            opt3 = new UnitOption(120, 60, 250, 40, "Destroy");
            opt4 = new UnitOption(65, 120, 250, 40, "Kill");
        }else if(unit instanceof MilitaryUnit){
            opt1 = new UnitOption(65, -60, 250, 40, "Move");
            opt2 = new UnitOption(120, 0, 250, 40, "Attack");
            opt3 = new UnitOption(120, 60, 250, 40, "Heal");
            opt4 = new UnitOption(65, 120, 250, 40, "Kill");
        }
        
        infoDisplay = new Canvas(230, 250);
        infoDisplay.setTranslateX(-195);
        infoDisplay.setTranslateY(-60);
        infoDisplay.setVisible(true);
        
        //Left info display
        statusText = new Text(displayUnitInfo());
        statusText.setFont(Font.font("Times New Roman", 20));
        statusText.setFill(Color.WHITE);
        statusText.setTranslateY(-25);
        statusText.setTranslateX(-180);
        //Right options
        opt1T = new Text(opt1.optionType);
        opt2T = new Text(opt2.optionType);
        opt3T = new Text(opt2.optionType);
        opt4T = new Text(opt4.optionType);
        
        opt1T.setTranslateX(opt1.x+20);
        opt1T.setTranslateY(opt1.y+25);
        opt2T.setTranslateX(opt2.x+20);
        opt2T.setTranslateY(opt2.y+25);
        opt3T.setTranslateX(opt3.x+20);
        opt3T.setTranslateY(opt3.y+25);
        opt4T.setTranslateX(opt4.x+20);
        opt4T.setTranslateY(opt4.y+25);
        
        opt1T.setFill(Color.WHITE);
        opt2T.setFill(Color.WHITE);
        opt3T.setFill(Color.WHITE);
        opt4T.setFill(Color.WHITE);
        
        opt1T.setFont(Font.font("Times New Roman", 20));
        opt2T.setFont(Font.font("Times New Roman", 20));
        opt3T.setFont(Font.font("Times New Roman", 20));
        opt4T.setFont(Font.font("Times New Roman", 20));
        
        opt1T.setMouseTransparent(true);
        opt2T.setMouseTransparent(true);
        opt3T.setMouseTransparent(true);
        opt4T.setMouseTransparent(true);
        
        gc = infoDisplay.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillPolygon(new double[]{0, 230, 230, 175, 175, 230, 230, 0}, new double[]{0, 0, 40, 75, 155, 180, 220, 220}, 8);
        gc.setFill(Color.BLACK);
        gc.fillPolygon(new double[]{5, 225, 225, 170, 170, 225, 225, 5}, new double[]{5, 5, 35, 70, 160, 185, 215, 215}, 8);
        
        System.out.println(unit.getClass().getSimpleName());
        
        opt1.setFill(Color.DARKSLATEGREY);
        opt2.setFill(Color.PALEGREEN);
        opt3.setFill(Color.BLUE);
        opt4.setFill(Color.INDIANRED);
        
        this.zmapRef = zmapRef;
        
        this.getChildren().add(opt1);
        this.getChildren().add(opt2);
        this.getChildren().add(opt3);
        this.getChildren().add(opt4);
        this.getChildren().add(infoDisplay);
        this.getChildren().add(statusText);
        this.getChildren().add(opt1T);
        this.getChildren().add(opt2T);
        this.getChildren().add(opt3T);
        this.getChildren().add(opt4T);
        this.setVisible(true);
        
        setOnMouseClicked((MouseEvent event) -> {
            clickEventHandling(event);
        });
    }
    
    
    private void clickEventHandling(MouseEvent e) {
        //System.out.println(e.getTarget());
        if(e.getTarget() instanceof UnitOption){
            System.out.println(((UnitOption)e.getTarget()).getOptionType());
            
            if(((UnitOption)e.getTarget()).getOptionType().equals("Move")){
                zmapRef.activateMove();
            }else if(((UnitOption)e.getTarget()).getOptionType().equals("Attack")){
                zmapRef.activateAttack();
            }else if(((UnitOption)e.getTarget()).getOptionType().equals("Settle")){
                zmapRef.activateSettle();
            }else if(((UnitOption)e.getTarget()).getOptionType().equals("Heal")){
                zmapRef.activateHeal();
            }else if(((UnitOption)e.getTarget()).getOptionType().equals("Kill")){
                zmapRef.activateKill();
            }else if(((UnitOption)e.getTarget()).getOptionType().equals("Improve")){
                zmapRef.activateImprove();
            }else if(((UnitOption)e.getTarget()).getOptionType().equals("Destroy")){
                zmapRef.activateDestroy();
            }
        }
    delete();

    }
    
    private String displayUnitInfo() {
        String msg = "";

        msg = msg + unit.getPlayer().getName() + "'s " + unit.getClass().getSimpleName().substring(0, unit.getClass().getSimpleName().length()-4) + "\n\n";
        
        
        msg = msg + "Movement: " + unit.getMovement() + "/" + unit.getMAX_MOVEMENT() + "\n";
        if(unit instanceof MilitaryUnit){
            msg = msg + "Health: " + ((MilitaryUnit) unit).getHealth() + " / " +((MilitaryUnit) unit).getMAX_HEALTH() + "\n";
        }else{
            msg = msg + "Health: 1/1 \n";
        }
        if(unit instanceof BuilderUnit){
            msg = msg + "Builds: " + ((BuilderUnit) unit).getActions();
        }
              

        return msg;
    }
    
    void delete(){
        zmapRef.getChildren().remove(zmapRef.getChildren().indexOf(this));
    }
    
        
}