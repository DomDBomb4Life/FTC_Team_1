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
    private UltrasonicSensor ULT_SENSOR;
    private Servo ARM_SERVO;
    @Override
    public void runOpMode(){
        int Move_Power = 1;
        boolean FocusMode = false;
        R_MOTOR = hardwareMap.get(DcMotor.class, "Right Motor");
        L_MOTOR = hardwareMap.get(DcMotor.class, "Left Motor");
        //ULT_SENSOR = hardwareMap.get(UltrasonicSensor.class, "Ultrasonic Sensor");
        ARM_SERVO = hardwareMap.get(Servo.class, "Arm Servo");
        ARM_SERVO.setPosition(0);
        waitForStart();
        while(opModeIsActive()){
            double LStickY = gamepad1.left_stick_y;
            double RStickX = gamepad1.right_stick_x;
            if(gamepad1.a){
                if(FocusMode){
                    FocusMode = false;
                    //Focus Mode enables slower move speed for precision
                }
                else{
                    FocusMode = true;
                }
            }
            if(FocusMode){
                Move_Power = 0.5;
            }
            else{
                Move_Power = 1;
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
