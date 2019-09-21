package taluvo.game.rules.tileplacement;

import taluvo.game.Board;
import taluvo.game.Game;
import taluvo.game.hex.Hex;
import taluvo.game.hex.Tile;

public class HexesNotSameTile implements TilePlacementRule
{
    @Override
    public boolean broken(int x, int y, Game.View game)
    {
        Board.View board = game.getBoard();

        int tileId = board.getTileId(x, y);

        if(tileId == Hex.NULL.getTileId())
        {
            return false;
        }

        for(Tile.Entry hex : game.getNextTile())
        {
            if(board.getTileId(x + hex.x, y + hex.y) != tileId)
            {
                return false;
            }
        }

        return true;
    }
}
