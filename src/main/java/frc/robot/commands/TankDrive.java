/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.PathFinder.TankDrivePath;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import jaci.pathfinder.*;
import java.util.*;
public class TankDrive extends Command {
  TankDrivePath tankDrivePath;
  ArrayList<Waypoint> points;
  static final int encoder_tick_speed = 0;
  public TankDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    try{

      points = new ArrayList<>();
      points.add(new Waypoint(0, 0, Pathfinder.d2r(0) ) );
      tankDrivePath = new TankDrivePath(points);
    
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (tankDrivePath.getLeftFollower().isFinished() || tankDrivePath.getRightFollower().isFinished()){
      
      // Left joystick controller side
      if (Math.abs(OI.driver.getY(Hand.kLeft)) < .1) {
        Robot.drivetrain.talon_BL.set(0);
        Robot.drivetrain.talon_FL.set(0);
      } else {
        Robot.drivetrain.talon_FL.set(OI.driver.getY(Hand.kLeft));
        Robot.drivetrain.talon_BL.set(OI.driver.getY(Hand.kLeft));
      }
    
      // Right joystick controller side
      if (Math.abs(OI.driver.getY(Hand.kRight)) < .1) { 
        Robot.drivetrain.talon_FR.set(0);
        Robot.drivetrain.talon_BR.set(0);
      } else {
        Robot.drivetrain.talon_FR.set(OI.driver.getY(Hand.kRight));
        Robot.drivetrain.talon_BR.set(OI.driver.getY(Hand.kRight));
      }
    } else {

      Robot.drivetrain.talon_BL.set(tankDrivePath.getLeftFollower().calculate(encoder_tick_speed));
      Robot.drivetrain.talon_FL.set(tankDrivePath.getLeftFollower().calculate(encoder_tick_speed));
      Robot.drivetrain.talon_FR.set(tankDrivePath.getRightFollower().calculate(encoder_tick_speed));
      Robot.drivetrain.talon_BR.set(tankDrivePath.getRightFollower().calculate(encoder_tick_speed));

    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
