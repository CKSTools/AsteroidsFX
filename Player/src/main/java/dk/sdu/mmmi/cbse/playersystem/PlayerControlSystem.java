package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.weapon.WeaponSPI;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

            
        for (Entity player : world.getEntities(Player.class)) {

            if (player.getHealth() <= 0) {
                world.removeEntity(player);
                continue;
            }
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.rotate(gameData.getDelta(),false);
                player.setHeading(player.getRotation());
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.rotate(gameData.getDelta(),true);
                player.setHeading(player.getRotation());
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                player.forward(gameData.getDelta());
            }
            if(gameData.getKeys().isDown(GameKeys.SPACE)) {
                this.getWeaponSPIs().stream().findFirst().ifPresent((spi) -> {
                    System.out.println("Creating weapon.");
                    player.setWeapon(spi.createWeapon(player));
                    player.get
                    world.addEntity(player.getWeapon());
                });
            }
            
        if (player.getX() < 0) {
            player.setX(1);
        }

        if (player.getX() > gameData.getDisplayWidth()) {
            player.setX(gameData.getDisplayWidth()-1);
        }

        if (player.getY() < 0) {
            player.setY(1);
        }

        if (player.getY() > gameData.getDisplayHeight()) {
            player.setY(gameData.getDisplayHeight()-1);
        }

                                        
        }
    }

    private Collection<? extends WeaponSPI> getWeaponSPIs() {
        return (Collection)ServiceLoader.load(WeaponSPI.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
