package frc.robot.lib;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class GenericMotorHandler {
  MotorEnum chosenMotor;
  
  int motorID;
  TalonFX motorTalonFX;
  CANSparkMax motorSparkMax;
  CANSparkFlex motorSparkFlex;
  

  public GenericMotorHandler(int motorID){
    this.chosenMotor = MotorEnum.NONE;
    this.motorID = motorID;
    
    this.motorTalonFX = new TalonFX(motorID);

    // Only works with brushless motors (for now)
    this.motorSparkMax = new CANSparkMax(motorID, MotorType.kBrushless);
    this.motorSparkFlex = new CANSparkFlex(motorID, MotorType.kBrushless);

  }

  /**
   * Enum for state management to define motors / motor controllers.
   */
  public enum MotorEnum {
    NONE,
    TALON_FX,
    SPARK_MAX,
    SPARK_FLEX
  } 

  // Hopefully these work for error handling and in general
  private void _setImplementation(double value, DoubleConsumer method){
    try {
      method.accept(value);
    } catch (Exception exception) {
      System.err.println(String.format("Error running _setImplementation with motor at ID: %", this.motorID));
    }
  }
  private double _getImplementation(DoubleSupplier method){
    try {
      return method.getAsDouble();
    } catch (Exception exception) {
      System.err.println(String.format("Error running _getImplementation with motor at ID: %", this.motorID));
      return 0.0;
    }
  }

  /**
   * Method to change the chosen motor for a given GenericMotorHandler instance.
   * 
   * @param motorEnumValue - Value of type MotorEnum
   */
  public void setChosenMotor(MotorEnum motorEnumValue){
    this.chosenMotor = motorEnumValue;
    System.out.println(String.format("chosenMotor at ID %d set to %s", this.motorID, this.chosenMotor.toString()));
  }

  /**
   * Method to set the speed / power for a given GenericMotorHandler instance.
   * 
   * @param powerPercentage - Value of type double, should be between -1.0 and 1.0
   */
  public void setPower(double powerPercentage){
    switch (chosenMotor){
      case TALON_FX:
        _setImplementation(powerPercentage, this.motorTalonFX::set);
      
      case SPARK_MAX:
        _setImplementation(powerPercentage, this.motorSparkMax::set);

      case SPARK_FLEX:
        _setImplementation(powerPercentage, this.motorSparkFlex::set);

      case NONE:
        System.err.println(String.format("You need to pick chosenMotor at ID: %d", this.motorID));
      
      default:
        System.err.println("chosenMotor invalid. This error should never be possible, so if you are seeing this, seek help.");
    }
  }

  /**
   * Method to get the speed / power for a given GenericMotorHandler instance.
   * 
   * @return Value of type double, between -1.0 and 1.0
   */
  public double getPower(){
    switch (chosenMotor){
      case TALON_FX:
        return _getImplementation(this.motorTalonFX::get);
      
      case SPARK_MAX:
        return _getImplementation(this.motorTalonFX::get);

      case SPARK_FLEX:
        return _getImplementation(this.motorTalonFX::get);
      
      case NONE:
        return 0.0;
      default:
        System.err.println("chosenMotor invalid. This error should never be possible, so if you are seeing this, seek help.");
        return 0.0;
    }
  }

        
}
