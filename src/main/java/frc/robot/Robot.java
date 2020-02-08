/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.TopPIDShooter;
import frc.robot.commands.BottomPIDShooter;
import frc.robot.commands.CloseValve;
import frc.robot.commands.FireBall;
import frc.robot.commands.OpenValve;
import frc.robot.commands.PIDTurret;
import frc.robot.commands.RapidFirePiston;
import frc.robot.commands.RunTurret;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    SmartDashboard.putNumber("Turret Speed", 0);
    SmartDashboard.putData("Run Turret", new RunTurret(Turret.getInstance()));
    SmartDashboard.putNumber("Top Setpoint", 0);
    SmartDashboard.putNumber("Bottom Setpoint", 0);

    SmartDashboard.putData("Top PID Shooter", new TopPIDShooter());
    SmartDashboard.putData("Bottom PID Shooter", new BottomPIDShooter());


    SmartDashboard.putNumber("Bottom Rotations", 0);
    SmartDashboard.putNumber("Top Rotations", 0);
    SmartDashboard.putNumber("Top RPM", 0);
    SmartDashboard.putNumber("Bot RPM", 0);


    Shooter.getInstance().getBotEncoder().reset();
    Shooter.getInstance().getTopEncoder().reset();
    //SmartDashboard.putNumber("Shooter Differential", 0);

    //DefaultShooter ds = new DefaultShooter(Shooter.getInstance());
    //ds.schedule();

    SmartDashboard.putNumber("iterations", 5);
    SmartDashboard.putNumber("period", 0.2);
    SmartDashboard.putData(new OpenValve());
    SmartDashboard.putData(new CloseValve());
    SmartDashboard.putData(new RapidFirePiston());
    SmartDashboard.putData(new PIDTurret());
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Bottom Rotations", Shooter.getInstance().getBotEncoder().getDistance());
    SmartDashboard.putNumber("Top Rotations", Shooter.getInstance().getTopEncoder().getDistance());
    SmartDashboard.putNumber("Top RPM", Shooter.getInstance().getRPM(Shooter.getInstance().getTopEncoder()));
    SmartDashboard.putNumber("Bot RPM", Shooter.getInstance().getRPM(Shooter.getInstance().getBotEncoder()));

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
