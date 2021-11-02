package model.picture;

import model.ShapeShadingType;
import model.ShapeType;
import java.awt.*;

public class Rectangle implements Shape{
    private ShapeType shapeType = ShapeType.RECTANGLE;
    private Color primary;
    private Color secondary;
    private ShapeShadingType treatment;
    private Point start;
    private Point end;
    private Point topLeft;
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int width;
    private int height;

    public Rectangle(Point start, Point end, Color primary, Color secondary, ShapeShadingType treatment){
        this.primary = primary;
        this.secondary = secondary;
        this.treatment = treatment;
        this.start = start;
        this.end = end;
        this.right = (Math.max(start.getX(), end.getX()));
        this.left = (Math.min(start.getX(), end.getX()));
        this.bottom = (Math.max(start.getY(), end.getY()));
        this.top = (Math.min(start.getY(), end.getY()));
        this.topLeft = new Point(left, top);
        this.width = right - left;
        this.height = bottom - top;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(primary);
        if (treatment.equals(ShapeShadingType.FILLED_IN)) {
            graphics.fillRect(topLeft.getX(), topLeft.getY(), width, height);
        }
        else if (treatment.equals(ShapeShadingType.OUTLINE)) {
            graphics.drawRect(topLeft.getX(), topLeft.getY(), width, height);
        }
        else if (treatment.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            graphics.fillRect(topLeft.getX(), topLeft.getY(), width, height);
            graphics.setColor(secondary);
            graphics.drawRect(topLeft.getX(), topLeft.getY(), width, height);
        }

        for (Shape shape:Selected.get()) {
            if(this.equals(shape)){
                graphics.setColor(Color.BLACK);
                Graphics2D graphics2D = (Graphics2D) graphics.create();
                graphics2D.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
                graphics2D.drawRect(topLeft.getX()-3, topLeft.getY()-3, width+6, height+6);
            }
        }
    }

    @Override
    public boolean equals(Shape shape2) {
        return shape2.getShapeType().equals(shapeType) &&
                shape2.getPrimary().equals(primary) &&
                shape2.getSecondary().equals(secondary) &&
                shape2.getTreatment().equals(treatment) &&
                shape2.getStart().equals(start) &&
                shape2.getEnd().equals(end);
    }

    @Override
    public Shape clone() {
        return new Rectangle(start, end, primary, secondary, treatment);
    }

    @Override
    public ShapeType getShapeType() {
        return shapeType;
    }

    @Override
    public Color getPrimary() {
        return primary;
    }

    @Override
    public Color getSecondary() {
        return secondary;
    }

    @Override
    public ShapeShadingType getTreatment() {
        return treatment;
    }

    @Override
    public Point getStart() {
        return start;
    }

    @Override
    public Point getEnd() {
        return end;
    }

    @Override
    public void setStart(Point point) {
        start = point;
        this.right = (Math.max(start.getX(), end.getX()));
        this.left = (Math.min(start.getX(), end.getX()));
        this.bottom = (Math.max(start.getY(), end.getY()));
        this.top = (Math.min(start.getY(), end.getY()));
        this.topLeft = new Point(left, top);
        this.width = right - left;
        this.height = bottom - top;
    }

    @Override
    public void setEnd(Point point) {
        end = point;
        this.right = (Math.max(start.getX(), end.getX()));
        this.left = (Math.min(start.getX(), end.getX()));
        this.bottom = (Math.max(start.getY(), end.getY()));
        this.top = (Math.min(start.getY(), end.getY()));
        this.topLeft = new Point(left, top);
        this.width = right - left;
        this.height = bottom - top;
    }

    @Override
    public Boolean intersects(Point p1, Point p2) {
        int interLeft =     (Math.min(p1.getX(), p2.getX()));
        int interRight =    (Math.max(p1.getX(), p2.getX()));
        int interTop =      (Math.min(p1.getY(), p2.getY()));
        int interBottom =   (Math.max(p1.getY(), p2.getY()));

        return left < interRight && right > interLeft && top < interBottom && bottom > interTop;
    }
}
