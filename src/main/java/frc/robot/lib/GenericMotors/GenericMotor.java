package frc.robot.lib.GenericMotors;

/**
 * An unofficial generic for motors, similiar to MotorController interface. Not entirely intended for every-day use cases.
 */
public interface GenericMotor {

  void setSpeed(double speedPercentage);
  
  double getSpeed();

  void setInverted(boolean isInverted);

  boolean getInverted();

  



}
