package utils;

import java.util.Objects;

public class Triplet<X, Y, Z> {
    /**
     * First element
     */
    public X x;

    /**
     * Second element
     */
    public Y y;

    /**
     * Third element
     */
    public Z z;

    /**
     * Instantiate a Triplet with each element
     * @param x First element
     * @param y Second element
     * @param z Third element
     */
    public Triplet(X x, Y y, Z z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Instantiate a Triplet from a Triplet
     * @param triplet Triplet's object
     */
    // Shallow-clone
    public Triplet(Triplet<X, Y, Z> triplet){
        this.x = triplet.getX();
        this.y = triplet.getY();
        this.z = triplet.getZ();
    }

    /**
     * Get first element
     * @return First element
     */
    public X getX() {
        return x;
    }

    /**
     * Get second element
     * @return Second element
     */
    public Y getY() {
        return y;
    }

    /**
     * Get third element
     * @return Third element
     */
    public Z getZ() {
        return z;
    }

    /**
     * Set first element
     * @param x First element
     */
    public void setX(X x) {
        this.x = x;
    }

    /**
     * Set second element
     * @param y Second element
     */
    public void setY(Y y) {
        this.y = y;
    }

    /**
     * Set third element
     * @param z Third element
     */
    public void setZ(Z z) {
        this.z = z;
    }

    /**
     * Clone current instance
     * @return Cloned instance
     */
    @Override
    public Triplet<X,Y,Z> clone(){
        return new Triplet<>(this);
    }

    /**
     * Equality between Triplet's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) o;
        return Objects.equals(x, triplet.x) && Objects.equals(y, triplet.y) && Objects.equals(z, triplet.z);
    }

    /**
     * String representation of Triplet's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Triplet{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", z=").append(z);
        sb.append('}');
        return sb.toString();
    }
}
