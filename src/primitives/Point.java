package primitives;


import static java.lang.Math.sqrt;

/**
 * Point class represents a point at the 3D Cartesian coordinate
 */
public class Point {
    public static  Point ZERO= new Point(0,0,0);

    /**
     * Point will be represented by double3 type
     */
    protected Double3 xyz;

    /**
     * Constructs of a Point by a Double3
     * @param xyz the Double3 that will be the new point
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Constructs of a point by 3 3D Cartesian coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Point(double x,double y,double z)
    {

        this.xyz = new Double3(x,y,z);
    }

    /**
     * Subtracts of a point from a vector
     * @param p point to be reduced
     * @return Subtraction of a point from a vector
     */
    final public Vector subtract (Point p)
    {
        return new Vector(this.xyz.subtract(p.xyz));

    }

    /**
     * Adds two points
     * @param p point to add
     * @return sum of points
     */
    final public Point add (Point p)
    {
        return new Point(this.xyz.add(p.xyz));
    }

    /**
     * Calculates the squared distance between two points
     * @param p point to calculate squared distance with
     * @return squared distance between two points
     */
    final public double distanceSquared(Point p)
    {
        return (this.xyz.d1 - p.xyz.d1)*(this.xyz.d1 - p.xyz.d1)+(this.xyz.d2 - p.xyz.d2)*(this.xyz.d2 - p.xyz.d2)+(this.xyz.d3 - p.xyz.d3)*(this.xyz.d3 - p.xyz.d3);
    }

    /**
     * Calculates the distance between two points
     * @param p point to calculate distance with
     * @return distance between two points
     */
    final public double distance(Point p)
    {
        return sqrt(distanceSquared(p));
    }

    @Override
    public String toString() {
        return xyz.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Point )) {
            return false;
        }
        return xyz.equals(((Point) obj).xyz);

    }
    /* regular multiple returns the Point (x1*x2,y1*y2,z1*z2)*/
    final public Point multiplication(Vector v)
    {
        return new Point(xyz.product(v.xyz));
    }

    public double getX() {
        return xyz.d1;
    }
    public double getY() {
        return xyz.d2;
    }
    public double getZ() {
        return xyz.d3;
    }
}
