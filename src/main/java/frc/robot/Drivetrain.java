// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

@SuppressWarnings("removal")
public class Drivetrain extends SubsystemBase {
  private final double MAX_DRIVE_SPEED = 0.25;
  private final double MAX_DRIVE_INPUT_RATE = 1.0;

  private final double MAX_TURN_SPEED = 0.25;
  private final double MAX_TURN_INPUT_RATE = 1.0;

  private final MotionProfile driveProfile;
  private final MotionProfile turnProfile;

  private WPI_TalonFX backLeft;
  private WPI_TalonFX backRight;
  private WPI_TalonFX frontLeft;
  private WPI_TalonFX frontRight;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    driveProfile = new MotionProfile(MAX_DRIVE_INPUT_RATE, MAX_DRIVE_SPEED);
    turnProfile = new MotionProfile(MAX_TURN_INPUT_RATE, MAX_TURN_SPEED);

    backLeft = new WPI_TalonFX(13);
    backRight = new WPI_TalonFX(12);
    frontLeft = new WPI_TalonFX(14);
    frontRight = new WPI_TalonFX( 11);

    backLeft.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);
    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);

    backLeft.setInverted(true);
    frontLeft.setInverted(true);
  }

  public void drive(double driveInput, double turnInput) {
    driveInput = driveProfile.calculate(driveInput);
    turnInput = turnProfile.calculate(turnInput);

    backLeft.set(driveInput + turnInput);
    backRight.set(driveInput - turnInput);
    frontLeft.set(driveInput + turnInput);
    frontRight.set(driveInput - turnInput);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}