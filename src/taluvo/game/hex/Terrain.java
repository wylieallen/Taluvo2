package taluvo.game.hex;

public enum Terrain
{
    NONE(false), VOLCANO(false), GRASSY(true), LAKE(true), JUNGLE(true), ROCKY(true);

    Terrain(boolean buildable)
    {
        this.buildable = buildable;
    }

    private final boolean buildable;

    public boolean isBuildable() { return buildable; }
}
