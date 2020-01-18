/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Piston;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RapidFirePiston extends CommandBase {

  double period;  
  Piston piston;
  Timer timer;
  DoubleSolenoid.Value solenoidState;
	int iterations;
  /**
   * Creates a new RapidFirePiston.
   */
  public RapidFirePiston() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Piston.getInstance());
    piston = Piston.getInstance();
    timer = new Timer();
    iterations = 0;
    period = 0.2;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
    solenoidState = DoubleSolenoid.Value.kReverse;
    period = SmartDashboard.getNumber("period", 0.2);
    piston.getValve().set(solenoidState);
    iterations = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Toggle piston state variable when it gets fully extended or retracted
    // and then turn the piston off
    if (piston.getValve().get() == solenoidState) {
      if (solenoidState == DoubleSolenoid.Value.kForward) {
        solenoidState = DoubleSolenoid.Value.kReverse;
      } else {
        solenoidState = DoubleSolenoid.Value.kForward;
      }
      piston.getValve().set(DoubleSolenoid.Value.kOff);
    }

    // Changes piston state once the period has expired.
    if (timer.hasPeriodPassed(period/2)) {
        piston.getValve().set(solenoidState);
        if (solenoidState == DoubleSolenoid.Value.kForward) {
          iterations++;
        }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return iterations >= SmartDashboard.getNumber("iterations", 5);
  }
}
