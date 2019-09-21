package taluvo.game.rules.tileplacement;

import taluvo.game.Board;
import taluvo.game.Game;
import taluvo.game.hex.Tile;

public class HexesAreDestructible implements TilePlacementRule
{
    @Override
    public boolean broken(int x, int y, Game.View game)
    {
        Board.View board = game.getBoard();

        for(Tile.Entry hex : game.getNextTile())
        {
            if(!board.isDestructible(x + hex.x, y + hex.y))
            {
                return true;
            }
        }

        return false;
    }
}
