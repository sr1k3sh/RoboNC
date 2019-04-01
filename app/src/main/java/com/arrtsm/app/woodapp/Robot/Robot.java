package com.arrtsm.app.woodapp.Robot;

public class Robot implements RobotPlan {
    private String robotHead,robotTorso,robotArms,robotLegs;
    @Override
    public void setRobotHead(String head) {
        robotHead=head;
    }
    public String getRobotHead(){
        return robotHead;
    }
    @Override
    public void setRobotTorso(String torso) {
        robotTorso=torso;
    }
    public String getRobotTorso(){
        return robotTorso;
    }
    @Override
    public void setRobotArms(String arms) {
        robotArms=arms;
    }
    public String getRobotArms(){
        return robotArms;
    }
    @Override
    public void setRobotLegs(String legs) {
        robotLegs=legs;
    }
    public String getRobotLegs(){
        return robotLegs;
    }
}
