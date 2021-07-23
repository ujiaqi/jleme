package cn.fishei.competition.util;

import java.text.DecimalFormat;

public class Difference {

    public static double difference(String maxData, String minData){
        DecimalFormat df = new DecimalFormat("0.0");
        return Double.parseDouble(df.format(Double.parseDouble(maxData) - Double.parseDouble(minData)));
    }
}
