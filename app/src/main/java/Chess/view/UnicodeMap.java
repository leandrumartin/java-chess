package Chess.view;

import java.util.Random;

public class UnicodeMap 
{
    public static String wPawn = "\u2659";
    public static String bPawn = "\u265F";

    public static String wRook = "\u2656";
    public static String bRook = "\u265C";

    public static String wKnight = "\u2658";
    public static String bKnight = "\u265E";

    public static String wBishop = "\u2657";
    public static String bBishop = "\u265D";

    public static String wQueen = "\u2655";
    public static String bQueen = "\u265B";

    public static String wKing = "\u2654";
    public static String bKing = "\u265A";

    public static String upArrow = "\u2191";
    public static String downArrow = "\u2193";

    private static String[] pieceArray = {wPawn, bPawn, wRook, bRook, wKnight, bKnight, wBishop, bBishop, wQueen, bQueen, wKing, bKing};
    private static Random random = new Random();
    public static String getRandomPiece()
    {
        int piece = random.nextInt(pieceArray.length);
        return pieceArray[piece];
    }
}