package com.noahhuppert.robocode.helpers;

import com.noahhuppert.robocode.states.RadarSweepState;
import com.noahhuppert.robocode.states.RobotState;

import java.util.ArrayList;

/**
 * Created by block7 on 1/9/15.
 */
public class StateManager {
    public static ArrayList<RobotState> states = new ArrayList<RobotState>();
    public static final String STATE_DEFAULT = RadarSweepState.NAME;

    /* Getters */
    public static RobotState getState(String stateName){
        RobotState robotState = null;

        for(RobotState state : states){
            if(state.getName().equals(stateName)){
                robotState = state;
            }
        }

        return robotState;
    }

    public static RobotState getActiveState(){
        RobotState activeState = null;

        for(RobotState state : states){
            if(state.getActive()){
                activeState = state;
            }
        }

        return activeState;
    }

    /* Setters */
    public static void addState(RobotState robotState){
        if(getState(robotState.getName()) == null){
            states.add(robotState);
        }
    }

    public static void activateState(String stateName){
        RobotState activeState = getActiveState();
        if(activeState != null){
            activeState.setActive(false);
        }

        RobotState toActivateState = getState(stateName);
        if(toActivateState != null) {
            getState(stateName).setActive(true);
        }
    }
}
