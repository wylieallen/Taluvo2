package taluvo.game.rules.tileplacement;

import taluvo.game.Board;
import taluvo.game.Game;
import taluvo.game.hex.Tile;

public class HexesHaveSameHeight implements TilePlacementRule
{
    @Override
    public boolean broken(int x, int y, Game.View game)
    {
        Board.View board = game.getBoard();
        int height = board.getHeight(x, y);

        for(Tile.Entry hex : game.getNextTile())
        {
            if(board.getHeight(x + hex.x, y + hex.y) != height)
            {
                return true;
            }
        }

        return false;
    }
}
