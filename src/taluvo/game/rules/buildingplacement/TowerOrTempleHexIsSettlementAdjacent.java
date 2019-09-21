package taluvo.game.rules.buildingplacement;

import taluvo.game.Game;
import taluvo.game.hex.Building;
import taluvo.util.Point;

public class TowerOrTempleHexIsSettlementAdjacent implements BuildingPlacementRule
{
    @Override
    public boolean broken(int x, int y, Building building, Game.View game)
    {
        if(building == Building.TOWER || building == Building.TEMPLE)
        {
            for(Point neighbor : game.getBoard().getNeighbors(x, y))
            {
                if(game.getBoard().getOwner(neighbor.x, neighbor.y) == game.getActivePlayer())
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }
}
