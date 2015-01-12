package com.noahhuppert.robocode;

import com.noahhuppert.robocode.helpers.StateManager;
import com.noahhuppert.robocode.states.RadarSweepState;
import com.noahhuppert.robocode.states.RadarTrackState;
import com.noahhuppert.robocode.states.RobotState;
import robocode.*;

import java.util.ArrayList;

/**
 * Created by block7 on 1/8/15.
 */
public class Curious extends AdvancedRobot{
    public StateManager stateManager = new StateManager();

    @Override
    public void run() {
        super.run();

        //State Setup
        stateManager.addState(new RadarSweepState());
        stateManager.addState(new RadarTrackState());

        stateManager.activateState(StateManager.STATE_DEFAULT, this);

        //Robot Setup
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);

        //Main Loop
        while(true){
            out.println("MAIN");

            RobotState activeState = stateManager.getActiveState();
            if(activeState != null) {
                stateManager.getActiveState().onActive(this);
            } else{
                stateManager.activateState(StateManager.STATE_DEFAULT, this);
            }
        }
    }



    /* Events */
    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        super.onHitByBullet(event);
        stateManager.getActiveState().handleEvent(event, RobotState.EVENT_HIT_BY_BULLET, this);
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        super.onHitWall(event);
        stateManager.getActiveState().handleEvent(event, RobotState.EVENT_HIT_WALL, this);
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        super.onScannedRobot(event);
        stateManager.getActiveState().handleEvent(event, RobotState.EVENT_SCANNED_ROBOT, this);
    }
}
