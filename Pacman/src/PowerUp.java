public class PowerUp {
    private int x;
    private int y;
    private PowerUpType type;

    public PowerUp(int x, int y, PowerUpType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public PowerUpType getType() {
        return type;
    }
}
