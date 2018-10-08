package guiframework.elements.displayables;

import guiframework.common.TypedFunction;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StringDisplayable extends AbstractDisplayable
{
    private static final int LINE_BUFFER = 4, END_BUFFER = 2;

    private final FontMetrics fontMetrics;
    private final Color color;
    private final TypedFunction<String> getString;

    public StringDisplayable(int x, int y, Color color, TypedFunction<String> getString)
    {
        super(x, y);
        this.color = color;
        this.getString = getString;
        this.fontMetrics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics().getFontMetrics();
    }

    @Override
    protected void do_display(Graphics2D g2d)
    {
        g2d.setColor(color);
        int y = fontMetrics.getHeight() + END_BUFFER;
        int x = END_BUFFER;
        for(String line : getString.execute().split("\n"))
        {
            g2d.drawString(line, x, y);
            y += fontMetrics.getHeight() + LINE_BUFFER;
        }
    }

    @Override
    public void update() {}

    @Override
    public int getWidth()
    {
        int maxWidth = 0;
        for(String line : getString.execute().split("\n"))
        {
            int lineWidth = fontMetrics.stringWidth(line);
            if(lineWidth > maxWidth) maxWidth = lineWidth;
        }
        return maxWidth + END_BUFFER;
    }

    @Override
    public int getHeight()
    {
        return getString.execute().split("\n").length * fontMetrics.getHeight() + END_BUFFER;
    }
}
