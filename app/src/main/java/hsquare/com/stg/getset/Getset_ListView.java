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



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }



    public Getset_ListView(String  id, String name) {
        Id = id;
        Name = name;
    }

    String Name,Id;


    public String toString() {
     return Name;
    }
}
