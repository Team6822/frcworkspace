//https://gist.github.com/DomenicP/7252269
//https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599717-encoders-measuring-rotation-of-a-wheel-or-other-shaft
//http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/Encoder.html
//https://github.com/SkylineSpartabots/KnowledgeCrates/wiki/Encoder-Overview
//https://github.com/Phred7/SentinelSteamworks/blob/master/src/org/usfirst/frc2906/SentinelSteamworks/Robot.java
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
public class Encoder {

    public  frontLeft = new TalonSRX(0);
    public  backLeft = new VictorSP(1);
    public  frontRight = new TalonSRX(2);
    public  backRight = new VictorSP(3);
    private RobotDrive myRobot = new RobotDrive(frontLeft, backLeft, frontRight, backRight); // robot drive system
    
    
    public static final double kDistancePerRevolution = 18.84;  // guestimate from your code
    public static final double kPulsesPerRevolution = 1024;     // for an AS5145B Magnetic Encoder
    public static final double kDistancePerPulse = kDistancePerRevolution / kPulsesPerRevolution;

    //change parameters
    private Encoder rightEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X); 
    private Encoder leftEncoder = new Encoder (0, 1, false, Encoder.EncodingType.k4x);
    //public Encoder(int channelA, int channelB, boolean reverseDirection, CounterBase.EncodingType encodingType)

    //change parameters for these
    sampleEncoder.setMaxPeriod(.1); 
    sampleEncoder.setMinRate(10);
    sampleEncoder.setDistancePerPulse(5);
    sampleEncoder.setReverseDirection(true);
    sampleEncoder.setSamplesToAverage(7);

    public void robotInit() {
        leftEncoder.setDistancePerPulse(kDistancePerPulse);
        rightEncoder.setDistancePerPulse(kDistancePerPulse);

        leftEncoder.start();
        rightEncoder.start();

        resetEncoders();
    }
    

    private void resetEncoders() {
       leftEncoder.reset();
       rightEncoder.reset();
    }
    

    //MOVE THIS INTO ANOTHER FILE AS NEEDED
    public void autonomous() {
        // Drive forwards for 50 units
        resetEncoders();
        do {
            drive.arcadeDrive(0.5, 0.0);
        } while (getAverageEncoderPosition() < 50.0);
        drive.arcadeDrive(0.0, 0.0);
    }

    private double getAverageEncoderPosition() {
        return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
    }

    private double getAverageEncoderSpeed() {

        return ((leftEncoder.getRate() + rightEncoder.getRate()) / 2.0);
   
    }

    
    //MOVE THIS INTO ANOTHER FILE AS NEEDED

    private double getAverageEncoderAngularVelocity(){  //use online formulas to find

    }


    
 
}