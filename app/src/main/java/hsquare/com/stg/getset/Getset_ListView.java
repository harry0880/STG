package hsquare.com.stg.getset;

/**
 * Created by harpreetsingh on 06/01/17.
 */

public class Getset_ListView {

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    int Id;

    public Getset_ListView(int id, String name) {
        Id = id;
        Name = name;
    }

    String Name;
}
