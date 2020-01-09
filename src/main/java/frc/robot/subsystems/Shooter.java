/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private WPI_TalonSRX shooterMotor;
  private static Shooter shooter;
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    shooterMotor = new WPI_TalonSRX(0);
  }

  public void runShooter(double speed){
    shooterMotor.set(speed);
  }

  public static Shooter getInstance(){
    if(shooter == null){
      shooter = new Shooter();
    }
    return shooter;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
