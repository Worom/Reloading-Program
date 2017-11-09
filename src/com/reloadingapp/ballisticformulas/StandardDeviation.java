package com.reloadingapp.ballisticformulas;

import projectiles.NineMM;
import projectiles.Projectile;
import projectiles.ThirtyEightSpecial;
import projectiles.TwoTwoThree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StandardDeviation {

private List<Projectile> projectileVelocities = new ArrayList<>();
private Map<String, Double> deviationsByCaliber = new HashMap<>();

public StandardDeviation(List<Projectile> velocities){
    this.projectileVelocities = velocities;
}

public Map<String, Double> deviationsByCaliber(){
    List<Projectile> nineMMCaliber = new ArrayList<>();
    List<Projectile> twoTwoThreeCaliber = new ArrayList<>();
    List<Projectile> thirtyEightCaliber = new ArrayList<>();
    List<Double> nineMM115Grains = new ArrayList<>();
    List<Double> nineMM124Grains = new ArrayList<>();
    List<Double> nineMM147Grains = new ArrayList<>();

    /* Separate each caliber into its own list so that we aren't calculating standard deviation between different
    calibers
     */
    for(Projectile projectiles : projectileVelocities){
     if(projectiles instanceof NineMM){
         nineMMCaliber.add(projectiles);
     }
     if(projectiles instanceof TwoTwoThree){
         twoTwoThreeCaliber.add(projectiles);
     }
     if(projectiles instanceof ThirtyEightSpecial) {
         thirtyEightCaliber.add(projectiles);
     }

    }

    for(Projectile projectiles : nineMMCaliber){
        if((int) projectiles.getWeight() == 147){
            nineMM147Grains.add(projectiles.getVelocity());
        }
        if((int)projectiles.getWeight() == 124){
            nineMM124Grains.add(projectiles.getVelocity());
        }
        if((int) projectiles.getWeight() == 115){
            nineMM115Grains.add(projectiles.getVelocity());
        }
    }

    deviationsByCaliber.put("9mm 147 grains", getStandardDeviation(nineMM147Grains));
    deviationsByCaliber.put("9mm 124 grains", getStandardDeviation(nineMM124Grains));
    deviationsByCaliber.put("9mm 115 grains", getStandardDeviation(nineMM115Grains));

    return deviationsByCaliber;
}

private double averageVelocity(List<Double> velocity){
    double sum = 0;

    for(Double getAverageVelocity : velocity){
        sum += getAverageVelocity;
    }
    return  sum / velocity.size();

}


    private double getStandardDeviation(List<Double> values){
        double average = averageVelocity(values);
        double squaredDifferences = 0;
        for(Double deviation : values){
            squaredDifferences += Math.pow((deviation - average), 2);
        }
        return Math.sqrt(squaredDifferences / (this.projectileVelocities.size() - 1));
    }



}
