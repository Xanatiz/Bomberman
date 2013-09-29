import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: cake Date: 2013-09-25 Time: 13:17 To change this template use File | Settings | File
 * Templates.
 */
public class Playfield {
    //Fields
    private BlockType[][] fieldArray;
    private int row;
    private int column;
    private List<ListenerHandler> myListener = new ArrayList<ListenerHandler>();




    //Constructor
    public Playfield(int row, int column){
        this.row = row;
        this.column = column;
        this.fieldArray = new BlockType[row][column];
    }


    //Methods
    public void fillFieldArray(){
        Random random = new Random();
        for(int i=0; i < this.row; i++){
            for(int j=0; j < this.column; j++){
                if ((i==0)||(i==this.row-1)||(j==0)||(j==this.column-1)||((i%2==0)&&(j%2==0))){
                    this.fieldArray[i][j]=BlockType.WALL;}
                else if((i==1&&j<4)||(i<4&&j==1)||(i==this.row-2&&j>this.column-5)||(i>this.row-5&&j==this.column-2)){
                    this.fieldArray[i][j]=BlockType.SPAWNGROUND;}
                else if((random.nextInt(100)<79)){
                    this.fieldArray[i][j]=BlockType.BOX;
                }
                else{
                    this.fieldArray[i][j]=BlockType.GROUND;
                }
            }
        }
    }

    public BlockType getData(int row, int column){
        return this.fieldArray[row][column];
    }

    public BlockType getData(Position position){
        return this.fieldArray[position.getY()][position.getX()];
    }

    public void setData(int row, int column, BlockType data){
        this.fieldArray[row][column]=data;
        notifyListener();
    }

    public void setData(Position position, BlockType data){
        this.fieldArray[position.getY()][position.getX()]=data;
        notifyListener();
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void addListener(ListenerHandler lh){
        this.myListener.add(lh);
    }

    private void notifyListener(){
        for (int i = 0; i < myListener.size(); i++) {
            ListenerHandler tmp = myListener.get(i);
            tmp.updateBoard();
        }
    }


}
