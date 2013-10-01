import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: henke
 * Date: 10/1/13
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class BombList {

    private ArrayList<Bomb> myList;

    public BombList() {
        this.myList = new ArrayList<Bomb>();
    }

    public int size() {
        return myList.size();
    }

    public boolean isEmpty() {
        return myList.isEmpty();
    }

    public void clear() {
        myList.clear();
    }

    public void add(Bomb bomb){
        myList.add(bomb);
    }

    public void remove(Position position){
        for (int i = 0; i < myList.size()-1; i++) {
            if(myList.get(i).getPosition().equals(position)){
                myList.remove(i);
                break;
            }
        }
    }

}