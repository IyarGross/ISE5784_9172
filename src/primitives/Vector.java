package primitives;

import static java.lang.Math.sqrt;
/**
 * Vector class represents a algebraic vector at the 3D Cartesian coordinate
 */
public class  Vector  extends Point {
    public static Vector X  =new Vector(1,0,0);
    public static Vector Y = new Vector(0,1,0);
    public static Vector Z = new Vector( 0,0,1);

    /**
     * Vector constructions by the Double3 constructor
     * @param xyz point of the end of the vector while the start is at zero point
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Zero Vector can not be tolerate");
    }

    /**
     * Vector construction by three points in the 3D Cartesian coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Zero Vector can not be tolerate");

    }

    /**
     * Vector addition
     * @param v vector to add
     * @return new vector of sum of vectors
     */
    final public Vector add(Vector v)
    {
        return new Vector(this.xyz.add(v.xyz));
    }

    /**
     *
     * @param rhs multiplier
     * @return new scaled vector
     */
    final public Vector scale (double rhs)
    {
        return new Vector(this.xyz.scale(rhs));
    }

    /**
     * Calculates cross product of two vectors
     * @param v vector to multiply with
     * @return the cross product of two vectors
     */
    final public Vector crossProduct (Vector v)
    {

        return new Vector(this.xyz.d2*v.xyz.d3 - this.xyz.d3*v.xyz.d2,this.xyz.d3*v.xyz.d1-this.xyz.d1*v.xyz.d3,this.xyz.d1*v.xyz.d2-this.xyz.d2*v.xyz.d1);//calculate the cross product by this formula

    }

    /**
     * Calculates dot product of two vectors
     * @param v vector to multiply with
     * @return the dot product of two vectors
     */
    final  public double dotProduct (Vector v)
    {
        return this.xyz.d1*v.xyz.d1+this.xyz.d2 * v.xyz.d2+this.xyz.d3 *v.xyz.d3;
    }

    /**
     * Calculates the squared length of the vector
     * @return squared length of the vector
     */
    final  public double lengthSquared ()
    {
        return this.xyz.d1*this.xyz.d1+ this.xyz.d3*this.xyz.d3+this.xyz.d2*this.xyz.d2;
    }

    /**
     * Calculates the length of the vector
     * @return length of vector
     */
    final  public double length()
    {
        return sqrt(lengthSquared());
    }

    /**
     * Calculates the normalization of the vector
     * @return the normalized vector
     */
    final public Vector normalize ()
    {
        return new Vector (this.xyz.reduce(this.length()));
    }

    @Override
    final public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector other)) return false;//if the type isnt vector
        return super.equals((Point) obj);
    }
    /*all the spin methods use spining matrix */
    final public  Vector  spinX(double angle)
    {
        double angleRadian = Math.toRadians(angle);
        double cos = Math.cos(angleRadian);
        double sin = Math.sin(angleRadian);
        return new Vector(xyz.d1,cos*xyz.d2+sin* xyz.d3,cos* xyz.d3-xyz.d2*sin);
    }
    final public Vector  spinY(double angle)
    {
        double angleRadian = Math.toRadians(angle);
        double cos = Math.cos(angleRadian);
        double sin = Math.sin(angleRadian);
        return new Vector(xyz.d1*cos+ xyz.d3*sin,xyz.d2,cos* xyz.d3-xyz.d1*sin);
    }
    final public Vector  spinZ(double angle)
    {
        double angleRadian = Math.toRadians(angle);
        double cos = Math.cos(angleRadian);
        double sin = Math.sin(angleRadian);
        return new Vector(xyz.d1*cos - xyz.d2*sin,xyz.d1*sin+xyz.d2*cos,xyz.d3).normalize();
    }
    public Vector vectorRotate(Vector axis, double theta) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        double u = axis.getX();
        double v = axis.getY();
        double w = axis.getZ();

        double v1 = u * x + v * y + w * z;

        double thetaRad=Math.toRadians(theta);
        double xPrime = u * v1 * (1d - Math.cos(thetaRad))
                + x * Math.cos(thetaRad)
                + (-w * y + v * z) * Math.sin(thetaRad);
        double yPrime = v * v1 * (1d - Math.cos(thetaRad))
                + y * Math.cos(thetaRad)
                + (w * x - u * z) * Math.sin(thetaRad);

        double zPrime = w * v1 * (1d - Math.cos(thetaRad))
                + z * Math.cos(thetaRad)
                + (-v * x + u * y) * Math.sin(thetaRad);

        return new Vector(xPrime, yPrime, zPrime);
    }

    public Vector Roatate(double angle , Vector axsis ) {
        angle = angle / 180 * Math.PI;
        double cosa = Math.cos(angle), sina = Math.sin(angle);
        double x = axsis.xyz.d1, y = axsis.xyz.d2, z = axsis.xyz.d3, x2 = x * x, y2 = y * y, z2 = z * z;
        double tx = this.xyz.d1, ty = this.xyz.d2, tz = this.xyz.d3;
        return new Vector(
                (x2 * (1 - cosa) + cosa) * tx + (x * y * (1 - cosa) - sina * z) * ty + (x * z * (1 - cosa) + y * sina) * tz,
                (x * y * (1 - cosa) + z * sina) * tx + (y2 * (1 - cosa) + cosa) * ty + (y * z * (1 - cosa) - x * sina) * tz,
                (x * z * (1 - cosa) - y * sina) * tx + (y * z * (1 - cosa) + x * sina) * ty + (z2 * (1 - cosa) + cosa) * tz
        );
    }
}