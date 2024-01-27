package frc.robot;



public class Controls {
  public static void controlsConfig(RobotContainer bot){
    // Runs the given motor (based on butotns) to the quick set speed
    bot.controller.triangle_y.onTrue(bot.motorMan.cmdQuickSet(bot.motorMan.motor1));
    bot.controller.circle_b.onTrue(bot.motorMan.cmdQuickSet(bot.motorMan.motor2));
    bot.controller.cross_a.onTrue(bot.motorMan.cmdQuickSet(bot.motorMan.motor3 ));
    bot.controller.square_x.onTrue(bot.motorMan.cmdQuickSet(bot.motorMan.motor4));

    // Runs the set motor to speed (based on power of triggers)
    bot.controller.rightTriggerB.onTrue(bot.motorMan.cmdSetCurrentMotor(bot.controller.rightTrigger.getAsDouble()));
    bot.controller.leftTriggerB.onTrue(bot.motorMan.cmdSetCurrentMotor(-bot.controller.leftTrigger.getAsDouble()));
  }
  
}
