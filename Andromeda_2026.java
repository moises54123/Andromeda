package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Andromeda_2026", group = "TeleOp")
public class Andromeda_2026 extends OpMode {

    // Declarar motores
    DcMotor MDI, frontRight, backLeft, backRight;

    @Override
    public void init() {
        // Inicializar motores
        MDI = hardwareMap.dcMotor.get("MDI");
        frontRight = hardwareMap.dcMotor.get("front_right");
        backLeft = hardwareMap.dcMotor.get("back_left");
        backRight = hardwareMap.dcMotor.get("back_right");

        // Configurar dirección de motores
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction. REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        // Configurar modo de los motores
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        // Obtener inputs del gamepad
        double y = -gamepad1.left_stick_y;  // Invertido para que adelante sea positivo
        double x = gamepad1.left_stick_x;    // Strafe
        double rx = gamepad1.right_stick_x;  // Rotación

        // Calcular potencias para cada motor
        double frontLeftPower = y + x + rx;
        double frontRightPower = y - x - rx;
        double backLeftPower = y - x + rx;
        double backRightPower = y + x - rx;

        // Normalizar valores si exceden 1.0
        double max = Math.max(1.0, Math.abs(frontLeftPower));
        max = Math.max(max, Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 1.0) {
            frontLeftPower /= max;
            frontRightPower /= max;
            backLeftPower /= max;
            backRightPower /= max;
        }

        // Asignar potencias a los motores
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        // Telemetría para debug
        telemetry.addData("Front Left", frontLeftPower);
        telemetry.addData("Front Right", frontRightPower);
        telemetry.addData("Back Left", backLeftPower);
        telemetry.addData("Back Right", backRightPower);
        telemetry.update();
    }
}