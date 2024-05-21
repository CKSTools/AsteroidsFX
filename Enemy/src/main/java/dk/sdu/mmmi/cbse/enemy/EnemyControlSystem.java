package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.weapon.WeaponSPI;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class EnemyControlSystem implements IEntityProcessingService {
    private Random rnd = new Random();
    private static double spawnRate = 5.0;
    private static double spawnTimer = 0.0;

    public EnemyControlSystem() {
    }

    public void process(GameData gameData, World world) {
        spawnTimer += gameData.getDeltaTime();
        if (spawnTimer >= spawnRate) {
            System.out.println("Spawning enemy");
            world.addEntity((new EnemyPlugin()).createEnemy(gameData));
            spawnTimer -= spawnRate;
        }

        Iterator var3 = world.getEntities(new Class[]{Enemy.class}).iterator();

        while(var3.hasNext()) {
            Entity entity = (Entity)var3.next();
            Enemy enemy = (Enemy)entity;
            if (enemy.getHealth() <= 0) {
                world.removeEntity(enemy);
            }

            if (enemy.getWeapon() != null) {
                enemy.getWeapon().setisFiring(true);
            } else {
                this.getWeaponSPIs().stream().findFirst().ifPresent((spi) -> {
                    System.out.println("Creating weapon.");
                    enemy.setWeapon(spi.createWeapon(enemy));
                    enemy.getWeapon().setFireRate(1.0);
                    world.addEntity(enemy.getWeapon());
                });
            }

            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setRotation(enemy.getRotation() + (double)this.rnd.nextInt(-2, 3) * enemy.getRotation() * gameData.getDeltaTime());
            this.rnd.setSeed(System.currentTimeMillis());
            enemy.setX(enemy.getX() + changeX * (double)enemy.getForwardRate() * gameData.getDeltaTime());
            enemy.setY(enemy.getY() + changeY * (double)enemy.getForwardRate() * gameData.getDeltaTime());
            if (enemy.getX() < 0.0) {
                enemy.setX(enemy.getX() + (double)gameData.getDisplayWidth());
            }

            if (enemy.getX() > (double)gameData.getDisplayWidth()) {
                enemy.setX(enemy.getX() % (double)gameData.getDisplayWidth());
            }

            if (enemy.getY() < 0.0) {
                enemy.setY(enemy.getY() + (double)gameData.getDisplayHeight());
            }

            if (enemy.getY() > (double)gameData.getDisplayHeight()) {
                enemy.setY(enemy.getY() % (double)gameData.getDisplayHeight());
            }
        }

    }

    private Collection<? extends WeaponSPI> getWeaponSPIs() {
        return (Collection)ServiceLoader.load(WeaponSPI.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}