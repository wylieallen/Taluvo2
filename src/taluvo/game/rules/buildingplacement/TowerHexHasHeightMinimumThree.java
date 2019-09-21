package taluvo.game.rules.buildingplacement;

import taluvo.game.Game;
import taluvo.game.hex.Building;

public class TowerHexHasHeightMinimumThree implements BuildingPlacementRule
{
    @Override
    public boolean broken(int x, int y, Building building, Game.View game)
    {
        if(building == Building.TOWER && game.getBoard().getHeight(x, y) < 3)
        {
            return true;
        }

        return false;
    }
}
