package com.noahhuppert.robocode.states;

import com.noahhuppert.robocode.helpers.StateManager;
import robocode.Event;
import robocode.Robot;
import robocode.ScannedRobotEvent;

/**
 * Created by block7 on 1/8/15.
 */
public class RadarSweepState extends RobotState {
    public static final String NAME = "STATE_ROBOT_SWEEP";

    public RadarSweepState() {
        super(NAME);
    }

    @Override
    public void onActive(Robot robot) {
        robot.turnRadarRight(5);
    }

    @Override
    public void handleEvent(Event event, String eventType, Robot robot) {
        if(eventType.equals(RobotState.EVENT_SCANNED_ROBOT)){
            robot.out.println("SWITCHING");
            ScannedRobotEvent scannedRobotEvent = (ScannedRobotEvent) event;
            StateManager.activateState(RadarTrackState.NAME);
        }
    }
}
