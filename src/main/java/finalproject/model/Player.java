package finalproject.model;

public class Player {
    private String name;
    private int highScore;
    public Player(String name,int highScore){
        this.name=name;
        this.highScore=highScore;
    }

    public String getName(){
        return name;
    }
    public int getHighScore(){
        return highScore;
    }
    public void setHighScore(int highScore){
        this.highScore=highScore;
    }
}
