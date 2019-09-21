package taluvo.game.rules.tileplacement;

import taluvo.game.Game;
import taluvo.game.hex.Terrain;

public class OriginTerrainIsVolcanoOrNull implements TilePlacementRule
{
    @Override
    public boolean broken(int x, int y, Game.View game)
    {
        Terrain terrain = game.getBoard().getTerrain(x, y);
        return terrain != Terrain.VOLCANO && terrain != Terrain.NONE;
    }
}
