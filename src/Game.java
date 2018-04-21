import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class Game {
    int time;
    float minerals;
    float gas;
    int nexus;
    int pylon;
    int assimilator;
    int gateway;
    int cyberneticsCore;
    int roboticsFacility;
    int stargate;
    int twilightCouncil;
    int templarArchives;
    int darkShrine;
    int roboticsBay;
    int fleetBeacon;
    int probe;
    int zealot;
    int stalker;
    int sentry;
    int observer;
    int immortal;
    int phoenix;
    int voidRay;
    int colossus;
    int highTemplar;
    int darkTemplar;
    int carrier;
    int gasMiners;
    int mineralMiners;
    int availableNexus;
    int availableGateway;
    int availableRobotics;
    int availableStargate;
    int availabeProbe;
    ArrayList<Buildable> buildOrder = new ArrayList<>();
    ArrayList<Buildable> didntBuild = new ArrayList<>();
    Goal goal;

    private ArrayList<Event> calendar = new ArrayList<>();
    private ArrayList<Game> possibleMoves = new ArrayList<>();

    public void startBuilding(Buildable buildable){
        this.buildOrder.add(buildable);
        this.minerals-=buildable.getMineralCost();
        this.gas-=buildable.getGasCost();
        Event event = new Event(buildable, this.time+buildable.getBuildTime());
        if(buildable instanceof Unit){
            //Remove available building, add back on build
            ((Unit) buildable).startBuild(this);
        }
        this.calendar.add(event);
        int h=9;
    }

    public void startMining(){
        this.availabeProbe--;
        this.mineralMiners++;
    }

    public void startGasMining(){
        if(this.gasMiners<this.assimilator*3&&this.availabeProbe>=1){
            this.gasMiners++;
            this.availabeProbe--;
        }
    }

    public void tick(){
        this.time++;
        this.minerals+=(this.mineralMiners*41.0/60.0)-(this.mineralMiners%2)*21.0/60.0;
        this.gas+=this.gasMiners*38/60;
        for (int i=0; i<calendar.size(); i++) {
            if(calendar.get(i).time==this.time){
                calendar.get(i).buildable.build(this);
                this.calendar.remove(i);
                i--;
            }

        }
    }

    public Game(Game game){
        time=game.time;
        minerals=game.minerals;
        gas=game.gas;
        nexus=game.nexus;
        pylon=game.pylon;
        assimilator=game.assimilator;
        gateway=game.gateway;
        cyberneticsCore=game.cyberneticsCore;
        roboticsFacility=game.roboticsFacility;
        stargate=game.stargate;
        twilightCouncil=game.twilightCouncil;
        templarArchives=game.templarArchives;
        darkShrine=game.darkShrine;
        roboticsBay=game.roboticsBay;
        fleetBeacon=game.fleetBeacon;
        probe=game.probe;
        zealot=game.zealot;
        stalker=game.stalker;
        sentry=game.sentry;
        observer=game.observer;
        immortal=game.immortal;
        phoenix=game.phoenix;
        voidRay=game.voidRay;
        colossus=game.colossus;
        highTemplar=game.highTemplar;
        darkTemplar=game.darkTemplar;
        carrier=game.carrier;
        gasMiners=game.gasMiners;
        mineralMiners=game.mineralMiners;
        availableNexus=game.availableNexus;
        availableGateway=game.availableGateway;
        availableRobotics=game.availableRobotics;
        availableStargate=game.availableStargate;
        availabeProbe=game.availabeProbe;
        calendar = new ArrayList<>(game.calendar);
        buildOrder=new ArrayList<>(game.buildOrder);
        didntBuild= new ArrayList<>(game.didntBuild);
        this.goal=game.goal;
    }

    public Game(Goal goal){
        time=0;
        minerals=50;
        gas=0;
        nexus=1;
        pylon=0;
        assimilator=0;
        gateway=0;
        cyberneticsCore=0;
        roboticsFacility=0;
        stargate=0;
        twilightCouncil=0;
        templarArchives=0;
        darkShrine=0;
        roboticsBay=0;
        fleetBeacon=0;
        probe=6;
        zealot=0;
        stalker=0;
        sentry=0;
        observer=0;
        immortal=0;
        phoenix=0;
        voidRay=0;
        colossus=0;
        highTemplar=0;
        darkTemplar=0;
        carrier=0;
        gasMiners=0;
        mineralMiners=6;
        availableNexus=1;
        availableGateway=0;
        availableRobotics=0;
        availableStargate=0;
        availabeProbe=0;
        this.goal = goal;
    }

    public ArrayList<Game> getPossibleMoves(HashSet<Buildable> useful){
        AllBuildables allBuildables = new AllBuildables();
        allBuildables.addBuildables();

        this.tick();
        for(int i=0; i<allBuildables.allBuildables.size();i++){
            Buildable buildable = allBuildables.allBuildables.get(i);
            //(buildable.getMineralCost()>this.minerals-(this.mineralMiners*41.0/60.0)+(this.mineralMiners%2)*21.0/60.0||!this.hasBeenBuilt(buildable))
            if((!didntBuild(buildable)&&allBuildables.isUseful(buildable,useful)&&buildable.canBeBuilt(this))){
                Game possibleGame = new Game(this);
                possibleGame.startBuilding(allBuildables.allBuildables.get(i));
                this.possibleMoves.add(possibleGame);
            }

        }
        if(this.availabeProbe>=1){
            Game possibleGame = new Game(this);
            possibleGame.startMining();
            this.possibleMoves.add(possibleGame);
            if(this.gasMiners<this.assimilator*3){
                Game possibleGame2 = new Game(this);
                possibleGame2.startGasMining();
                this.possibleMoves.add(possibleGame2);
            }
        }
        else{
            this.didntBuild.clear();
            for(int i=0; i<allBuildables.allBuildables.size();i++){
                if(allBuildables.allBuildables.get(i).canBeBuilt(this)){
                    didntBuild.add(allBuildables.allBuildables.get(i));
                }
            }
            this.possibleMoves.add(new Game(this));
        }
        return this.possibleMoves;
    }

    public Boolean didntBuild(Buildable buildable){
        Boolean didntBuild=false;
        for (Buildable item: this.didntBuild
             ) {
            if(item.getClass().equals(buildable.getClass())){
                didntBuild=true;
            }
        }
        return didntBuild;
    }

    public Boolean hasBeenBuilt(Buildable buildable){
        Boolean beenBuilt=false;
        for (Buildable item: this.buildOrder
                ) {
            if(item.getClass().equals(buildable.getClass())){
                beenBuilt=true;
            }
        }
        return beenBuilt;
    }

}
