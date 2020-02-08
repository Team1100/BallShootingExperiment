/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
  public static NetworkTable nt;
  public NetworkTableEntry yaw;
  public static Vision vision;

  /**
   * Creates a new Vision.
   */
  public Vision() {
    nt = NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable("HD Pro Webcam C920");
    yaw = nt.getEntry("targetYaw");
  }

  public static Vision getInstance(){
    if(vision == null){
      vision = new Vision();
    }
    return vision;
  }

  public NetworkTableEntry getYaw(){
    return yaw;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
