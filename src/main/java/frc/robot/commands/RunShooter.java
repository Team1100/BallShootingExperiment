/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RunShooter extends CommandBase {
  private double topSpeed, botSpeed;
  private double lastTopSpeed, lastBotSpeed;

  private boolean isFinished;
  /**
   * Creates a new DefaultShooter.
   */
  public RunShooter(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lastTopSpeed = SmartDashboard.getNumber("Top Shooter Speed", 0);
    lastBotSpeed = SmartDashboard.getNumber("Bottom Shooter Speed", 0);
    isFinished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    topSpeed = SmartDashboard.getNumber("Top Shooter Speed", 0);
    botSpeed = SmartDashboard.getNumber("Bottom Shooter Speed", 0);

    if(lastTopSpeed != topSpeed || lastBotSpeed != botSpeed){
      isFinished = true;
    }

    lastTopSpeed = topSpeed;
    lastBotSpeed = botSpeed;

    Shooter.getInstance().setTop(topSpeed);
    Shooter.getInstance().setBottom(-botSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.getInstance().setTop(0);
    Shooter.getInstance().setBottom(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
