import java.util.ArrayList;
import java.util.Arrays;

public class Sentry extends Unit{
    private int mineralCost=50;
    private int gasCost=100;
    private int buildTime=37;
    ArrayList<Building> dependentOn = new ArrayList<Building>(Arrays.asList(new Gateway(), new Pylon(), new Assimilator()));
    public boolean canBeBuilt(Game game){
        if (game.minerals>=this.mineralCost&&game.gas>=this.gasCost&&game.availableGateway>=1){
            return true;
        }
        else{
            return false;
        }
    }
    public void build(Game game){
        game.sentry++;
        game.availableGateway++;
    }
    public void startBuild(Game game){
        game.availableGateway--;
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