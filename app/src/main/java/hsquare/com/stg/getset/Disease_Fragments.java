package hsquare.com.stg.getset;

import java.util.ArrayList;

/**
 * Created by harpreetsingh on 29/12/16.
 */

public class Disease_Fragments {

    public String getFragment_id() {
        return fragment_id;
    }

    public void setFragment_id(String fragment_id) {
        this.fragment_id = fragment_id;
    }

    static String fragment_id=null;
    public static ArrayList<String> arList=new ArrayList<>();
    public static int cnt=0;
}
