/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  public static final double P = 0.2, I = 0, D = 0, VELOCITY = 2.5, WHEELBASE_WIDTH = 0.8;

  public  WPI_TalonSRX talon_FR = new WPI_TalonSRX(RobotMap.TALON_PORT_FR);
  public  WPI_TalonSRX talon_FL = new WPI_TalonSRX(RobotMap.TALON_PORT_FL);
  public  WPI_TalonSRX talon_BR = new WPI_TalonSRX(RobotMap.TALON_PORT_BR);
  public  WPI_TalonSRX talon_BL = new WPI_TalonSRX(RobotMap.TALON_PORT_BL);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TankDrive());
  }
}
