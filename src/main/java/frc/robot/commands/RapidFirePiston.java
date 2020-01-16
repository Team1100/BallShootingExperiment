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
  private static final int STATE1 = 1;
	private static final int STATE2 = 2;
  private static final int STATE3 = 3;
  
  private static final double PERIOD = 0.1;
  private static final double SPERIOD = 0.1;
  
  Piston piston;
  int state = STATE1;
  Timer timer;
  Timer sTimer;
  DoubleSolenoid.Value solenoidState;
	int stateCounter = 0;
  /**
   * Creates a new RapidFirePiston.
   */
  public RapidFirePiston() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Piston.getInstance());
    piston = Piston.getInstance();
    timer = new Timer();
    sTimer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    state = STATE1;
    timer.start();
    sTimer.start();
    solenoidState = DoubleSolenoid.Value.kForward;
    stateCounter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (state) {
    	case STATE1:
    			state = STATE2;
    			piston.getValve().set(solenoidState);
    		break;
    	case STATE2:
    		if (piston.getValve().get() == solenoidState) {
    	    	if (solenoidState == DoubleSolenoid.Value.kForward) {
    				solenoidState = DoubleSolenoid.Value.kReverse;
    			} else {
    				solenoidState = DoubleSolenoid.Value.kForward;
    			}
    	    	piston.getValve().set(DoubleSolenoid.Value.kOff);
    		}
    		if (sTimer.hasPeriodPassed(SPERIOD)) {
				  piston.getValve().set(solenoidState);
    		}
    		if (timer.hasPeriodPassed(PERIOD)) {
    			piston.getValve().set(DoubleSolenoid.Value.kOff);
    			state = STATE3;
    		}
    		break;
    	case STATE3:
    			stateCounter++;
    			state = STATE1;
    		break;
    	default:
    		break;
    	}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stateCounter >= SmartDashboard.getNumber("iterations", 5);
  }
}
