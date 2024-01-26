package frc.robot.lib.GenericMotors;

public class BlankGenericMotor implements GenericMotor {
  int motorID;

  public BlankGenericMotor(int motorID){
    this.motorID = motorID;
    System.out.printf("New BlankGenericMotor constructed at motor ID: %d", this.motorID);
  }

  @Override
  public void setSpeed(double speedPercentage) {
    System.out.printf("Blank Motor at ID: %d: setSpeed %f", this.motorID, speedPercentage);
  }

  @Override
  public double getSpeed() {
    System.out.printf("Blank Motor at ID %d: getSpeed (cannot store data, returning 0)", this.motorID);
    return 0;
  }

  @Override
  public void setInverted(boolean isInverted) {
    System.out.printf("Blank Motor at ID: %d: setInverted %b", this.motorID, isInverted);
  }

  @Override
  public boolean getInverted() {
    System.out.printf("Blank Motor at ID %d: getInverted (cannot store data, returning false)", this.motorID);
    return false;
  }
  
}
