package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class DefaultDrive extends Command {

  public DefaultDrive() {

    requires(Robot.drivetrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
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
