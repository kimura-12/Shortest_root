package shortestRoute;

public class GSResult<T> {
    public boolean success;
    public String text;
    public T json;
    public String toString() {
        return "" + success + ":" + text + ":"+json;
    }
}
