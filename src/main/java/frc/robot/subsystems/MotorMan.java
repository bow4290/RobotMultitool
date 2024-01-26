package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.GenericMotors.BlankGenericMotor;
import frc.robot.lib.GenericMotors.GenericMotor;
import frc.robot.lib.GenericMotors.GenericSparkMax;
import frc.robot.lib.GenericMotors.GenericTalonFX;

/**
 * The pseudo-subsystem used for handling / running motors. 
 */
public class MotorMan extends SubsystemBase{

  GenericMotor motor1, motor2, motor3, motor4;
  MotorEnum enumMotor1, enumMotor2, enumMotor3, enumMotor4;
  

  public enum MotorEnum{
    None,
    TalonFX,
    SparkMax
  }

  /**
   * The helper function for handling setting motor type, based on ID. This is not meant to be called directly, instead use {@link #motorSwitch(int, MotorEnum) motorSwitch()}
   * @param motorID - The ID of the motor that should be changed. Value should be of type int between 1-4.
   * @param genericMotorType - The GenericMotor implementation to set. Value should be a new GenericMotor implementation instance.
   * @param enumMotorType - The value that should be set for that motors instance of MotorEnum. Used to handle state.
   */
  private void _motorIDHandler(int motorID, GenericMotor genericMotorType, MotorEnum enumMotorType){
    switch (motorID) {
      case 1:
        this.motor1 = genericMotorType;
        this.enumMotor1 = enumMotorType;
        break;
      case 2:
        this.motor2 = genericMotorType;
        this.enumMotor2 = enumMotorType;
        break;
      case 3:
        this.motor3 = genericMotorType;
        this.enumMotor3 = enumMotorType;
        break;
      case 4:
        this.motor4 = genericMotorType;
        this.enumMotor4 = enumMotorType;
        break;

      default:
        System.err.printf("Attemped to change motor type with ID outside accepted range: ID %d. Nothing has been changed.", motorID);
        break;
    }

  }

  public void motorSwitch(int motorID, MotorEnum motorTypeEnum){
    switch (motorTypeEnum) {
      case None:
        _motorIDHandler(motorID, new BlankGenericMotor(motorID), MotorEnum.None);
        break;
      
      case TalonFX:
        _motorIDHandler(motorID, new GenericTalonFX(motorID), MotorEnum.TalonFX);
        break;

      case SparkMax:
        _motorIDHandler(motorID, new GenericSparkMax(motorID, MotorType.kBrushless), MotorEnum.SparkMax);
        break;

      default:
        break;
    }
  }


  @Override
  public void periodic(){

  }
}
