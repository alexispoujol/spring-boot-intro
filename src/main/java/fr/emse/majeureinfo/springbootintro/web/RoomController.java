package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.dao.RoomDao;
import fr.emse.majeureinfo.springbootintro.model.Room;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

@RestController
@RequestMapping(value = "/api/rooms")
@Transactional
public class RoomController {

    private final RoomDao roomDao;


    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<RoomDto> list() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }


    @GetMapping(value = "/{roomId}")
    public RoomDto get(@PathVariable Long roomId) {
        Room room = roomDao.getOne(roomId);
        return new RoomDto(room);
    }



    @PostMapping(value = "/{roomId}/switch/light", consumes = "application/json")
    public RoomDto switchLight(@PathVariable Long roomId){
        Room room = roomDao.getOne(roomId);
        room.getLight().switchStatus();
        return new RoomDto(room);
    }

    @PostMapping(value = "/{roomId}/switch/ringer", consumes = "application/json")
    public RoomDto switchRinger(@PathVariable Long roomId){
        Room room = roomDao.getOne(roomId);
        room.getNoise().switchStatus();
        return new RoomDto(room);
    }


    @PostMapping(value = "/{roomId}/switch/ringer/list", consumes = "application/json")
    public List<RoomDto> switchRingerList(@PathVariable Long roomId){
        Room room = roomDao.getOne(roomId);
        room.getNoise().switchStatus();
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/{roomId}/switch/light/list", consumes = "application/json")
    public List<RoomDto> switchLightList(@PathVariable Long roomId){
        Room room = roomDao.getOne(roomId);
        room.getLight().switchStatus();


        String topic        = "sensor/1";
        String content      = "Message : light switch";
        int qos             = 2;
        String broker       = "tcp://m14.cloudmqtt.com:16767";
        String clientId     = "sfkfkf";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName("aaa");
            connOpts.setPassword("aaa".toCharArray());
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        }
        catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }

        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/on")
    public List<RoomDto> listWithOnLight() {
        return roomDao.findRoomWithOnLight().stream().map(RoomDto::new).collect(Collectors.toList());
    }

}