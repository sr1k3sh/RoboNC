package com.arrtsm.app.woodapp.Robot;

public class OldRobotBuilder implements  RobotBuilder {
    private Robot robot;
    public OldRobotBuilder(){
        this.robot=new Robot();
    }
    @Override
    public void buildRobothead() {
robot.setRobotHead("Tin head");
    }

    @Override
    public void buildRobotTorso() {
robot.setRobotTorso("Toros ");
    }

    @Override
    public void buildRobotArms() {
robot.setRobotArms("Paper Arms");
    }

    @Override
    public void buildRobotLegs() {
robot.setRobotLegs("stil legs");
    }

    @Override
    public Robot getRobot() {
        return this.robot;
    }
}
