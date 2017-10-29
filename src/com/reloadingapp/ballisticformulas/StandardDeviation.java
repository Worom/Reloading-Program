package com.reloadingapp.ballisticformulas;

import java.util.ArrayList;
import java.util.List;

public class StandardDeviation {

private List<Double> projectileVelocities = new ArrayList<>();
public StandardDeviation(List<Double> velocities){
    this.projectileVelocities = velocities;
}



public double getStandardDeviation(){
    double average = averageVelocity(this.projectileVelocities);
    double squaredDifferences = 0;
    for(Double deviation : this.projectileVelocities){
        squaredDifferences += Math.pow((deviation - average), 2);
    }
    return Math.sqrt(squaredDifferences / (this.projectileVelocities.size() - 1));
}


private double averageVelocity(List<Double> velocity){
    double sum = 0;

    for(Double getAverageVelocity : velocity){
        sum += getAverageVelocity;
    }
    return  sum / velocity.size();

}

}
