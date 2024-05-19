package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
/*

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;                    
                }
*/


                // CollisionDetection
                if (this.collides(entity1, entity2)) {

                    if(entity1.getType().equals("ASTEROID") && entity2.getType().equals("PLAYER_BULLET")) {
                        world.removeEntity(entity2);
                        entity1.setHealth(entity1.getHealth()-1);
                        System.out.println("Entity 1: " + entity1.getType() +"entity 2: " + entity2.getType());
                        ((Asteroid) entity1).setHit(true);
                        System.out.println("before kill" + gameData.getDestroyedAsteroids());
                        gameData.setDestroyedAsteroids(gameData.getDestroyedAsteroids()+1);
                        System.out.println("after kill" + gameData.getDestroyedAsteroids());
                    }
                    if (entity1.getType().equals("ASTEROIDSPLINTER") && entity2.getType().equals("PLAYER")) {
                        world.removeEntity(entity1);
                        entity2.setHealth(entity2.getHealth()-1);
                        gameData.setDestroyedAsteroids(gameData.getDestroyedAsteroids()+1);
                    }
                    if(entity1.getType().equals("ASTEROIDSPLINTER") && entity2.getType().equals("PLAYER_BULLET")) {
                        world.removeEntity(entity2);
                        world.removeEntity(entity1);
                        gameData.setDestroyedAsteroids(gameData.getDestroyedAsteroids()+1);
                    }
                    if(entity1.getType().equals("ASTEROID") && entity2.getType().equals("PLAYER")) {
                        entity2.setHealth(entity2.getHealth()-1);
                        ((Asteroid) entity1).setHit(true);
                        System.out.println("Entity 1:" + entity1.getType() +"entity 2:" + entity2.getType());
                        System.out.println("Entity 1 health:" + entity1.getHealth() +"entity 2 health:" + entity2.getHealth());
                        gameData.setDestroyedAsteroids(gameData.getDestroyedAsteroids()+1);
                    }

                }

            }
        }

    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}
