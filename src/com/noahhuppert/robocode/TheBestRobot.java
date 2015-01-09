package com.noahhuppert.robocode;

import robocode.*;

import java.awt.*;

/**
 * Created by block7 on 1/5/15.
 */
public class TheBestRobot extends AdvancedRobot {
    private boolean scanningForRobot = true;
    private double scannedRobotBearing = 0;

    private boolean targetingRobot = false;
    private boolean firingAtRobot = false;

    private boolean evading = false;
    private double evadeDirection = 0;

    private boolean unHittingWall = false;
    private double unHitWallDirection = 0;


    public void run(){
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);

        while(true){
            if(unHittingWall){
                setRobotHeading(unHitWallDirection);
                ahead(100);
                unHittingWall = false;
                scanningForRobot = true;
            } else if(evading){
                setRobotHeading(evadeDirection);
                ahead(100);
                evading = false;
                scanningForRobot = true;
            } else if(scanningForRobot){
                turnRadarRight(5);
            } else if(targetingRobot){
                setGunHeading(scannedRobotBearing);
                targetingRobot = false;
                firingAtRobot = true;
            } else if(firingAtRobot){
                fire(1);
                scanningForRobot = true;
            }

            if(evading) {
                setBodyColor(Color.RED);
            }else if(scanningForRobot){
                setBodyColor(Color.CYAN);
            }
        }
    }

    public void setGunHeading(double heading){
        double difference = heading - getGunHeading();

        if(difference < 180){
            turnGunRight(difference);
        } else{
            turnGunLeft(180 - difference);
        }
    }

    public void setRobotHeading(double heading){
        double difference = heading - getHeading();

        if(difference < 180){
            turnRight(difference);
        } else{
            turnLeft(180 - difference);
        }
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        super.onHitWall(event);
        unHittingWall = true;
        unHitWallDirection = event.getBearing() + 90;
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        super.onScannedRobot(event);
        if(scanningForRobot){
            scanningForRobot = false;
            targetingRobot = true;
            scannedRobotBearing = getRadarHeading();
        }
    }

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        super.onHitByBullet(event);
        evading = true;
        evadeDirection = event.getBearing() + 90;
    }
}
