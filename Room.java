
public class Room
{
    public String name;
    public String description;
    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;

    public Room(String name, String description)
    {
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
