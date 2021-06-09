package utils;

import java.util.Objects;

public class Triplet<X, Y, Z> {
    public X x;
    public Y y;
    public Z z;

    public Triplet(X x, Y y, Z z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Shallow-clone
    public Triplet(Triplet<X, Y, Z> triplet){
        this.x = triplet.getX();
        this.y = triplet.getY();
        this.z = triplet.getZ();
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public Z getZ() {
        return z;
    }

    public void setX(X x) {
        this.x = x;
    }

    public void setY(Y y) {
        this.y = y;
    }

    public void setZ(Z z) {
        this.z = z;
    }

    @Override
    public Triplet<X,Y,Z> clone(){
        return new Triplet<>(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) o;
        return Objects.equals(x, triplet.x) && Objects.equals(y, triplet.y) && Objects.equals(z, triplet.z);
    }

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
