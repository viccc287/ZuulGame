package main;

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
}
