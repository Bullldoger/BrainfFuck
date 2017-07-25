package life.brain.fuck.utils;

/**
 * Created by roman on 22.07.17.
 */
public class Pair<S, T> {

    private S s;
    private T t;

    public Pair(S s, T t) {
        this.s = s;
        this.t = t;
    }

    public S getFirst() { return this.s; }
    public T getSecond() { return this.t; }

    public void setFirst(S s) { this.s = s; }
    public void setSecond(T t) { this.t = t; }
}
