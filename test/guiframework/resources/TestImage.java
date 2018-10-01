package guiframework.resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TestImage extends BufferedImage
{
    public TestImage(Dimension size)
    {
        this(size.width, size.height);
    }

    public TestImage(int width, int height)
    {
        super(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public boolean equals(Object other)
    {
        if(!(other instanceof TestImage))
        {
            return false;
        }

        BufferedImage image2 = (BufferedImage) other;

        int height1 = this.getHeight(), height2 = image2.getHeight();
        int width1 = this.getWidth(), width2 = image2.getWidth();

        if(height1 != height2 || width1 != width2) return false;

        for(int i = 0; i < width1; i++)
        {
            for(int j = 0; j < height1; j++)
            {
                if(this.getRGB(i, j) != image2.getRGB(i, j))
                    return false;
            }
        }

        return true;
    }
}
