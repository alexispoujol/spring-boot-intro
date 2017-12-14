
package fr.emse.majeureinfo.springbootintro.model;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.util.List;

@Entity

public class Building {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;


    /**
     * The Rooms of a building
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;


    @SuppressWarnings("unused")
    public Building() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  List<Room>  getRooms() { return rooms; }

    public void setRooms( List<Room>  rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

