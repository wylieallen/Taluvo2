package taluvo.game.rules.buildingplacement;

import taluvo.game.Game;
import taluvo.game.hex.Building;

public class PlayerHasNonZeroBuildingCount implements BuildingPlacementRule
{
    @Override
    public boolean broken(int x, int y, Building building, Game.View game)
    {
        return game.getActivePlayer().getBuildingCount(building) <= 0;
    }
}
