package service;

import model.Player;
import utils.ConsoleHelper;
import utils.Strings;

import java.util.*;

public class GooseGame {

    private final List<Player> playerList = new LinkedList<>();

    /**
     * game start
     * init console and handle user input
     */
    public void gameStart(){
        ConsoleHelper consoleHelper = new ConsoleHelper();

        String output = Strings.STRING_Game_Start.toString();
        String input = "";
        consoleHelper.write(output);
        while (! output.contains(Strings.STRING_Game_Win.toString()) && ! input.equals(Strings.STRINGS_Game_Exit.toString())){
            try {
                input = consoleHelper.readCommand();
                output = inputProcessor(input);
            }
            catch (Exception e){
                output = help();
            }
            consoleHelper.write(output);
        }


    }

    /**
     *
     * @param input of user on console
     * @return string which is the output msg to the user
     */
    public String inputProcessor(String input){
        String result;
        if(input.startsWith(Strings.STRINGS_Player_Add.toString())){
            //add new players
            String playerName = input.replace(Strings.STRINGS_Player_Add.toString(), "");
            result = addNewPlayer(playerName);
        }
        else if(input.startsWith(Strings.STRINGS_Player_Move.toString())){
            // move players
            String playerName = input.replace(Strings.STRINGS_Player_Move.toString(), "");
            result = move(playerName);
        }
        else if(input.startsWith(Strings.STRINGS_Player_Show.toString())){
            //show players list
            result = showPlayers();
        }
        else {
            //show help menu
            result = help();
        }
        return result;
    }

    private String addNewPlayer(String playerName){
        if(playerList.stream().anyMatch(player -> Objects.equals(player.getName(),playerName))){
            // player name exists
            return Strings.STRING_Game_player_Exist+System.lineSeparator()+getNames();
        }

        // add new player
        playerList.add(new Player(playerName,0));
        return getNames();
    }

    /**
     * helping method that get names of all players
     * @return string of all players names
     */
    private String getNames(){
        StringBuilder result = new StringBuilder(Strings.STRINGS_Game_Players.toString());
        for (Player player:playerList){
            result.append(player.getName()).append(", ");

        }
        result.delete(result.length()-2,result.length());
        return result.toString();
    }

    /**
     * method that handle where should player moves based on game rules
     * @param playerName  input msg of user which contains player name and moves
     * @return string as user show
     */
    private String move(String playerName){
        String result = "";
        for(Player player:playerList){
            String name = player.getName();
            if(playerName.contains(name)){
                //getting the moves that he wants
                String[] moves = playerName.replace(name,"").trim().split(",");
                if(moves.length < 2){
                    // auto throw to generate random moves
                    moves = autoThrow();
                }
                result = "player rolls "+ moves[0] + ", " + moves[1] + " .";
                result += updatePosition(player,moves);
            }
        }
        return result;
    }

    /**
     * helping method which decide the special cases exists or not
     * @param player name
     * @param moves which need to be hold on
     * @return the decision
     */
    private String updatePosition(Player player,String[] moves){

        String name = player.getName();
        int position = player.getPosition();
        int move1 = Integer.parseInt(moves[0]);
        int move2 = Integer.parseInt(moves[1]);
        int expectedPosition = position + move1 + move2;
        String defaultMsg = name + Strings.STRINGS_Action_moveFrom + position + Strings.STRINGS_Action_To + expectedPosition;
        StringBuilder result = new StringBuilder(defaultMsg);
        //real throws check -> cannot be more than 6 or less than 1
        if(move1 < 1 || move2 < 1 || move1 > 6 || move2 > 6){
            return Strings.STRINGS_Game_Invalid.toString();
        }

        //special cases
        //bouncing or wining
        if(expectedPosition >= 63){
            result.append(name).append(Strings.STRINGS_Action_moveFrom).append(position).append(Strings.STRINGS_Action_To)
                    .append("63 ");
            if(expectedPosition > 63){
                int bounceValue = expectedPosition - 63;
                expectedPosition = 63 - bounceValue;
                result.append(name).append(Strings.STRINGS_Action_bounce).append(name).append(Strings.STRINGS_Action_returns)
                        .append(expectedPosition);
            }
            else {
                result.append(name).append(Strings.STRING_Game_Win);
            }
        }
        else if(expectedPosition == 6){
            //the bridge
            result = new StringBuilder(defaultMsg);
            expectedPosition = 12;
            result.append(Strings.STRINGS_Game_bridge).append(name).append(Strings.STRINGS_Action_Jump).append(expectedPosition);

        }
        else if(isGoose(expectedPosition)){
            //step on the Goose
            result = new StringBuilder(defaultMsg);
            //possibility of recursion
            while (isGoose(expectedPosition)){
                expectedPosition += move1 + move2;
                result.append(Strings.STRINGS_Game_Goose).append(name).append(Strings.STRINGS_Action_MoveAgain)
            .append(expectedPosition);
            }
        }
        // check the prank
        for( Player player2 : playerList){
            if( player2 != player && player2.getPosition() == expectedPosition){
                //the expected cell is occupied
                player2.setPosition(position);
                result.append(Strings.STRINGS_Action_On).append(expectedPosition).append(Strings.STRINGS_Action_there)
                        .append(player2.getName()).append(Strings.STRINGS_Action_WhoReturn).append(position);
            }
        }
        player.setPosition(expectedPosition);
        return result.toString();
    }

    /**
     * check if goose cell
     * @param position int
     * @return boolean of goose or not
     */
    private boolean isGoose(int position){
        List<Integer> goosePositions = new ArrayList<>(Arrays.asList(5,9,14,18,23,27));
        return goosePositions.contains(position);
    }

    /**
     * automatic random moves
     * @return array of suggested moves
     */
    private String[] autoThrow(){
        String[] moves = {
                ""+(new Random().nextInt(5) +1),
                ""+(new Random().nextInt(5) +1)
        };
        return moves;
    }

    /**
     *
     * print all players wrt positions
     * @return string of player names and positions
     */
    private String showPlayers(){
        StringBuilder outputMessage = new StringBuilder();
        for (Player player : playerList ){
            outputMessage.append(Strings.STRINGS_Player_playerName).append(player.getName()).append(Strings.STRINGS_Game_Position)
                    .append(player.getPosition()).append(
                    System.lineSeparator());

        }
        return outputMessage.toString();
    }

    /**
     * print the command menu
     * @return command menu
     */
    private String help(){
        return Strings.STRING_Game_Help.toString();
    }
}
