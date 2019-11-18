/*
java -Djava.library.path=/usr/lib/jni:/home/pi/opencv/opencv-3.4.5/build/lib -cp classes:lib/'*' companion.companion.RealMain
 */

package kosa.team3.drone.main;

import kosa.team3.drone.device.Electromagnet;
import kosa.team3.drone.network.NetworkConfig;
import syk.drone.device.Camera;
import syk.drone.device.FlightController;


public class RealMain {
    public static void main(String[] args) {
        NetworkConfig networkConfig = new NetworkConfig();

        FlightController flightController = new FlightController();
        
        //FC <-> RealMain
        //라즈베리파이의 rx,tx 포트 설정 = "/dev/ttyAMA0"
        flightController.mavlinkConnectRxTx("/dev/ttyAMA0");
        
        //RealMain <-> MQTT
        
        flightController.mqttConnect(
        		//원래라면 tcp://106.253.56.124:1883
        		//나중에 유지보수시 자바코드 안에 위 코드가 있으면 불편함.
        		//networkconfing.properties안에 "mqttBrokerConnstr"안에 기입
                networkConfig.mqttBrokerConnStr,
                networkConfig.droneTopic +"/fc/pub",
                networkConfig.droneTopic +"/fc/sub"
        );

        Camera camera0 = new Camera(); //�븯諛� 移대찓�씪
        camera0.cameraConnect(0, 320, 240, 270);
        camera0.mattConnect(
                networkConfig.mqttBrokerConnStr,
                networkConfig.droneTopic + "/cam1/pub",
                networkConfig.droneTopic + "/cam1/sub"
        );

        Camera camera1 = new Camera(); //�쟾諛� 移대찓�씪(USB 苑껊뒗 �닚�꽌��濡� No媛� 0,1 �닚�쑝濡� �맖)
        camera1.cameraConnect(1, 320, 240, 180);
        camera1.mattConnect(
                networkConfig.mqttBrokerConnStr,
                networkConfig.droneTopic +"/cam0/pub",
                networkConfig.droneTopic +"/cam0/sub"
        );

        Electromagnet electromagnet = new Electromagnet();
        electromagnet.mqttConnect(
                networkConfig.mqttBrokerConnStr,
                networkConfig.droneTopic +"/electromagnet/pub",
                networkConfig.droneTopic+"/electromagnet/sub"
        );
    }
}
