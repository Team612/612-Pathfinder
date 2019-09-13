package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.PathFinder.TankDrivePath;
import jaci.pathfinder.followers.DistanceFollower;

public class AutoDrive extends Command {
  
  TankDrivePath tankDrivePath;
  DistanceFollower[] followers;
  
  public AutoDrive() {
    
  }

  @Override
  protected void initialize() {
    tankDrivePath = new TankDrivePath();
   followers = tankDrivePath.generate();
  }

  @Override
  protected void execute() {
    if (!followers[0].isFinished() && !followers[1].isFinished()){
      
      double leftVal = followers[0].calculate(followers[0].getSegment().position); 
      double rightVal = followers[1].calculate(followers[1].getSegment().position); 

      Robot.drivetrain.talon_FL.set(leftVal);
      Robot.drivetrain.talon_BL.set(leftVal);
      Robot.drivetrain.talon_FR.set(rightVal);
      Robot.drivetrain.talon_BR.set(rightVal);
      
    } 
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
