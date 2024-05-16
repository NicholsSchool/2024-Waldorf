// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class RobotContainer {
  private Drivetrain drivetrain;
  private XboxController controller;

  public RobotContainer() {
    drivetrain = new Drivetrain();
    controller = new XboxController(0);
    configureBindings();
  }

  private void configureBindings() {
    drivetrain.setDefaultCommand(new RunCommand(
      () -> drivetrain.drive(-controller.getLeftY(), controller.getRightX()), drivetrain));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}