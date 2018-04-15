import java.util.ArrayList;

public class AllBuildables {
    ArrayList<Buildable> allBuildables = new ArrayList<>();

    public void addBuildables(){
        this.allBuildables.add(new Nexus());
        this.allBuildables.add(new Pylon());
        this.allBuildables.add(new Assimilator());
        this.allBuildables.add(new Gateway());
        this.allBuildables.add(new CyberneticsCore());
        this.allBuildables.add(new RoboticsFacility());
        this.allBuildables.add(new Stargate());
        this.allBuildables.add(new Probe());
        this.allBuildables.add(new Zealot());
        this.allBuildables.add(new Stalker());
        this.allBuildables.add(new Sentry());
        this.allBuildables.add(new Observer());
        this.allBuildables.add(new Immortal());
        this.allBuildables.add(new Phoenix());
        this.allBuildables.add(new VoidRay());
    }
}