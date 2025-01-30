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
    boolean FocusMode = false;
    int Move_Power = 1;
    @Overide
    public void runOpMode(){
        R_MOTOR = hardwareMap.get(DcMotor.class, "Right Motor");
        L_MOTOR = hardwareMap.get(DcMotor.class, "Left Motor");
        ULT_SENSOR = hardwareMap.get(UltrasonicSensor.class, "Ultrasonic Sensor");
        ARM_SERVO = hardwareMap.get(Servo.class, "Arm Servo");
        ARM_SERVO.setPosition(90);
        waitForStart();
        while(opModeIsActive()){
            double LStickX = gamepad1.left_stick_x;
            double RStickY = gamepad1.right_stick_y;
            if(gamepad1.a){
                if(FocusMode){
                    FocusMode = false;
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
            if(LStickX != 0){
                R_MOTOR.setPower(LStickX*Move_Power);
                L_MOTOR.setPower(LStickX*Move_Power);
            }
            else if(RStickY != 0){
                R_MOTOR.setPower(RStickY*Move_Power);
                L_MOTOR.setPower(-RStickY*Move_Power);
            }
            else{
                R_MOTOR.setPower(0);
                L_MOTOR.setPower(0);
            }
        }
    }
}
