//package fr.emse.majeureinfo.springbootintro.web;
//
//import fr.emse.majeureinfo.springbootintro.dao.RoomDao;
//import fr.emse.majeureinfo.springbootintro.model.Room;
//import org.springframework.web.bind.annotation.*;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@RestController
//@RequestMapping(value = "/api/rooms")
//@Transactional
//public class RoomController2 {
//    private final RoomDao roomDao;
//    public RoomController2(RoomDao roomDao) {
//        this.roomDao = roomDao;
//    }
//
//
//    @GetMapping
//    public List<RoomDto> list() {
//        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
//    }
//
//
//    @GetMapping(value = "/roomId/{roomId}")
//    public RoomDto get(@PathVariable Long roomId) {
//        Room room = roomDao.getOne(roomId);
//        return new RoomDto(room);
//    }
//
//
//    @PostMapping(value = "/roomId/{roomId}/switch/ringer", consumes = "application/json")
//    public List<RoomDto> switchRingerList(@PathVariable Long roomId){
//        Room room = roomDao.getOne(roomId);
//        room.getNoise().switchStatus();
//        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
//    }
//
//    @PostMapping(value = "/roomId/{roomId}/switch/light", consumes = "application/json")
//    public List<RoomDto> switchLightList(@PathVariable Long roomId){
//        Room room = roomDao.getOne(roomId);
//        room.getLight().switchStatus();
//        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
//    }
//
//    @GetMapping(value = "/list/with/on/light")
//    public List<RoomDto> listWithOnLight() {
//        return roomDao.findRoomWithOnLight().stream().map(RoomDto::new).collect(Collectors.toList());
//    }
//    @GetMapping(value = "/list/with/on/Noise")
//    public List<RoomDto> listWithOnNoise() {
//        return roomDao.findRoomWithOnLight().stream().map(RoomDto::new).collect(Collectors.toList());
//    }
//
//    @PostMapping(value = "/switch/all/light/off", consumes = "application/json")
//    public List<RoomDto> switchAllLightOff() {
//        roomDao.findRoomWithOnLight() =
//        for(int i = 0 ; i < rooms.size() ; i++) {
//            this.rooms.add(new RoomDto(rooms.get(i)));
//        }
//    }
//
//
//}