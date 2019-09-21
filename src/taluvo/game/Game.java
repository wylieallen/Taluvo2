package taluvo.game;

import taluvo.game.hex.Building;
import taluvo.game.hex.Settlement;
import taluvo.game.hex.Tile;
import taluvo.game.hex.deck.Deck;
import taluvo.game.hex.deck.InfiniteDeck;
import taluvo.game.listeners.BuildingPlacementListener;
import taluvo.game.listeners.HexPlacementListener;
import taluvo.game.rules.RuleViolationException;
import taluvo.game.rules.Ruleset;
import taluvo.game.rules.buildingplacement.*;
import taluvo.game.rules.tileplacement.*;
import taluvo.util.Carousel;
import taluvo.util.Point;

import java.util.*;

public class Game
{
    private final View view;
    private final Ruleset ruleset;
    private final Board board;
    private final Deck deck;
    private final Carousel<Player> players;
    private final Map<Point, Settlement> settlements;
    private Player activePlayer;
    private int tileCount;
    private boolean tilePlaced;

    public Game(Player... players)
    {
        this.board = new Board();
        this.deck = new InfiniteDeck();
        this.view = new View();
        this.settlements = new HashMap<>();

        this.players = new Carousel<>(players);
        this.activePlayer = this.players.next();

        this.ruleset = new Ruleset();
        ruleset.add(new GameInTilePlacementPhase());
        ruleset.add(new HexesHaveSameHeight());
        ruleset.add(new HexesNotSameTile());
        ruleset.add(new OriginTerrainIsVolcanoOrNull());
        ruleset.add(new HexesAreDestructible());

        ruleset.add(new GameInBuildingPlacementPhase());
        ruleset.add(new HexIsBuildable());
        ruleset.add(new PlayerHasNonZeroBuildingCount());
        ruleset.add(new VillagerHexHasHeightOne());
        ruleset.add(new TowerHexHasHeightMinimumThree());
        ruleset.add(new TowerOrTempleHexIsSettlementAdjacent());

        this.tileCount = 0;
        this.tilePlaced = false;
    }

    private void recalculateSettlements()
    {
        settlements.clear();
        int settlementCount = 0;

        Board.View boardView = getBoardView();

        for(Point origin : boardView)
        {
            Player owner = boardView.getOwner(origin.x, origin.y);
            if(!settlements.containsKey(origin) && owner != Player.NULL)
            {
                Settlement settlement = new Settlement(settlementCount, owner);
                settlementCount++;

                Queue<Point> toVisit = new LinkedList<>();
                toVisit.add(origin);

                while(!toVisit.isEmpty())
                {
                    Point point = toVisit.remove();

                    settlement.add(point.x, point.y, boardView.getBuilding(point.x, point.y));
                    settlements.put(point, settlement);

                    for(Point neighbor : boardView.getNeighbors(point.x, point.y))
                    {
                        if(!settlements.containsKey(neighbor) && !toVisit.contains(neighbor) && boardView.getOwner(neighbor.x, neighbor.y) == owner)
                            toVisit.add(neighbor);
                    }
                }
            }
        }
    }

    public void placeTile(final int x, final int y) throws RuleViolationException
    {
        if(ruleset.tilePlacementIsLegal(x, y, view))
        {
            for(Tile.Entry hex : deck.pop())
            {
                board.placeTile(x + hex.x, y + hex.y, hex.terrain, tileCount);
            }
            ++tileCount;
            tilePlaced = true;
            recalculateSettlements();
        }
        else
        {
            throw new RuleViolationException("You cannot placed this tile at " + x + ", " + y + " !");
        }
    }

    private void placeEmptyHex(final int x, final int y)
    {
        board.placeHexPlaceholder(x, y);
    }

    public void placeBuilding(final int x, final int y, final Building building) throws RuleViolationException
    {
        if(ruleset.buildingPlacementIsLegal(x, y, building, view))
        {
            board.placeBuilding(x, y, building, activePlayer);
            activePlayer.placed(building);
            tilePlaced = false;
            activePlayer = players.next();
            recalculateSettlements();
        }
        else
        {
            throw new RuleViolationException("You cannot placed a " + building.name() + " at " + x + " , " + y + " !");
        }
    }

    public void start() { placeEmptyHex(0, 0); }

    public void subscribe(final HexPlacementListener listener) { board.subscribe(listener); }
    public void subscribe(final BuildingPlacementListener listener) { board.subscribe(listener); }

    public View getView() { return view; }
    public Board.View getBoardView() { return board.getView(); }
    public Deck getDeck() { return deck; }
    public boolean isTilePlaced() { return tilePlaced; }

    public class View
    {
        public Board.View getBoard() { return getBoardView(); }
        public Tile.Entry[] getNextTile() { return deck.peek(); }
        public Player getActivePlayer() { return activePlayer; }
        public int getTileCount() { return tileCount; }
        public boolean tilePlaced() { return tilePlaced; }
    }
}
