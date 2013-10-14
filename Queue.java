import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: henke
 * Date: 9/5/13
 * Time: 10:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class Queue {

    private ArrayList<Position> myList;

    public Queue() {
        this.myList = new ArrayList<Position>();
    }

    public int size() {
        return myList.size();
    }

    public boolean isEmpty() {
        return myList.isEmpty();
    }

    public boolean contains(Position position){
           for (int i = 0; i <= myList.size()-1; i++) {
               if(myList.get(i).equals(position)){
                   return true;
               }
           }
           return false;
       }

    /*public boolean contains(Position position) {
        return myList.contains(position);
    }*/

    public void clear() {
        myList.clear();
    }

    public void push(Position position){
        myList.add(position);
    }

    public Object pop(){
        return myList.remove(0);
    }


}
