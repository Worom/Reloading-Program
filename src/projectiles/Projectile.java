package projectiles;

public abstract class Projectile {

private double weightInGrains, velocity,muzzleEnergy;
    private final double MUZZLEENERGYCONSTANT = 0.000002218;
    Projectile(double weightInGrains, double velocity){
 this.weightInGrains = weightInGrains;
 this.velocity = velocity;
 }


    public double getWeight() {
        return weightInGrains;
    }

    public void setWeightInGrains(double weightInGrains) {
        this.weightInGrains = weightInGrains;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void calculateMuzzleEnergy(){
        muzzleEnergy = (weightInGrains * velocity) * (velocity * MUZZLEENERGYCONSTANT);
    }

    public double getEnergy() {
        return muzzleEnergy;
    }

    public abstract String getCaliber();

}
