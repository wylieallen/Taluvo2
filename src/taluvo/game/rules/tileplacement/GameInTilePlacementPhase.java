package taluvo.game.rules.tileplacement;

import taluvo.game.Game;

public class GameInTilePlacementPhase implements TilePlacementRule
{
    @Override
    public boolean broken(int x, int y, Game.View game)
    {
        return game.tilePlaced();
    }
}
