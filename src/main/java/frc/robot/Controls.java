package frc.robot;

import edu.wpi.first.wpilibj2.command.Commands;

public class Controls {
  public static void controlsConfig(RobotContainer bot){
    // Runs the given motor (based on buttons) to the quick set speed
    bot.controller.triangle_y.whileTrue(bot.motorMan.cmdQuickSet(1));
    bot.controller.circle_b.whileTrue(bot.motorMan.cmdQuickSet(2));
    bot.controller.cross_a.whileTrue(bot.motorMan.cmdQuickSet(3));
    bot.controller.square_x.whileTrue(bot.motorMan.cmdQuickSet(4));

    // Runs the set motor to a set power
    bot.controller.rightTriggerB.whileTrue(bot.motorMan.cmdSetCurrentMotor(75));
    bot.controller.leftTriggerB.whileTrue(bot.motorMan.cmdSetCurrentMotor(-75));

    bot.controller.dpadDown.onTrue(Commands.runOnce(() -> {
      bot.motorMan.refreshMotorTypes();
    }));
  }
  
}
