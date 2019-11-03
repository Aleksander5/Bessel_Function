package besselapplication;

import org.apache.commons.math3.special.BesselJ;

import java.util.ArrayList;
import java.util.List;

public class BesselCalculation {

    public static List<Double> calculateBesselFunctionValues(double order){
        BesselJ singleBesselValue = new BesselJ(order);
        List<Double> besselValuesList = new ArrayList<Double>();

        for(double i=0; i<=40; i+=0.2)
            besselValuesList.add(singleBesselValue.value(i));

        return besselValuesList;
    }

}
