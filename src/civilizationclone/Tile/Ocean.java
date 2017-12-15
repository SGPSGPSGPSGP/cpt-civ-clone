package civilizationclone.Tile;

import java.util.EnumSet;
import java.util.Set;

public class Ocean extends Tile {

    static Set<Improvement> possibleImprovement = EnumSet.of(Improvement.FISHING);
    
    public Ocean() {
        super(true, 1);
    }

    @Override
    public void calcOutput() {
        setFoodOutput(1 + getImprovement().getFoodBonus() + getResource().getFoodBonus());
        setGoldOutput(1 + getImprovement().getGoldBonus() + getResource().getGoldBonus());
        setProductionOutput(getImprovement().getProductionBonus() + getResource().getProductionBonus());
        setScienceOutput(getImprovement().getScienceBonus() + getResource().getTechBonus());
    }

       @Override
    public Set<Improvement> getPossibleImprovements() {
        Set<Improvement> improve = possibleImprovement;
        improve.add(this.getResource().getImprovementType());
        return improve;
    }

}
