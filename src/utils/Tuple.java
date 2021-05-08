package utils;

public class Tuple<X,Y> {
    public final X x;
    public final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public Tuple(Tuple<X, Y> tuple){
        this.x = tuple.getX();
        this.y = tuple.getY();
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    @Override
    public Tuple<X,Y> clone(){
        return new Tuple<>(this);
    }

    public boolean equals(Tuple<X, Y> o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return getX().equals(o.getX()) &&
                getY().equals(o.getY());
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}