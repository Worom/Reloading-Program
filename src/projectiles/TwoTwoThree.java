package projectiles;

public class TwoTwoThree extends Projectile {

    public TwoTwoThree(){
        super(0,0);
    }


   public TwoTwoThree(double weightInGrains, double velocity){
       super(weightInGrains, velocity);
   }


    @Override
    public String getCaliber() {
        return ".223";
    }
}
