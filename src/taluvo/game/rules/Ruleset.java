package taluvo.game.rules;

import taluvo.game.Game;
import taluvo.game.hex.Building;
import taluvo.game.rules.buildingplacement.BuildingPlacementRule;
import taluvo.game.rules.tileplacement.TilePlacementRule;

import java.util.HashSet;
import java.util.Set;

public class Ruleset
{
    private final Set<TilePlacementRule> tilePlacementRules;
    private final Set<BuildingPlacementRule> buildingPlacementRules;

    public Ruleset()
    {
        this.tilePlacementRules = new HashSet<>();
        this.buildingPlacementRules = new HashSet<>();
    }

    public void add(TilePlacementRule tilePlacementRule) { tilePlacementRules.add(tilePlacementRule); }
    public void remove(TilePlacementRule tilePlacementRule) { tilePlacementRules.remove(tilePlacementRule); }
    public void add(BuildingPlacementRule buildingPlacementRule) { buildingPlacementRules.add(buildingPlacementRule); }
    public void remove(BuildingPlacementRule buildingPlacementRule) { buildingPlacementRules.remove(buildingPlacementRule); }

    public boolean buildingPlacementIsLegal(int x, int y, Building building, Game.View game)
    {
        for(BuildingPlacementRule rule : buildingPlacementRules)
        {
            if(rule.broken(x, y, building, game))
            {
                return false;
            }
        }

        return true;
    }

    public boolean tilePlacementIsLegal(int x, int y, Game.View game)
    {
        for(TilePlacementRule rule : tilePlacementRules)
        {
            if(rule.broken(x, y, game))
            {
                return false;
            }
        }

        return true;
    }
}
