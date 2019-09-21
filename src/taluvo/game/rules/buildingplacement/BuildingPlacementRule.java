package taluvo.game.rules.buildingplacement;

import taluvo.game.Board;
import taluvo.game.Game;
import taluvo.game.hex.Building;

public interface BuildingPlacementRule
{
    boolean broken(int x, int y, Building building, Game.View game);
}
