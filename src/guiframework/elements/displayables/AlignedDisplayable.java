package guiframework.elements.displayables;

import guiframework.common.Locatable;

import java.awt.*;
import java.util.LinkedHashSet;

public class AlignedDisplayable extends AbstractDisplayable
{
    private final LinkedHashSet<Displayable> displayables;
    private final Orientation orientation;
    private final Alignment alignment;
    private int span, breadth;
    private final int endBuffer;

    public AlignedDisplayable(int x, int y, int span, int endBuffer, Orientation orientation, Alignment alignment)
    {
        super(x, y);
        this.span = span;
        this.breadth = 0;
        this.endBuffer = endBuffer;
        this.orientation = orientation;
        this.alignment = alignment;
        this.displayables = new LinkedHashSet<>();
    }

    public int getSpan() { return span; }
    public void setSpan(int span) { this.span = span; resize(); }

    public int getBreadth() { return breadth; }

    public int getWidth() { return orientation.getWidth(this); }
    public int getHeight() { return orientation.getHeight(this); }

    public void do_display(Graphics2D g2d)
    {
        displayables.forEach(d -> d.display(g2d));
    }

    public void update() { displayables.forEach(Displayable::update); }

    public void add(Displayable displayable)
    {
        displayables.add(displayable);

        int breadth = orientation.getBreadth(displayable);
        if(breadth > this.breadth)
            this.breadth = breadth;

        resize();
    }

    public void resize()
    {
        if(displayables.isEmpty()) return;

        int remainingSpace = this.span;

        remainingSpace -= (endBuffer * 2);

        for(Displayable d : displayables)
        {
            remainingSpace -= orientation.getSpan(d);
        }

        int componentBuffer = displayables.size() >= 2 ? remainingSpace / (displayables.size() - 1) : 0;

        final int breadthPosBase = orientation.getBreadth.get(this) / 2;
        int spanPos = endBuffer;
        for(Displayable displayable : displayables)
        {
            orientation.setSpan(displayable, spanPos);
            orientation.setBreadth(displayable, alignment.getBreadthPos(orientation, displayable, breadthPosBase));

            spanPos += orientation.getSpan(displayable) + componentBuffer;
        }
    }

    public enum Orientation
    {
        VERTICAL(Locatable::getHeight, Locatable::getWidth,
                Locatable::setY, Locatable::setX,
                AlignedDisplayable::getBreadth, AlignedDisplayable::getSpan),

        HORIZONTAL(Locatable::getWidth, Locatable::getHeight,
                Locatable::setX, Locatable::setY,
                AlignedDisplayable::getSpan, AlignedDisplayable::getBreadth);

        Orientation(DimensionAccessor getSpan, DimensionAccessor getBreadth,
                    LocationMutator setSpan, LocationMutator setBreadth,
                    AlignedDimensionAccessor getWidth, AlignedDimensionAccessor getHeight)
        {
            this.getSpan = getSpan;
            this.getBreadth = getBreadth;
            this.setSpan = setSpan;
            this.setBreadth = setBreadth;
            this.getWidth = getWidth;
            this.getHeight = getHeight;
        }

        private final DimensionAccessor getSpan, getBreadth;
        private final LocationMutator setSpan, setBreadth;
        private final AlignedDimensionAccessor getWidth, getHeight;

        public int getSpan(Locatable locatable) { return this.getSpan.get(locatable); }
        public int getBreadth(Locatable locatable) { return this.getBreadth.get(locatable); }
        public void setSpan(Locatable locatable, int span) { this.setSpan.set(locatable, span); }
        public void setBreadth(Locatable locatable, int breadth) { this.setBreadth.set(locatable, breadth); }

        public int getWidth(AlignedDisplayable displayable) { return this.getWidth.get(displayable); }
        public int getHeight(AlignedDisplayable displayable) { return this.getHeight.get(displayable); }

        private interface DimensionAccessor
        {
            int get(Locatable locatable);
        }

        private interface LocationMutator
        {
            void set(Locatable locatable, int value);
        }

        private interface AlignedDimensionAccessor
        {
            int get(AlignedDisplayable displayable);
        }
    }

    public enum Alignment
    {
        POSITIVE((alignment, locatable, base) -> base + (alignment.getBreadth(locatable) / 2)),
        NEUTRAL((alignment, locatable, base) -> 0),
        NEGATIVE((alignment, locatable, base) -> base - (alignment.getBreadth(locatable) / 2));

        Alignment(BreadthPosCalculator breadthPosCalculator)
        {
            this.breadthPosCalculator = breadthPosCalculator;
        }

        public int getBreadthPos(Orientation orientation, Locatable locatable, int breadthPosBase)
        {
            return this.breadthPosCalculator.get(orientation, locatable, breadthPosBase);
        }

        private final BreadthPosCalculator breadthPosCalculator;

        private interface BreadthPosCalculator
        {
            int get(Orientation orientation, Locatable locatable, int breadthPosBase);
        }
    }
}
