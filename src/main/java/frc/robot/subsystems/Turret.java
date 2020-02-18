/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  private VictorSP turretMotor;
  private static Turret turret;
  private Encoder encoder;
  private double PPD = 1024;
  /**
   * Creates a new Turret.
   */
  public Turret() {
    turretMotor = new VictorSP(0);
    encoder = new Encoder(4, 5);
    encoder.setDistancePerPulse(1/PPD);
  }

  public void setSpeed(double speed){
    if(Vision.getInstance().getIsValid().getBoolean(false)){
      turretMotor.set(speed);
    }
    else{
      turretMotor.set(0);
    }
  }

  public static Turret getInstance(){
    if(turret == null){
      turret = new Turret();
    }
    return turret;
  }

  public Encoder getEncoder(){
    return encoder;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
