package utils;

public enum Strings {
    STRING_Game_Start("Welcome to The Goose Game"+System.lineSeparator()+"Add one or more player to start, move your player to reach 63"),
    STRING_Game_Win(" Wins!!"),
    STRINGS_Game_Exit("exit"),
    STRINGS_Game_Invalid("Invalid throw; should be between 1 and 6"),
    STRING_Game_player_Exist("player exists"),
    STRINGS_Game_Players("players: "),
    STRINGS_Game_bridge(" the bridge!! . "),
    STRINGS_Game_Position(" position: "),
    STRINGS_Game_Goose(", the Goose "),
    STRING_Game_Help(System.lineSeparator()+"please write valid command->"+System.lineSeparator()+
            "-add player <playerName>          : add a new player to the game" +System.lineSeparator()+
            "-show players                     : show list of players"+System.lineSeparator()+
            "-move <playerName> <move1,move2>  : move player with specific moves"+System.lineSeparator()+
            "-move <playerName>                : automatically move player"),
    STRINGS_Player_Show("show players"),
    STRINGS_Player_Move("move "),
    STRINGS_Player_Add("add player "),
    STRINGS_Player_playerName("Player name: "),
    STRINGS_Action_moveFrom(" move from "),
    STRINGS_Action_To(" to "),
    STRINGS_Action_bounce(" bounces! "),
    STRINGS_Action_returns(" returns to "),
    STRINGS_Action_Jump(" jumps to "),
    STRINGS_Action_MoveAgain(" moves again to "),
    STRINGS_Action_there(" there is player "),
    STRINGS_Action_On(" on "),
    STRINGS_Action_WhoReturn(", who returns to "),

    ;



    private final String text;

    Strings(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
