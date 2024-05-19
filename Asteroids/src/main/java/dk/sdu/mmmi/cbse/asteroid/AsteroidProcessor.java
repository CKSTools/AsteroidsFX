package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {

    private IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

    private static double spawnRate = 5;
    private static double spawnTimer = 0.0;

    @Override
    public void process(GameData gameData, World world) {

        spawnTimer += gameData.getDeltaTime();
        if (spawnTimer >= spawnRate) {
            world.addEntity(new AsteroidPlugin().createAsteroid(gameData));
            spawnTimer -= spawnRate;
        }

        for (Entity asteroid : world.getEntities(Asteroid.class)) {


            if(asteroid.getHealth() <= 0){
                world.removeEntity(asteroid);
                continue;
            }

            if (asteroidSplitter != null) {
                if (((Asteroid) asteroid).isHit()) {
                    System.out.println("Asteroid hit and splitting.");
                    asteroidSplitter.createSplitAsteroid(asteroid, world);
                    ((Asteroid) asteroid).setHit(false);
                }
            }

            asteroid.forward(gameData.getDelta());
            asteroid.rotate(gameData.getDelta(), true);

            if (asteroid.getX() < 0) {
                asteroid.setX(asteroid.getX() + gameData.getDisplayWidth());
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(asteroid.getX() % gameData.getDisplayWidth());
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(asteroid.getY() + gameData.getDisplayHeight());
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(asteroid.getY() % gameData.getDisplayHeight());
            }
        }

    }

    /**
     * Dependency Injection using OSGi Declarative Services
     */
    public void setAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = asteroidSplitter;
    }

    public void removeAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = null;
    }


}
