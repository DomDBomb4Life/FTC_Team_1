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
    private Servo ARM_SERVO, PLANE_SERVO, HAND_SERVO, PINCHER_SERVO;
    @Override
    public void runOpMode(){
        double Move_Power = 1;
        boolean launch = false;
        boolean grab = false;
        boolean grabButtonPressed = false;
        double armServoPosition = -90;
        double handServoPosition = 90;
        R_MOTOR = hardwareMap.get(DcMotor.class, "Right Motor");
        L_MOTOR = hardwareMap.get(DcMotor.class, "Left Motor");
        R_MOTOR.setDirection(DcMotorSimple.Direction.REVERSE);
        L_MOTOR.setDirection(DcMotorSimple.Direction.REVERSE);
        //ULT_SENSOR = hardwareMap.get(UltrasonicSensor.class, "Ultrasonic Sensor");
        ARM_SERVO = hardwareMap.get(Servo.class, "Arm Servo");
        PLANE_SERVO = hardwareMap.get(Servo.class, "Plane Servo");
        //HAND_SERVO = hardwareMap.get(Servo.class, "Hand Servo");
        //PINCHER_SERVO = hardwareMap.get(Servo.class, "Pincher Servo");
        PLANE_SERVO.setPosition(0);
        ARM_SERVO.setPosition(-90);
        waitForStart();
        while(opModeIsActive()){
            double LStick1Y = gamepad1.left_stick_y;
            double RStick1X = gamepad1.right_stick_x;
            double LStick2Y = gamepad2.left_stick_y;
            double RStick2Y = gamepad2.right_stick_y;
            //GAMEPAD1 CODE START     \/
            if(gamepad1.a){
                Move_Power = 0.25;
            }
            else{
                Move_Power = 1;
            }
            if(gamepad1.x){
                PLANE_SERVO.setPosition(90);
            }
            if(RStick1X != 0){
                R_MOTOR.setPower(RStick1X*Move_Power);
                L_MOTOR.setPower(-RStick1X*Move_Power);
            }
            else if(LStick1Y != 0){
                R_MOTOR.setPower(LStick1Y*Move_Power);
                L_MOTOR.setPower(LStick1Y*Move_Power);
            }
            else{
                R_MOTOR.setPower(0);
                L_MOTOR.setPower(0);
            }
            //GAMEPAD1 CODE END       /\
            //GAMEPAD2 CODE START     \/
            if(grabButtonPressed == false){
                if(gamepad2.a){
                    if(grab){
                        grab = false;
                    }
                    else{
                        grab = true;
                    }
                }
            }
            if(gamepad2.a){
                grabButtonPressed = true;
            }
            else{
                grabButtonPressed = false;
            }
            // if(grab){
            //     PINCHER_SERVO.setPosition(90);
            // }
            // else{
            //     PINCHER_SERVO.setPosition(0);
            // }
            if(armServoPosition + 0.1 <= 90 && armServoPosition - 0.1 >= -90){
                if(LStick2Y != 0){
                    if(LStick2Y < 0){
                        ARM_SERVO.setPosition(ARM_SERVO.getPosition() - 0.1);
                        telemetry.addData("Arm Position -: ", ARM_SERVO.getPosition());
                    }
                    else{
                        ARM_SERVO.setPosition(ARM_SERVO.getPosition() + 0.1);
                        telemetry.addData("Arm Position +: ", ARM_SERVO.getPosition());
                    }
                }
            }
            telemetry.addData("Arm Position: ", ARM_SERVO.getPosition());
            telemetry.update();
            // if(handServoPosition + 0.1 <= 90 && handServoPosition - 0.1 >= -75){
            //     if(RStick2Y != 0){
            //         if(RStick2Y < 0){
            //             handServoPosition -= 0.1;
            //         }
            //         else{
            //             handServoPosition += 0.1;
            //         }
            // //         HAND_SERVO.setPosition(handServoPosition);
            //     }
            // }
            //GAMEPAD2 CODE END       /\
        }
    }
}
