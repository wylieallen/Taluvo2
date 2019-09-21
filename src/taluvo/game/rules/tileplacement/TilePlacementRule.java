package taluvo.game.rules.tileplacement;

import taluvo.game.Game;

public interface TilePlacementRule
{
    boolean broken(int x, int y, Game.View game);
}
