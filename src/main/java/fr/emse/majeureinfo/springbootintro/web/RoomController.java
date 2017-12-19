package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.dao.RoomDao;
import fr.emse.majeureinfo.springbootintro.model.Room;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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


        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/on")
    public List<RoomDto> listWithOnLight() {
        return roomDao.findRoomWithOnLight().stream().map(RoomDto::new).collect(Collectors.toList());
    }

}