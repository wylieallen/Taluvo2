package taluvo.gui.factories.displayablefactories;

import guiframework.elements.displayables.CompositeDisplayable;
import guiframework.elements.displayables.Displayable;
import guiframework.elements.displayables.primitives.ImageDisplayable;
import guiframework.elements.displayables.primitives.StringDisplayable;
import taluvo.game.Player;
import taluvo.game.hex.Building;
import taluvo.game.hex.Terrain;
import taluvo.gui.factories.imagefactories.ImageFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ClassicDisplayableFactory implements DisplayableFactory
{
    private final static Map<Terrain, Color> terrainColors = new HashMap<>();
    {
        terrainColors.put(Terrain.NONE, Color.WHITE);
        terrainColors.put(Terrain.VOLCANO, Color.RED);
        terrainColors.put(Terrain.GRASSY, new Color(0x7f, 0xff, 0x00, 0xFF));
        terrainColors.put(Terrain.JUNGLE, Color.GREEN);
        terrainColors.put(Terrain.ROCKY, Color.LIGHT_GRAY);
        terrainColors.put(Terrain.LAKE, Color.CYAN);
    }

    private final ImageFactory imageFactory;

    private final Map<Color, Map<Color, Map<Building, Displayable>>> buildingDisplayables = new HashMap<>();

    public ClassicDisplayableFactory(ImageFactory imageFactory)
    {
        this.imageFactory = imageFactory;
    }

    @Override
    public Displayable[] makeRectButton(int width, int height, Color defaultColor, Color hoverColor, Color pressColor, Color textColor, String text)
    {
        Displayable[] displayables = new Displayable[3];
        displayables[0] = makeLabeledBox(0, 0, width, height, defaultColor, textColor, text);
        displayables[1] = makeLabeledBox(0, 0, width, height, hoverColor, textColor, text);
        displayables[2] = makeLabeledBox(0, 0, width, height, pressColor, textColor, text);
        return displayables;
    }

    public Displayable[] makeMenuButton()
    {
        BufferedImage[] images = imageFactory.makeMenuButton();

        Displayable[] displayables = new Displayable[3];

        for(int i = 0; i < 3; i++)
        {
            displayables[i] = new ImageDisplayable(images[i], 0, 0);
        }

        return displayables;
    }

    public Displayable makeHex(int x, int y, Color bodyColor, Color borderColor)
    {
        return new ImageDisplayable(imageFactory.makeBorderedHex(bodyColor, borderColor), x, y);
    }

    public ImageDisplayable[] makeHexButton(Terrain terrain, Color press, Color hover, Color border)
    {
        BufferedImage[] images = imageFactory.makeHexButton(terrainColors.get(terrain), press, hover, border);
        ImageDisplayable[] displayables = new ImageDisplayable[3];
        displayables[0] = new ImageDisplayable(images[0], 0, 0);
        displayables[1] = new ImageDisplayable(images[1], 0, 0);
        displayables[2] = new ImageDisplayable(images[2], 0, 0);
        return displayables;
    }

    public Displayable makeLabeledBox(int x, int y, int width, int height, Color bodyColor, Color textColor, String text)
    {
        BufferedImage box = imageFactory.makeBorderedRect(width, height, bodyColor);
        Graphics2D g2d = box.createGraphics();
        Displayable textDisplayable = new StringDisplayable(0, 0, textColor, () -> text);
        textDisplayable.setX((width / 2) - (textDisplayable.getWidth() / 2) - 2);
        textDisplayable.setY((height / 2) - (textDisplayable.getHeight() / 2) - 4);
        textDisplayable.display(g2d);
        return new ImageDisplayable(box, x, y);
    }

    @Override
    public Displayable makeBox(int x, int y, int width, int height, Color color)
    {
        return new ImageDisplayable(imageFactory.makeBorderedRect(width, height, color), x, y);
    }

    public Shape getHexShape() { return imageFactory.getHexShape(); }

    public static Color getTerrainColor(Terrain terrain)
    {
        return terrainColors.getOrDefault(terrain, new Color(0x00, 0x00, 0x00, 0x00));
    }

    @Override
    public Displayable makeBuildingStatusDisplayable(Player player, Building building, Color primary, Color secondary)
    {
        CompositeDisplayable base = new CompositeDisplayable(0, 0);
        base.add(new ImageDisplayable(new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB), 0, 0));
        Displayable buildingDisplayable = makeBuildingDisplayable(building, primary, secondary);
        buildingDisplayable.setY(4);
        base.add(buildingDisplayable);
        base.add(new StringDisplayable(16, 16, Color.BLACK, () -> "" + player.getBuildingCount(building)));
        return base;
    }

    public Displayable makeBuildingDisplayable(Building building, Color primary, Color secondary)
    {
        if(building == Building.NONE)
            return new ImageDisplayable(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), 0, 0);
        BufferedImage image = new BufferedImage(21, 21, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(primary);
        g2d.fillOval(0, 0, 20, 20);
        g2d.setColor(Color.GRAY);
        g2d.drawOval(0, 0, 20, 20);
        g2d.setColor(secondary);
        FontMetrics fm = g2d.getFontMetrics();
        int stringX = 10 - fm.stringWidth(building.name().substring(0, 2)) / 2;
        int stringY = 10 + fm.getHeight() / 2 - 3;
        g2d.drawString(building.name().substring(0, 2), stringX, stringY); // used to be (3, 15)
        return new ImageDisplayable(image, 0, 0);
    }

    @Override
    public Displayable getBuildingDisplayable(Building building, Color primary, Color secondary)
    {
        if(!buildingDisplayables.containsKey(primary))
        {
            buildingDisplayables.put(primary, new HashMap<>());
        }

        Map<Color, Map<Building, Displayable>> secondaryMap = buildingDisplayables.get(primary);

        if(!secondaryMap.containsKey(secondary))
        {
            secondaryMap.put(secondary, new HashMap<>());
        }

        Map<Building, Displayable> displayableMap = secondaryMap.get(secondary);

        if(!displayableMap.containsKey(building))
        {
            displayableMap.put(building, makeBuildingDisplayable(building, primary, secondary));
        }

        return displayableMap.get(building);
    }
}
