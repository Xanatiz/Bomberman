import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: henke
 * Date: 10/1/13
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerList {

    private ArrayList<Player> myList;

    public PlayerList() {
        this.myList = new ArrayList<Player>();
    }

    public int size() {
        return myList.size();
    }

    public void clear() {
        myList.clear();
    }
    public boolean contains(Position position){
        for (int i = 0; i <= myList.size()-1; i++) {
            if(myList.get(i).getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    public void add(Player player){
        myList.add(player);
    }

    public void remove(Position position){
        for (int i = 0; i <= myList.size()-1; i++) {
            if(myList.get(i).getPosition().equals(position)){
                myList.remove(i);
                break;
            }
        }
    }

    //bygger på att players alltid ligger kvar i listan,
    //tas en spelare bort hänger inte id och position i listan ihop.
    public Player get(ID id){
        return myList.get(id.read()-1);
    }

    public Player get(Position position){
        for (int i = 0; i <= myList.size()-1; i++) {
            if(myList.get(i).getPosition().equals(position))
                return myList.get(i);

        }
        return null;
    }
}