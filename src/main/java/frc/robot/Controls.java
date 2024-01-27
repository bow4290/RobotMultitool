package frc.robot;

import edu.wpi.first.wpilibj2.command.Commands;

public class Controls {
  public static void controlsConfig(RobotContainer bot){
    // Runs the given motor (based on butotns) to the quick set speed
    bot.controller.triangle_y.onTrue(bot.motorMan.cmdQuickSet(bot.motorMan.getMotor(1)));
    bot.controller.circle_b.onTrue(bot.motorMan.cmdQuickSet(bot.motorMan.getMotor(2)));
    bot.controller.cross_a.onTrue(bot.motorMan.cmdQuickSet(bot.motorMan.getMotor(3)));
    bot.controller.square_x.onTrue(bot.motorMan.cmdQuickSet(bot.motorMan.getMotor(4)));

    // Runs the set motor to speed (based on power of triggers)
    bot.controller.rightTriggerB.onTrue(bot.motorMan.cmdSetCurrentMotor(bot.controller.rightTrigger.getAsDouble()));
    bot.controller.leftTriggerB.onTrue(bot.motorMan.cmdSetCurrentMotor(-bot.controller.leftTrigger.getAsDouble()));

    bot.controller.dpadDown.onTrue(Commands.runOnce(() -> {
      bot.motorMan.refreshMotorTypes();
    }));
  }
  
}
