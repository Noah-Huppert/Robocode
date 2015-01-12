package com.noahhuppert.robocode.states;

import com.noahhuppert.robocode.Curious;
import com.noahhuppert.robocode.helpers.Headings;
import robocode.Event;
import robocode.Robot;
import robocode.ScannedRobotEvent;

/**
 * Created by block7 on 1/9/15.
 */
public class RadarTrackState extends RobotState {
    public static final String NAME = "STATE_RADAR_TRACK";

    private ScannedRobotEvent mostRecentScan = null;

    public RadarTrackState() {
        super(NAME);
    }

    @Override
    public void onActive(Robot robot) {
        robot.out.println("TRACK");
        if(mostRecentScan != null){
            robot.out.println("TRACKING: " + mostRecentScan.getHeading());
            Headings.setRadarHeading(mostRecentScan.getHeading(), robot);
        }
    }

    @Override
    public void handleEvent(Event event, String eventType, Curious robot) {
        if(eventType.equals(RobotState.EVENT_SCANNED_ROBOT)){
            mostRecentScan = (ScannedRobotEvent) event;
        }
    }

    @Override
    public void reset() {
        mostRecentScan = null;
    }
}
