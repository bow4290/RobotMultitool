package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.GenericMotors.BlankGenericMotor;
import frc.robot.lib.GenericMotors.GenericMotor;
import frc.robot.lib.GenericMotors.GenericSparkMax;
import frc.robot.lib.GenericMotors.GenericTalonFX;

/**
 * The pseudo-subsystem used for handling / running motors. 
 */
public class MotorMan extends SubsystemBase{

  
  SendableChooser<MotorEnum> _chooserTemplate = new SendableChooser<>();
  
  double limitedQuickMotorSpeed = 0;
  
  public GenericMotor motor1, motor2, motor3, motor4;
  SendableChooser<MotorEnum> chooserTypeMotor1, chooserTypeMotor2, chooserTypeMotor3, chooserTypeMotor4;
  SendableChooser<GenericMotor> chooserMotor;
  

  public enum MotorEnum{
    None,
    TalonFX,
    SparkMax
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
        break;
    }
  }

  /**
   * Sets up all the SendableChooser instances. Should be ran once at robot initialization.
   */
  public void chooserConfig(){
    _chooserTemplate.setDefaultOption("None", MotorEnum.None);
    _chooserTemplate.addOption("TalonFX", MotorEnum.TalonFX);
    _chooserTemplate.addOption("SparkMax", MotorEnum.SparkMax);

    chooserTypeMotor1 = _chooserTemplate;
    chooserTypeMotor2 = _chooserTemplate;
    chooserTypeMotor3 = _chooserTemplate;
    chooserTypeMotor4 = _chooserTemplate;

    chooserMotor = new SendableChooser<>();
    chooserMotor.setDefaultOption("Motor 1", motor1);
    chooserMotor.addOption("Motor 2", motor2);
    chooserMotor.addOption("Motor 3", motor3);
    chooserMotor.addOption("Motor 4", motor4);

    SmartDashboard.putData("Motor 1 Type", chooserTypeMotor1);
    SmartDashboard.putData("Motor 2 Type", chooserTypeMotor2);
    SmartDashboard.putData("Motor 3 Type", chooserTypeMotor3);
    SmartDashboard.putData("Motor 4 Type", chooserTypeMotor4);
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

  public double getQuickMotorSpeed(){
    return SmartDashboard.getNumber("Quick Set Motor Speed", 0);
  }

  // Commands
  public Command cmdQuickSet(GenericMotor motorObject){
    limitedQuickMotorSpeed = getQuickMotorSpeed();

    if (limitedQuickMotorSpeed > 1.0){
      limitedQuickMotorSpeed = 1.0;
    } else if (limitedQuickMotorSpeed < -1.0){
      limitedQuickMotorSpeed = -1.0;
    }

    return this.runEnd(() ->{
      motorObject.setSpeed(limitedQuickMotorSpeed);
    }, () -> {
      motorObject.setSpeed(0);
    });
  }

  public Command cmdSetCurrentMotor(double speed){
    return this.runEnd(() -> {
      chooserMotor.getSelected().setSpeed(speed);
    }, () -> {
      chooserMotor.getSelected().setSpeed(0);
    });
  }


  @Override
  public void periodic(){
    // - SmartDashboard -
    SmartDashboard.putNumber("Motor 1 Current Speed", motor1.getSpeed());
    SmartDashboard.putNumber("Motor 2 Current Speed", motor2.getSpeed());
    SmartDashboard.putNumber("Motor 3 Current Speed", motor3.getSpeed());
    SmartDashboard.putNumber("Motor 4 Current Speed", motor4.getSpeed());

    SmartDashboard.putNumber("Quick Set Motor Speed", 0);
  }
}
