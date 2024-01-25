package frc.robot.lib.GenericMotors;


import com.ctre.phoenix6.hardware.TalonFX;

public class GenericTalonFX implements GenericMotor{
  TalonFX motorTalon;
  int motorID;

  public GenericTalonFX(int motorID){
    this.motorID = motorID;

    this.motorTalon = new TalonFX(this.motorID);
    System.out.printf("New TalonFX constructed with GenericTalonFX at motor ID: %d", this.motorID);
  }
  
  @Override
  public void setSpeed(double speedPercentage) {
    motorTalon.set(speedPercentage);
  }

  @Override
  public double getSpeed() {
    return motorTalon.get();
  }

  @Override
  public void setInverted(boolean isInverted) {
    motorTalon.setInverted(isInverted);
  }

  @Override
  public boolean getInverted() {
    return motorTalon.getInverted();
  }
  
}
