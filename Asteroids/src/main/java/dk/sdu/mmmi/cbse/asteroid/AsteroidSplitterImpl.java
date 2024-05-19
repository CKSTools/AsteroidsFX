package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;

/**
 *
 * @author corfixen
 */
public class AsteroidSplitterImpl implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        world.addEntity(createSplinter(e.getForwardRate() / 2, e.getRadius() / 2, e.getHealth(), e.getX(), e.getY()));
        world.addEntity(createSplinter(e.getForwardRate() / 2, e.getRadius() / 2, e.getHealth(), e.getX(), e.getY()));

        world.removeEntity(e);}

    private Entity createSplinter(int speed, float size, int health, double x, double y) {
        Entity asteroid = new Asteroid();
        Random rnd = new Random();
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(x);
        asteroid.setY(y);
        asteroid.setRadius(size);
        asteroid.setHeading(rnd.nextInt(360));
        asteroid.setRotation(0);
        asteroid.setForwardRate(speed);
        asteroid.setRotationRate(300);
        asteroid.setHealth(health);
        asteroid.setType("ASTEROIDSPLINTER");
        return asteroid;
    }

}

