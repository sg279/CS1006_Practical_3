import java.util.ArrayList;
import java.util.Arrays;

public class Phoenix extends Unit{
    private int mineralCost=150;
    private int gasCost=100;
    private int buildTime=35;
    ArrayList<Building> dependentOn = new ArrayList<Building>(Arrays.asList(new Stargate(), new CyberneticsCore(), new Gateway(), new Pylon(), new Assimilator()));
    public boolean canBeBuilt(Game game){
        if (game.minerals>=this.mineralCost&&game.gas>=this.gasCost&&game.availableStargate>=1){
            int phoenixes = 0;
            for (Buildable buildable: game.buildOrder
                    ) {
                if(buildable.getClass()==this.getClass()){
                    phoenixes++;
                }
            }
            if(phoenixes==game.goal.phoenix){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
    public void build(Game game){
        game.phoenix++;
        game.availableStargate++;
    }
    public void startBuild(Game game){
        game.availableStargate--;
    }

    public int getBuildTime() {
        return buildTime;
    }

    public int getGasCost() {
        return gasCost;
    }

    public int getMineralCost() {
        return mineralCost;
    }

    public ArrayList<Building> getDependentOn() {
        return dependentOn;
    }
}