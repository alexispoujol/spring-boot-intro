package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.model.Building;
import java.util.List;

public class BuildingDto {

    private final Long id;
    private final List<RoomDto> rooms;

    public BuildingDto(Building building) {
        this.id = building.getId();
        this.rooms = (building.getRooms() == null) ? null : new List<RoomDto>(building.getRooms());

    }



    public Long getId() { return id; }
    public List<RoomDto> getRooms() { return rooms; }



}
