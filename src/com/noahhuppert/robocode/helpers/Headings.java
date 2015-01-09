package com.noahhuppert.robocode.helpers;

import robocode.Robot;

/**
 * Created by block7 on 1/8/15.
 */
public class Headings {
    public static void setRadarHeading(double heading, Robot robot){
        double dif = heading - robot.getRadarHeading();

        if(dif < 180){
            robot.turnRadarRight(dif);
        } else{
            robot.turnRadarLeft(dif);
        }
    }
}
