/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  private Victor turretMotor;
  private static Turret turret;
  /**
   * Creates a new Turret.
   */
  public Turret() {
    turretMotor = new Victor(0);
  }

  public void setSpeed(double speed){
    turretMotor.set(speed);
  }

  public static Turret getInstance(){
    if(turret == null){
      turret = new Turret();
    }
    return turret;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
