package Chess;

import java.util.ArrayList;

public interface GameInterface
{
   // observer design pattern
   public void register(GameObserver observer);
   public void unregister(GameObserver observer);
   public void notifyObservers(ArrayList<int[]> pieceLocations);
}

