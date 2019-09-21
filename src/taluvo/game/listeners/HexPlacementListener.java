package taluvo.game.listeners;

import taluvo.game.hex.Terrain;

public interface HexPlacementListener
{
    void hexPlaced(int x, int y, int height, Terrain terrain, int tileId);
}
