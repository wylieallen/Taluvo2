package taluvo.game.rules.buildingplacement;

import taluvo.game.Game;
import taluvo.game.hex.Building;

public class HexIsBuildable implements BuildingPlacementRule
{
    @Override
    public boolean broken(int x, int y, Building building, Game.View game)
    {
        return !game.getBoard().isBuildable(x, y);
    }
}
