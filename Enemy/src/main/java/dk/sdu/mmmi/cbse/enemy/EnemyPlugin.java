package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;

    public EnemyPlugin() {
    }

    public void start(GameData gameData, World world) {
        int spawnCounter = 3;

        for(int i = 0; i < spawnCounter; ++i) {
            this.enemy = this.createEnemy(gameData);
            world.addEntity(this.enemy);
        }

    }

    public Entity createEnemy(GameData gameData) {
        this.enemy = new Enemy();
        Random rnd = new Random();
        this.enemy.setPolygonCoordinates(new double[]{-5.0, -5.0, 10.0, 0.0, -5.0, 5.0});
        this.enemy.setX((double)rnd.nextInt(gameData.getDisplayWidth()));
        this.enemy.setY((double)rnd.nextInt(gameData.getDisplayWidth()));
        this.enemy.setRadius(8.0F);
        this.enemy.setRotation((double)rnd.nextInt(360));
        this.enemy.setHealth(2);
        this.enemy.setForwardRate(150);
        this.enemy.setRotationRate(150);
        this.enemy.setType(EntityType.ENEMY_BULLET);
        return this.enemy;
    }

    public void stop(GameData gameData, World world) {
        world.removeEntity(this.enemy);
    }
}
