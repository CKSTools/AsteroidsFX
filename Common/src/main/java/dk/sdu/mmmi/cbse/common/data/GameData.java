package dk.sdu.mmmi.cbse.common.data;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private double deltaTime;
    private long lastFrameTime;

    private int destroyedAsteroids = 0;

    public double getDeltaTime() {
        return deltaTime / 1_000_000_000.0; // convert nanoseconds to seconds
    }

    public void updateDeltaTime() {
        long currentTime = System.nanoTime();
        if (lastFrameTime > 0) {
            deltaTime = currentTime - lastFrameTime;
        }
        lastFrameTime = currentTime;
    }


    private long delta;

    public long getDelta() {
        return delta;
    }

    public void setDelta(long delta) {
        this.delta = delta;
    }


    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }


    public int getDestroyedAsteroids() {
        return destroyedAsteroids;
    }
    public int setDestroyedAsteroids(int destroyedAsteroids) {
        return this.destroyedAsteroids = destroyedAsteroids;
    }
}
