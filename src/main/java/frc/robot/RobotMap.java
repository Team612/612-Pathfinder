/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  
  // Ports numbers for controllers
  public static final int DRIVER_PORT            = 0;
  public static final int GUNNER_PORT            = 1;

  // Talons for drivetrain
  public static int TALON_PORT_FL = 6;
  public static int TALON_PORT_FR = 3;
  public static int TALON_PORT_BL = 7;
  public static int TALON_PORT_BR = 2;

}
