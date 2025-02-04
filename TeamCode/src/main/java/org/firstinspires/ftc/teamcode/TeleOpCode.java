// import nessecary packages and libraries
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import java.lang.Math;
@TeleOp(name = "Drive")
public class TeleOpCode extends LinearOpMode{
    //WHEELS!
    private DcMotor R_MOTOR, L_MOTOR;
    //private UltrasonicSensor ULT_SENSOR;
    private Servo ARM_SERVO;
    @Override
    public void runOpMode(){
        int Move_Power = 1;
        boolean launch = false;
        R_MOTOR = hardwareMap.get(DcMotor.class, "Right Motor");
        L_MOTOR = hardwareMap.get(DcMotor.class, "Left Motor");
        R_MOTOR.setDirection(DcMotorSimple.Direction.REVERSE);
        L_MOTOR.setDirection(DcMotorSimple.Direction.REVERSE);
        //ULT_SENSOR = hardwareMap.get(UltrasonicSensor.class, "Ultrasonic Sensor");
        PLANE_SERVO = hardwareMap.get(Servo.class, "Plane Servo");
        PLANE_SERVO.setPosition(0);
        waitForStart();
        while(opModeIsActive()){
            double LStickY = gamepad1.left_stick_y;
            double RStickX = gamepad1.right_stick_x;
            if(gamepad1.a){
                Move_Power = 0.5;
            }
            else{
                Move_Power = 1;
            }
            if(gamepad1.x){
                PLANE_SERVO.setPosition(90);
            }
            if(RStickX != 0){
                R_MOTOR.setPower(RStickX*Move_Power);
                L_MOTOR.setPower(-RStickX*Move_Power);
            }
            else if(LStickY != 0){
                R_MOTOR.setPower(LStickY*Move_Power);
                L_MOTOR.setPower(LStickY*Move_Power);
            }
            else{
                R_MOTOR.setPower(0);
                L_MOTOR.setPower(0);
            }
        }
    }
}
