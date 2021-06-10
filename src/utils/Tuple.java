package utils;

import java.util.Objects;

public class Tuple<X,Y> {

    /**
     * First element
     */
    public X x;

    /**
     * Second element
     */
    public Y y;

    /**
     * Instantiate a Tuple with each element
     * @param x First element
     * @param y Second element
     */
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiate a Tuple from a Triplet
     * @param tuple Tuple's object
     */
    // Shallow-clone
    public Tuple(Tuple<X, Y> tuple){
        this.x = tuple.getX();
        this.y = tuple.getY();
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
     * Clone current instance
     * @return Cloned instance
     */
    @Override
    public Tuple<X,Y> clone(){
        return new Tuple<>(this);
    }

    /**
     * Equality between Tuple's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(x, tuple.x) && Objects.equals(y, tuple.y);
    }

    /**
     * Hascode of the current Tuple's instance
     * @return Hashcode of the instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * String representation of Tuple's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tuple{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
}
