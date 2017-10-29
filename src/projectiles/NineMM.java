package projectiles;

public class NineMM extends Projectile {

    private final String CALIBER = "9mm";
    public NineMM(){
        super(0, 0);
    }

    public NineMM(double weightInGrains, double velocity){
        super(weightInGrains, velocity);
    }

    @Override
    public String getCaliber() {
        return this.CALIBER;
    }

}
