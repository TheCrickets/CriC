public class PostClass
{
    String name;
    int id;

    @Override
    public String toString()
    {
        return "PostClass{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
