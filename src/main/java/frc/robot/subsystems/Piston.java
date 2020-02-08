/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Piston extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
	private static Piston piston;
	Compressor compressor;
  	DoubleSolenoid valve;
  
  /**
   * Creates a new Piston.
   */
  public Piston() {
		// Instantiate left motor class
		//compressor = new Compressor(RobotMap.P_PISTON);
		//compressor.setClosedLoopControl(true);
		valve = new DoubleSolenoid(RobotMap.P_PCM_CAN, RobotMap.P_PORT0, RobotMap.P_PORT1);
  }

	/**
	 * Gets the singular instance of the Piston subsystem
	 * @return the singular instance of the Piston subsystem
	 */
	public static Piston getInstance() {
		if (piston == null) piston = new Piston();
		return piston;
	}

	public DoubleSolenoid getValve() {
		return valve;
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
