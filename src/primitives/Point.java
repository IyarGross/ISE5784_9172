package primitives;
import static java.lang.Math.sqrt;
public class Point {
    // Point will be represented by double3 type
     final Double3 xyz ;
    // point con, gets x ,y z cordeniats and build a new double3
    public Point (double x,double y,double z){
        xyz = new Double3(x,y,z);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;// if equal return true
        if (!(o instanceof Point point)) return false;// if not equal return false

        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return xyz.hashCode();
    }
}






