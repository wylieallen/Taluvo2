package taluvo.game.listeners;

import taluvo.game.hex.Building;
import taluvo.game.Player;

public interface BuildingPlacementListener
{
    void buildingPlaced(int x, int y, Building building, Player player);
}
