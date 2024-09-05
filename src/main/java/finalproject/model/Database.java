package finalproject.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * A Singleton Database
 */
public class Database {
    private static Database instance;
/** 
 * example 
 * [ {"player":{"name":"player1","bestScore":"0"}},
 * {"player":{"name":"player2","bestScore":"1"}} ] 
 */
    private JSONArray playersList ;  

    private Database(){
        this.loadFile();
    }
    /** checks if existed the file and convert it as a JSONArray Object.
     * if it's not existing creates an empty JSONArray Object
     */
    private void loadFile(){

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("database.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            playersList = (JSONArray) obj;
            //System.out.println(playersList);
        } 
        catch (FileNotFoundException e)  { playersList= new JSONArray(); }
        catch (IOException e)            { e.printStackTrace(); }
        catch (ParseException e)         { e.printStackTrace(); }
    }

    public static Database getInstance(){
        if(instance!= null){
            return instance;
        }
        instance = new Database();
        return instance;
    }
    
    /** 
     * Check if the player has the same name as the givenName.
     * If they are equal ,return a new Player Object with the
     *  name and the highscore taken from the JSONObject.
     */
    private static Player searchArrayItemForPlayer(JSONObject player,String givenName){
        //Get player object within list
        JSONObject playerObject = (JSONObject) player.get("player");
        System.out.println(player.get("player")+"???");
        
        String playerName =(String) playerObject.get("name");
        if( playerName.equals(givenName) ){                            
            //Get player BEST SCORE
            int bestScore = Integer.parseInt((String)playerObject.get("bestScore"));  
            System.out.println(bestScore);
            return new Player(givenName, bestScore);
        }
        return null;
    }
    
    /**
     * update the player's high score ,if the player exist in database.
     * If not it add to the database the player's name with his highscore.
     * @param player Object with name and highscore
     */
    @SuppressWarnings("unchecked")
    private void updatePlayer(Player player){ //update or add player if not exist
        int i;
        boolean flag= false;
        for( i=0;i< playersList.size();i++){

            JSONObject playerItem=(JSONObject)playersList.get(i);
            JSONObject playerObj= (JSONObject)playerItem.get("player");
            int bestScore=Integer.parseInt((String)playerObj.get("bestScore"));

            if(playerObj.get("name").equals(player.getName())){ 
                if(bestScore < player.getHighScore()){
                    playerObj.remove("bestScore");
                    playerObj.put( "bestScore", String.valueOf( player.getHighScore() ) );    
                }
                flag=true;
                break;
            }       
        }
        if(!flag){
            JSONObject playerObj= new JSONObject();
            JSONObject playerDetails= new JSONObject();

            playerDetails.put("name", player.getName());
            playerDetails.put("bestScore", String.valueOf(player.getHighScore()));
            playerObj.put("player",playerDetails);
            playersList.add(playerObj);
        }
    }
    /**
     * Check if the name correspond to any player in the database and
     * return a player object with the details if it's found,or
     * Player object with the given name and 0 highscore.
     * @param name of the player to retrieve.
     */
    public Player retrievePlayer(String name) {
        Player player;
        //Iterate over employee array
        for(int i=0 ;i <playersList.size();i++){
            player=searchArrayItemForPlayer( (JSONObject) playersList.get(i) ,name) ;
            if(player!=null)    
                return player;
        }
        return new Player(name,0);
    }

    
    /**
     * update the database, and saves the new database in the database.json file.
     * @param player Object to be updated or added.
     */
    public void saveNewData(Player player){
        try (FileWriter file = new FileWriter("database.json")) {
            System.out.println(player.getHighScore());
            updatePlayer(player);
            file.write(playersList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
