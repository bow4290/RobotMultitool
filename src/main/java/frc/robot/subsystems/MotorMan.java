package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.MotorTypeChooserTemplate;
import frc.robot.lib.GenericMotors.BlankGenericMotor;
import frc.robot.lib.GenericMotors.GenericMotor;
import frc.robot.lib.GenericMotors.GenericSparkMax;
import frc.robot.lib.GenericMotors.GenericTalonFX;

/**
 * The pseudo-subsystem used for handling / running motors. 
 */
public class MotorMan extends SubsystemBase{
  
  double quickSetMotorSpeed = 0;
  
  SendableChooser<Integer> chooserMotor;
  SendableChooser<MotorEnum> chooserTypeMotor1, chooserTypeMotor2, chooserTypeMotor3, chooserTypeMotor4 = new SendableChooser<>();
  
  private GenericMotor motor1, motor2, motor3, motor4;
  
  public enum MotorEnum{
    None,
    TalonFX,
    SparkMax
  }

  public MotorMan(){
    chooserTypeMotor1 = new MotorTypeChooserTemplate();
    chooserTypeMotor2 = new MotorTypeChooserTemplate();
    chooserTypeMotor3 = new MotorTypeChooserTemplate();
    chooserTypeMotor4 = new MotorTypeChooserTemplate();

    chooserMotor = new SendableChooser<>();
    chooserMotor.setDefaultOption("Motor 1", 1);
    chooserMotor.addOption("Motor 2", 2);
    chooserMotor.addOption("Motor 3", 3);
    chooserMotor.addOption("Motor 4", 4);

    SmartDashboard.putData("Motor 1 Type", chooserTypeMotor1);
    SmartDashboard.putData("Motor 2 Type", chooserTypeMotor2);
    SmartDashboard.putData("Motor 3 Type", chooserTypeMotor3);
    SmartDashboard.putData("Motor 4 Type", chooserTypeMotor4);

    SmartDashboard.putData("Current Set Motor", chooserMotor);
  }

  // - Commands -
  public Command cmdQuickSet(int motorID){

    return this.runEnd(() ->{
      getMotor(motorID).setSpeed(getQuickMotorSpeed());
    }, () -> {
      getMotor(motorID).setSpeed(0);
    });
  }

  public Command cmdSetCurrentMotor(double speed){
    return this.runEnd(() -> {
      getMotor(chooserMotor.getSelected()).setSpeed(speed);
    }, () -> {
      getMotor(chooserMotor.getSelected()).setSpeed(0);
    });
  }

  /**
   * A safe way to interact with motors defined in MotorMan, using their IDs exclusively.
   * 
   * @param motorID - The ID of the motor to interact with
   * @return GenericMotor instance
   */
  public GenericMotor getMotor(int motorID){
    GenericMotor thisMotor = null;
    switch (motorID) {
      case 1:
        thisMotor = motor1;
        break;
      case 2:
        thisMotor = motor2;
        break;
      case 3:
        thisMotor = motor3;
        break;
      case 4:
        thisMotor = motor4;
        break;
      default:
        System.err.println(String.format("getMotor() was called with a number that exceeds the limit, of value: %d", motorID));
        break;
    }

    if (thisMotor == null){
      return new BlankGenericMotor(motorID);
    }

    return thisMotor;

  }

  /**
   * Automatically sets all motors to the types selected by their individual SendableChooser instances.
   */
  public void refreshMotorTypes(){
    setMotorType(1, chooserTypeMotor1.getSelected());
    setMotorType(2, chooserTypeMotor2.getSelected());
    setMotorType(3, chooserTypeMotor3.getSelected());
    setMotorType(4, chooserTypeMotor4.getSelected());
  }


  /**
   * Sets the motor type for a specific GenericMotor instance, based on its ID and the MotorEnum value to be switched to. In most cases, {@link #refreshMotorTypes()} should be used instead.
   * 
   * @param motorID - The ID of the motor to set, of value int.
   * @param motorTypeEnum - The type of motor to change to, of value MotorEnum.
   */
  public void setMotorType(int motorID, MotorEnum motorTypeEnum){
    switch (motorTypeEnum) {
      case None:
        _motorIDHandler(motorID, new BlankGenericMotor(motorID));
        break;
      
      case TalonFX:
        _motorIDHandler(motorID, new GenericTalonFX(motorID));
        break;

      case SparkMax:
        _motorIDHandler(motorID, new GenericSparkMax(motorID, MotorType.kBrushless));
        break;

      default:
        System.err.printf("setMotorType() invalid at Motor ID at: %d with MotorEnum value: %s \n", motorID, motorTypeEnum);
        break;
    }
  }

  public void incrementQuickMotorSpeed(){
    if (quickSetMotorSpeed >= 1){
    }else{
      quickSetMotorSpeed += 0.05;
    }
  }

  public void decrementQuickMotorSpeed(){
    if (quickSetMotorSpeed <= 0){
    }else {
      quickSetMotorSpeed -= 0.05;
    }

  }

  private double getQuickMotorSpeed(){
    return quickSetMotorSpeed;
  }


  /**
   * The helper function for handling setting motor type, based on ID. This is not meant to be called directly, instead use {@link #setMotorType(int, MotorEnum) motorSwitch()}
   * @param motorID - The ID of the motor that should be changed. Value should be of type int between 1-4.
   * @param genericMotorType - The GenericMotor implementation to set. Value should be a new GenericMotor implementation instance.
   */
  private void _motorIDHandler(int motorID, GenericMotor genericMotorType){
    switch (motorID) {
      case 1:
        motor1 = genericMotorType;
        break;
      case 2:
        motor2 = genericMotorType;
        break;  
      case 3:
        motor3 = genericMotorType;
        break;
      case 4:
        motor4 = genericMotorType;
        break;

      default:
        System.err.printf("Attemped to change motor type with ID outside accepted range: ID %d. Nothing has been changed.", motorID);
        break;
    }

  }


  @Override
  public void periodic(){
    // - SmartDashboard -
    SmartDashboard.putNumber("Motor 1 Current Speed", getMotor(1).getSpeed());
    SmartDashboard.putNumber("Motor 2 Current Speed", getMotor(2).getSpeed());
    SmartDashboard.putNumber("Motor 3 Current Speed", getMotor(3).getSpeed());
    SmartDashboard.putNumber("Motor 4 Current Speed", getMotor(4).getSpeed());
    SmartDashboard.putNumber("Quickset Motor Speed", getQuickMotorSpeed());
  }
}
