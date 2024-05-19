package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

/**
 *
 * @author corfixen
 */
public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        Entity asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    public Entity createAsteroid(GameData gameData) {
        System.out.println("Create asteroid");
        Entity asteroid = new Asteroid();
        Random rnd = new Random();
        int size = rnd.nextInt(11) + 10;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(rnd.nextInt(gameData.getDisplayWidth()));
        asteroid.setY(rnd.nextInt(gameData.getDisplayHeight()));
        asteroid.setRadius(size);
        asteroid.setHeading(rnd.nextInt(360));
        asteroid.setForwardRate(100);
        asteroid.setRotation(0);
        asteroid.setRotationRate(300);
        int a = rnd.nextInt(2) + 1;
        asteroid.setHealth(a);
        System.out.println("Astroid hp = " + a);
        asteroid.setType("ASTEROID");
        System.out.println("Asteroid created size " + size);
        return asteroid;
    }
}
