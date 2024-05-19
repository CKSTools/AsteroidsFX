package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author corfixen
 */
public class AsteroidSplitterImpl implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        System.out.println("Splitting asteroid");
        System.out.println("Splitting.");
        world.addEntity(createSplinter(e.getRotation() + 35, e.getRadius(), e.getHealth(), e.getX(), e.getY()));
        world.addEntity(createSplinter(e.getRotation() - 35, e.getRadius(), e.getHealth(), e.getX(), e.getY()));

        world.removeEntity(e);}

    private Entity createSplinter(double v, float size, int health, double x, double y) {
        Entity asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(x);
        asteroid.setY(y);
        asteroid.setRadius(size-1);
        asteroid.setRotation(v);
        asteroid.setHealth(health);
        asteroid.setSpeed(100);
        return asteroid;
    }

}

