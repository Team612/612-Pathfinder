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
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 * Add your docs here.
 */
public class TankDrivePath {
    public TankDrivePath(){

    }
    
    public DistanceFollower[] generate(){
        Waypoint[] points = {new Waypoint(0, 0, Pathfinder.d2r(0)), 
            new Waypoint(1, 1, Pathfinder.d2r(0)),
            new Waypoint(1, 1, Pathfinder.d2r(60))};
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

        DistanceFollower leftFollower = new DistanceFollower(leftTrajectory);
        DistanceFollower rightFollower = new DistanceFollower(rightTrajectory);
        
        DistanceFollower[] followers = {leftFollower, rightFollower};
        
        return followers;
    }
}
