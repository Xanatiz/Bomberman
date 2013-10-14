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

    public void add(Bomb bomb){
        myList.add(bomb);
    }

    public void remove(Position position){
        for (int i = 0; i <= myList.size()-1; i++) {
            if(myList.get(i).getPosition().equals(position)){
                myList.remove(i);
                break;
            }
        }
    }

    public void activateBomb(Position position){
        for (int i = 0; i <= myList.size()-1; i++) {
            if(myList.get(i).getPosition().equals(position)){
                myList.get(i).activate();
                break;
            }
        }
    }
    public void explodeBomb(Position position){
         for (int i = 0; i <= myList.size()-1; i++) {
             if(myList.get(i).getPosition().equals(position)){
                 myList.get(i).explode();
                 break;
             }
         }
     }


    public void kickBomb(Position position, int xDirection, int yDirection){
        for (int i = 0; i <= myList.size()-1; i++) {
            if(myList.get(i).getPosition().equals(position)){
                myList.get(i).kick(xDirection, yDirection);
                break;
            }
        }
    }
}