package com.noahhuppert.robocode.helpers;

import com.noahhuppert.robocode.states.RadarSweepState;
import com.noahhuppert.robocode.states.RobotState;
import robocode.Robot;

import java.util.ArrayList;

/**
 * Created by block7 on 1/9/15.
 */
public class StateManager {
    public ArrayList<RobotState> states = new ArrayList<RobotState>();
    public static final String STATE_DEFAULT = RadarSweepState.NAME;

    /* Getters */
    public RobotState getState(String stateName){
        RobotState robotState = null;

        for(RobotState state : states){
            if(state.getName().equals(stateName)){
                robotState = state;
            }
        }

        return robotState;
    }

    public RobotState getActiveState(){
        RobotState activeState = null;

        for(RobotState state : states){
            if(state.getActive()){
                activeState = state;
            }
        }

        return activeState;
    }

    /* Setters */
    public void addState(RobotState robotState){
        if(getState(robotState.getName()) == null){
            states.add(robotState);
        }
    }

    public void activateState(String stateName, Robot robot){
        robot.out.println("     1");

        RobotState activeState = getActiveState();
        if(activeState != null){
            activeState.setActive(false);
            activeState.reset();

            robot.out.println("     A1");
        }


        robot.out.println("     2");

        RobotState toActivateState = getState(stateName);
        if(toActivateState != null) {
            toActivateState.setActive(true);
            toActivateState.reset();


            robot.out.println("     A2");
        }


        robot.out.println("     3");
    }
}