package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.model.Building;
import fr.emse.majeureinfo.springbootintro.model.Room;

import java.util.ArrayList;
import java.util.List;

public class BuildingDto {
    private final Long id;
    private final String name;
    private final List<RoomDto> rooms = new ArrayList<RoomDto>();



    public BuildingDto(Building building, List<Room> rooms) {
        this.id = building.getId();
        this.name = building.getName();
        for(int i = 0 ; i < rooms.size() ; i++) {
            this.rooms.add(new RoomDto(rooms.get(i)));
        }
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<RoomDto> getRooms() { return rooms; }

}