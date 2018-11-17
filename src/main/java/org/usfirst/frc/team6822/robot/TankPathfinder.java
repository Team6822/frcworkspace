import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Tank {

    public static void main(String[] args) {
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
        //change these numbers
        Waypoint[] points = new Waypoint[] {
                new Waypoint(-4, -1, Pathfinder.d2r(-45)),
                new Waypoint(0, -48, 0),
                new Waypoint(98, 36, 0),
                new Waypoint(132, 36, 0),
                new Waypoint(98, 36, 0),
                new Waypoint(146, 72, 0)


        }

        // Create the Trajectory Configuration
        //
        // Arguments CHANGE THESE
        // Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
        // Sample Count:        SAMPLES_HIGH (100 000)
        //                      SAMPLES_LOW  (10 000)
        //                      SAMPLES_FAST (1 000)
        // Time Step:           0.05 Seconds
        // Max Velocity:        1.7 m/s
        // Max Acceleration:    2.0 m/s/s
        // Max Jerk:            60.0 m/s/s/s



        Trajectory trajectory = Pathfinder.generate(points, config);

       // CHANGE WHEELBASE WIDTH
        // Wheelbase Width = 0.5m
        TankModifier modifier = new TankModifier(trajectory).modify(0.5);


        // Do something with the new Trajectories...
        Trajectory left = modifier.getLeftTrajectory();
        Trajectory right = modifier.getRightTrajectory();

        EncoderFollower left = new EncoderFollower(modifier.getLeftTrajectory());
        EncoderFollower right = new EncoderFollower(modifier.getRightTrajectory());

        
        left.configureEncoder(encoder_position, 1000, wheel_diameter);
        right.configureEncoder(encoder_position, 1000, wheel_diameter);

    
        left.configurePIDVA(1.0, 0.0, 0.0, 1 / max_velocity, 0);
        right.configurePIDVA(1.0, 0.0, 0.0, 1 / max_velocity, 0);

        double output = left.calculate(encoder_position);

        double l = left.calculate(encoder_position_left);
        double r = right.calculate(encoder_position_right);

        double gyro_heading = ... your gyro code here ...    // Assuming the gyro is giving a value in degrees
        double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference;

        setLeftMotors(l + turn);
        setRightMotors(r - turn);

    }

}
