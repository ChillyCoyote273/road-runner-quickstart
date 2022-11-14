package org.firstinspires.ftc.teamcode.CommandFramework;


import com.acmerobotics.roadrunner.drive.Drive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainCommands.RoadrunnerTrajectoryFollower;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainCommands.UltimateGoalMoment.FollowPath;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainCommands.UltimateGoalMoment.UGLqrPoseStabilization;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Robot;

public abstract class BaseAuto extends LinearOpMode {

	protected Robot robot;
	protected TrajectoryBuilder trajectoryBuilder;

	@Override
	public void runOpMode() {
		robot = new Robot(hardwareMap, Robot.OpMode.Auto, gamepad1, gamepad2);
		setRobotPosition();
		waitForStart();


		robot.getScheduler().forceCommand(setupAuto(robot.getScheduler()));

		while (opModeIsActive()) {
			robot.update();
		}
		robot.scoringMechanism.setWristToStow();

	}

	public abstract Command setupAuto(CommandScheduler scheduler);

	public RoadrunnerTrajectoryFollower followRR(Trajectory trajectory) {
		return new RoadrunnerTrajectoryFollower(this.robot, trajectory);
	}

	public UGLqrPoseStabilization goToLQR(Pose2d pose) {
		return new UGLqrPoseStabilization(robot.drivetrain, pose);
	}

	public FollowPath followPath(Pose2d... poses) {
		return new FollowPath(robot.drivetrain, poses);
	}

	public void setRobotPosition() {

	}


}
