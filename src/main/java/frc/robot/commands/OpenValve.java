/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Piston;

public class OpenValve extends CommandBase {
  Piston piston;
	boolean finished = false;
  /**
   * Creates a new CloseValve.
   */
  public OpenValve() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Piston.getInstance());
    piston = Piston.getInstance();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    piston.getValve().set(DoubleSolenoid.Value.kForward);
    finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (piston.getValve().get() == DoubleSolenoid.Value.kForward) {
      piston.getValve().set(DoubleSolenoid.Value.kOff);
      finished = true;
    }    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    piston.getValve().set(DoubleSolenoid.Value.kOff);    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
