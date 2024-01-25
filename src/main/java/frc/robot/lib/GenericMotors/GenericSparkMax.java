package frc.robot.lib.GenericMotors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class GenericSparkMax implements GenericMotor {
  int motorID;
  
  CANSparkMax motorSparkMax;
  MotorType motorType;

  public GenericSparkMax(int motorID, MotorType motorType){
    this.motorID = motorID;
    this.motorType = motorType;

    this.motorSparkMax = new CANSparkMax(this.motorID, this.motorType);
    System.out.printf("New CanSparkMax constructed with GenericSparkMax at motor ID: %d", this.motorID);
  }

  @Override
  public void setSpeed(double speedPercentage) {
    motorSparkMax.set(speedPercentage);
  }

  @Override
  public double getSpeed() {
    return motorSparkMax.get();
  }

  @Override
  public void setInverted(boolean isInverted) {
    motorSparkMax.setInverted(isInverted);
  }

  @Override
  public boolean getInverted() {
    return motorSparkMax.getInverted();
  }
  
}
