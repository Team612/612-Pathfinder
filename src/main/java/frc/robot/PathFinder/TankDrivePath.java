/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.PathFinder;

import java.util.*;

import frc.robot.subsystems.Drivetrain;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory.FitMethod;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 * Add your docs here.
 */
public class TankDrivePath {
    EncoderFollower leftFollower;
    EncoderFollower rightFollower;
    public TankDrivePath(ArrayList<Waypoint> arr){
        Waypoint[] points = arr.toArray(new Waypoint[0]);
        Trajectory.Config config = new Trajectory.Config(FitMethod.HERMITE_CUBIC, 
                                                         Trajectory.Config.SAMPLES_HIGH, 
                                                         Drivetrain.P, 
                                                         Drivetrain.I, 
                                                         Drivetrain.D, 
                                                         Drivetrain.VELOCITY);
        Trajectory path = Pathfinder.generate(points, config);
        
        TankModifier modifier = new TankModifier(path);
        modifier.modify(Drivetrain.WHEELBASE_WIDTH);

        Trajectory leftTrajectory = modifier.getLeftTrajectory();
        Trajectory rightTrajectory = modifier.getRightTrajectory();

        leftFollower = new EncoderFollower(leftTrajectory);
        rightFollower = new EncoderFollower(rightTrajectory);
    }
    public EncoderFollower getLeftFollower(){
        return leftFollower;
    }
    public EncoderFollower getRightFollower(){
        return rightFollower;
    }
}
