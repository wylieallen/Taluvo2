package taluvo.game.rules.buildingplacement;

import taluvo.game.Game;
import taluvo.game.hex.Building;

public class VillagerHexHasHeightOne implements BuildingPlacementRule
{
    @Override
    public boolean broken(int x, int y, Building building, Game.View game)
    {
        return building == Building.VILLAGE && game.getBoard().getHeight(x, y) != 1;
    }
}
