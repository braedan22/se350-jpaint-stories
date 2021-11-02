package model.picture;

import model.ShapeShadingType;
import model.ShapeType;
import java.awt.*;

public class Triangle implements Shape{
    private ShapeType shapeType = ShapeType.TRIANGLE;
    private Color primary;
    private Color secondary;
    private ShapeShadingType treatment;
    private Point start;
    private Point end;
    private int[] xPoints;
    private int[] yPoints;

    private int left;
    private int right;
    private int top;
    private int bottom;

    public Triangle(Point start, Point end, Color primary, Color secondary, ShapeShadingType treatment){
        this.primary = primary;
        this.secondary = secondary;
        this.treatment = treatment;
        this.start = start;
        this.end = end;
        this.xPoints = new int[]{end.getX(), (start.getX()+ end.getX())/2,    start.getX()};
        this.yPoints = new int[]{end.getY(), start.getY(),                    end.getY()};
        this.right = (Math.max(start.getX(), end.getX()));
        this.left = (Math.min(start.getX(), end.getX()));
        this.bottom = (Math.max(start.getY(), end.getY()));
        this.top = (Math.min(start.getY(), end.getY()));
    }


    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        Stroke oldStroke = graphics2D.getStroke();

        graphics.setColor(primary);
        if (treatment.equals(ShapeShadingType.FILLED_IN)) {
            graphics.fillPolygon(xPoints, yPoints, 3);
        }
        else if (treatment.equals(ShapeShadingType.OUTLINE)) {
            graphics.drawPolygon(xPoints, yPoints, 3);
        }
        else if (treatment.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            graphics.fillPolygon(xPoints, yPoints, 3);
            graphics.setColor(secondary);
            graphics.drawPolygon(xPoints, yPoints, 3);
        }

        for (Shape shape : Selected.get()) {
            if (this.equals(shape)) {
                graphics.setColor(Color.BLACK);
                int[] newXPoints;
                int[] newYPoints;
                if (yPoints[0] > yPoints[1]){
                    newXPoints = new int[]{xPoints[0]+6, xPoints[1], xPoints[2]-6};
                    newYPoints = new int[]{yPoints[0]+3, yPoints[1]-6, yPoints[2]+3};
                }
                else {
                    newXPoints = new int[]{xPoints[0]-6, xPoints[1], xPoints[2]+6};
                    newYPoints = new int[]{yPoints[0]-3, yPoints[1]+6, yPoints[2]-3};
                }
                graphics2D.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
                graphics2D.drawPolygon(newXPoints, newYPoints, 3);
                graphics2D.setStroke(oldStroke);
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
        return new Triangle(start, end, primary, secondary, treatment);
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
        this.xPoints = new int[]{end.getX(), (start.getX()+ end.getX())/2,    start.getX()};
        this.yPoints = new int[]{end.getY(), start.getY(),                    end.getY()};
        this.right = (Math.max(start.getX(), end.getX()));
        this.left = (Math.min(start.getX(), end.getX()));
        this.bottom = (Math.max(start.getY(), end.getY()));
        this.top = (Math.min(start.getY(), end.getY()));
    }

    @Override
    public void setEnd(Point point) {
        end = point;
        this.xPoints = new int[]{end.getX(), (start.getX()+ end.getX())/2,    start.getX()};
        this.yPoints = new int[]{end.getY(), start.getY(),                    end.getY()};
        this.right = (Math.max(start.getX(), end.getX()));
        this.left = (Math.min(start.getX(), end.getX()));
        this.bottom = (Math.max(start.getY(), end.getY()));
        this.top = (Math.min(start.getY(), end.getY()));
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
