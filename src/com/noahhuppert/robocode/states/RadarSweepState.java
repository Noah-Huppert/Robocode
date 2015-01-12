package com.noahhuppert.robocode.states;

import com.noahhuppert.robocode.Curious;
import com.noahhuppert.robocode.helpers.StateManager;
import robocode.Event;
import robocode.Robot;
import robocode.ScannedRobotEvent;

/**
 * Created by block7 on 1/8/15.
 */
public class RadarSweepState extends RobotState {
    public static final String NAME = "STATE_ROBOT_SWEEP";

    private boolean switching = false;

    public RadarSweepState() {
        super(NAME);
    }

    @Override
    public void onActive(Robot robot) {
        robot.turnRadarRight(5);
    }

    @Override
    public void handleEvent(Event event, String eventType, Curious robot) {
        if(eventType.equals(RobotState.EVENT_SCANNED_ROBOT) && !switching){
            robot.out.println("     SSSS");
            switching = true;
            ScannedRobotEvent scannedRobotEvent = (ScannedRobotEvent) event;
            robot.stateManager.activateState(RadarTrackState.NAME, robot);
        }
    }

    @Override
    public void reset() {
        switching = false;
    }
}
