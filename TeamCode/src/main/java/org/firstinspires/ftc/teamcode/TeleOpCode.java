// import nessecary packages and libraries
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
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
       boolean score = false;
       boolean grabButtonPressed = false;
       boolean scoreButtonPressed = false;
       double armServoPosition = -90;
       //0.1,0.6,0.9
       double handServoPosition = 90;
       R_MOTOR = hardwareMap.get(DcMotor.class, "Right Motor");
       L_MOTOR = hardwareMap.get(DcMotor.class, "Left Motor");
       //R_MOTOR.setDirection(DcMotorSimple.Direction.REVERSE);
       L_MOTOR.setDirection(DcMotorSimple.Direction.REVERSE);
       //ULT_SENSOR = hardwareMap.get(DistanceSensor.class, "Ultrasonic Sensor");
       ARM_SERVO = hardwareMap.get(Servo.class, "Arm Servo");
       PLANE_SERVO = hardwareMap.get(Servo.class, "Plane Servo");
       HAND_SERVO = hardwareMap.get(Servo.class, "Hand Servo");
       PINCHER_SERVO = hardwareMap.get(Servo.class, "Pincher Servo");
       PLANE_SERVO.setPosition(0.8);
       ARM_SERVO.setPosition(0);
       waitForStart();
       while(opModeIsActive()){
           double RightTrigger = gamepad1.right_trigger;
           double LeftTrigger = gamepad1.left_trigger;
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
           if(LeftTrigger != 0){
               Move_Power = -Move_Power;
           }
           if(gamepad1.x){
               PLANE_SERVO.setPosition(0);
           }
           if(RStick1X != 0){
               R_MOTOR.setPower(RStick1X*Move_Power);
               L_MOTOR.setPower(-RStick1X*Move_Power);
           }
           else if(RightTrigger != 0){
               R_MOTOR.setPower(RightTrigger*Move_Power);
               L_MOTOR.setPower(RightTrigger*Move_Power);
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
           if(scoreButtonPressed == false){
               if(gamepad2.x){
                   if(score){
                       score = false;
                   }
                   else{
                       score = true;
                   }
               }
           }
           if(gamepad2.a){
               grabButtonPressed = true;
           }
           else{
            grabButtonPressed = false
           }
               
           if(gamepad2.x){
               scoreButtonPressed = true;
           }
           else{
               scoreButtonPressed = false;
           }
            if(grab){
                PINCHER_SERVO.setPosition(1);
            }
            else{
                PINCHER_SERVO.setPosition(0);
            }
            if(score){
                ARM_SERVO.setPosition(0.75);
            }
            else{
                ARM_SERVO.setPosition(0);
            }
           if(LStick2Y != 0 && score == false){
               if(LStick2Y < 0){
                   armServoPosition -= 0.0025;
                   ARM_SERVO.setPosition(armServoPosition);
                   telemetry.addData("Arm Position -: ", armServoPosition);
               }
               else{
                   armServoPosition += 0.0025;
                   ARM_SERVO.setPosition(armServoPosition);
                   telemetry.addData("Arm Position +: ", armServoPosition);
               }
           }
           if (armServoPosition > 1){
               armServoPosition = 1;
           }
           else if (armServoPosition < 0){
               armServoPosition = 0;
           }
           telemetry.addData("Arm Position: ", armServoPosition);
           telemetry.update();
           //GAMEPAD2 CODE END       /\
       }
   }
}

