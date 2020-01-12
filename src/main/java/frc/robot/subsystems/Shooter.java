/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private WPI_TalonSRX bottomShooter;
  private WPI_TalonSRX topShooter;

  private Encoder botEncoder;
  private Encoder topEncoder;

  private final double PPD = 2048;

  private static Shooter shooter;
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    bottomShooter = new WPI_TalonSRX(0);
    topShooter = new WPI_TalonSRX(1);

    botEncoder = new Encoder(0,1);
    topEncoder = new Encoder(2,3);

    botEncoder.setDistancePerPulse(1/PPD);
    topEncoder.setDistancePerPulse(1/PPD);

  }

  public void setSpeed(double baseSpeed, double ... differential){
    if(differential.length == 0){
      bottomShooter.set(-baseSpeed);
      topShooter.set(baseSpeed);
    }
    else if(differential.length == 1){
      bottomShooter.set(-baseSpeed);
      topShooter.set(baseSpeed + differential[0]);
    }
    else{
      throw new IllegalArgumentException("Too many arguments passed as a differential");
    }
    
  }

  public void setBottom(double speed){
    bottomShooter.set(-speed);
  }

  public void setTop(double speed){
    topShooter.set(speed);
  }

  public Encoder getBottomEncoder(){
    return botEncoder;
  }

  public Encoder getTopEncoder(){
    return topEncoder;
  }

  public double getRPM(Encoder encoder){
    return encoder.getRate() * 60;
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
