package frc.robot.lib;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.MotorMan.MotorEnum;

public class MotorTypeChooserTemplate extends SendableChooser<MotorEnum> {
  public MotorTypeChooserTemplate(){
    this.setDefaultOption("None", MotorEnum.None);
    this.addOption("TalonFX", MotorEnum.TalonFX);
    this.addOption("SparkMax", MotorEnum.SparkMax);
  }
  
}
