package projectiles;

public class ThirtyEightSpecial extends Projectile {

    public ThirtyEightSpecial(double weightInGrains, double velocity){
        super(weightInGrains, velocity);
    }


    @Override
    public String getCaliber() {
        return ".38 Special";
    }
}
