import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: cake Date: 2013-09-25
 * Time: 13:17
 * To change this template use File | Settings | File Templates.
 */
public class Playfield {

    private BlockType[][] fieldArray;
    private int row;
    private int column;
    private List<ListenerHandler> myListener = new ArrayList<ListenerHandler>();

    public Playfield(int row, int column){
        this.row = row;
        this.column = column;
        this.fieldArray = new BlockType[row][column];
    }

    public void fillBackground(){
        Random random = new Random();
        for(int i=0; i < this.row; i++){
            for(int j=0; j < this.column; j++){
                if ((i==0)||(i==this.row-1)||(j==0)||(j==this.column-1)||((i%2==0)&&(j%2==0))){
                    this.fieldArray[i][j]=BlockType.WALL;}
                else if((i==1&&j<4)||(i<4&&j==1)||(i==this.row-2&&j>this.column-5)||(i>this.row-5&&j==this.column-2)){
                    this.fieldArray[i][j]=BlockType.SPAWNGROUND;}
                else if((random.nextInt(100)<=59)){
                    this.fieldArray[i][j]=BlockType.BOX;
                }
                else{
                    this.fieldArray[i][j]=BlockType.GROUND;
                }
            }
        }
    }

    public void replaceBox(Position position){
        Random random = new Random();
        int ifPowerUp = random.nextInt(100);
        int whichBox = random.nextInt(100);

        if(fieldArray[position.getY()][position.getX()]==BlockType.BOX){
            if(whichBox<=32&&ifPowerUp<=32){
                fieldArray[position.getY()][position.getX()]=BlockType.EXPLOSIONRADIUSBOOST;
            }else if(32<whichBox&&whichBox<=65&&ifPowerUp<=32){
                fieldArray[position.getY()][position.getX()]=BlockType.BOMBBOOST;
            }else if(65<whichBox&&ifPowerUp<=32){
                fieldArray[position.getY()][position.getX()]=BlockType.KICKBOMB;
            }else{
                fieldArray[position.getY()][position.getX()]=BlockType.GROUND;
            }
        }else{
            fieldArray[position.getY()][position.getX()]=BlockType.GROUND;
        }
    }

    public BlockType getData(Position position){
        return fieldArray[position.getY()][position.getX()];
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

    public void updatePlayfield(){
        notifyListener();
    }

    public void addListener(ListenerHandler lh){
        this.myListener.add(lh);
    }

    private void notifyListener(){
       for (ListenerHandler tmp : myListener) {
            tmp.updateBoard();
        }
    }

    public boolean legal(Position position){
        return (position.getX() >= 0 && position.getX() <= column && position.getY() >= 0 && position.getY() <= row);
    }
}