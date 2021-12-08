
public class Room
{
    private final String name;
    private final String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;

    public Room(String name, String description)
    {
        this.name = name;
        this.description = description;
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

    public String getDescription()
    {
        return description;
    }

    public String getName() {
        return name;
    }
}
