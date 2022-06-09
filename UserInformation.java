import java.util.ArrayList;

public class UserInformation {

    ArrayList<String> database = new ArrayList<String>();
    private String NAMEUSER;

    public UserInformation(String name) {
        NAMEUSER = name;
        generateDataBase(NAMEUSER);
    }

    private void generateDataBase(String NAMEUSER) {
        database.add(NAMEUSER);
    }

    public ArrayList<String> getUserInformation() {
        return database;
    }
}
