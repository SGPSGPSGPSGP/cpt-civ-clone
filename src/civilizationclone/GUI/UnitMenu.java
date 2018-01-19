
package civilizationclone.GUI;

import civilizationclone.City;
import civilizationclone.Unit.BuilderUnit;
import civilizationclone.Unit.MilitaryUnit;
import civilizationclone.Unit.SettlerUnit;
import civilizationclone.Unit.Unit;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class UnitMenu extends Pane{
    
    Unit unit;
    UnitOption opt1;
    UnitOption opt2, opt3, opt4;
    ZoomMap zmapRef;
    //Make menu option a private class
    public UnitMenu(Unit unit, ZoomMap zmapRef) {
        this.unit = unit;
        
        if(unit instanceof SettlerUnit){
            opt1 = new UnitOption(65, -60, 250, 40, "Move");
            opt2 = new UnitOption(100, 0, 250, 40, "Move");
            opt3 = new UnitOption(100, 60, 250, 40, "Settle");
            opt4 = new UnitOption(65, 120, 250, 40, "Kill");
        }else if(unit instanceof BuilderUnit){
            opt1 = new UnitOption(65, -60, 250, 40, "Move");
            opt2 = new UnitOption(100, 0, 250, 40, "Improve");
            opt3 = new UnitOption(100, 60, 250, 40, "Destroy");
            opt4 = new UnitOption(65, 120, 250, 40, "Kill");
        }else if(unit instanceof MilitaryUnit){
            opt1 = new UnitOption(65, -60, 250, 40, "Move");
            opt2 = new UnitOption(100, 0, 250, 40, "Attack");
            opt3 = new UnitOption(100, 60, 250, 40, "Heal");
            opt4 = new UnitOption(65, 120, 250, 40, "Kill");
        }
        
        opt1.setFill(Color.DARKSLATEGREY);
        opt2.setFill(Color.PALEGREEN);
        opt3.setFill(Color.BLUE);
        opt4.setFill(Color.INDIANRED);
        
        this.zmapRef = zmapRef;
        
        this.getChildren().add(opt1.getRect());
        this.getChildren().add(opt2.getRect());
        this.getChildren().add(opt3.getRect());
        this.getChildren().add(opt4.getRect());
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
    void delete(){
        zmapRef.getChildren().remove(zmapRef.getChildren().indexOf(this));
    }
    
        
}