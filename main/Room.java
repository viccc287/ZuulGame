package main;

import java.util.Objects;

public class Room
{
    private String name; //No borrar, el IDE lo sugiere porque no detecta que se usa en la reflection de RoomInstantiator
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;

    public Room(String name, String description)
    {
        initializeRoom(name, description);
    }

    public void setExits(Room northExit, Room eastExit, Room southExit, Room westExit)
    {
        if(northExit != null)
            this.northExit = northExit;
        if(eastExit != null)
            this.eastExit = eastExit;
        if(southExit != null)
            this.southExit = southExit;
        if(westExit != null)
            this.westExit = westExit;
    }

    private void initializeRoom(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public Room getNorthExit() {
        return northExit;
    }

    public Room getSouthExit() {
        return southExit;
    }

    public Room getEastExit() {
        return eastExit;
    }

    public Room getWestExit() {
        return westExit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name) && Objects.equals(description, room.description) && Objects.equals(northExit, room.northExit) && Objects.equals(southExit, room.southExit) && Objects.equals(eastExit, room.eastExit) && Objects.equals(westExit, room.westExit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, northExit, southExit, eastExit, westExit);
    }
}
