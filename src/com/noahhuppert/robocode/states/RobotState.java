package com.noahhuppert.robocode.states;

import com.noahhuppert.robocode.Curious;
import robocode.Event;
import robocode.Robot;

/**
 * Created by block7 on 1/8/15.
 */
public abstract class RobotState {
    public static final String EVENT_HIT_BY_BULLET = "EVENT_HIT_BY_BULLET";
    public static final String EVENT_HIT_WALL = "EVENT_HIT_WALL";
    public static final String EVENT_SCANNED_ROBOT = "EVENT_SCANNED_ROBOT";

    private String name;
    private boolean active;

    public RobotState(String name) {
        this.name = name;
        this.active = false;
    }

    /* Actions */
    public abstract void onActive(Robot robot);
    public abstract void handleEvent(Event event, String eventType, Curious robot);
    public abstract void reset();

    /* Getters */
    public String getName() {
        return name;
    }

    public boolean getActive() {
        return active;
    }

    /* Setters */
    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
