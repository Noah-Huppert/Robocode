package com.noahhuppert.robocode;

import com.noahhuppert.robocode.helpers.StateManager;
import com.noahhuppert.robocode.states.RadarSweepState;
import com.noahhuppert.robocode.states.RadarTrackState;
import com.noahhuppert.robocode.states.RobotState;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.util.ArrayList;

/**
 * Created by block7 on 1/8/15.
 */
public class Curious extends Robot{
    @Override
    public void run() {
        super.run();

        //State Setup
        StateManager.addState(new RadarSweepState());
        StateManager.addState(new RadarTrackState());

        StateManager.activateState(StateManager.STATE_DEFAULT);

        //Robot Setup
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);

        //Main Loop
        while(true){
            RobotState activeState = StateManager.getActiveState();
            if(activeState != null) {
                StateManager.getActiveState().onActive(this);
            }
        }
    }

    /* Events */
    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        super.onHitByBullet(event);
        StateManager.getActiveState().handleEvent(event, RobotState.EVENT_HIT_BY_BULLET, this);
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        super.onHitWall(event);
        StateManager.getActiveState().handleEvent(event, RobotState.EVENT_HIT_WALL, this);
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        super.onScannedRobot(event);
        StateManager.getActiveState().handleEvent(event, RobotState.EVENT_SCANNED_ROBOT, this);
    }
}
