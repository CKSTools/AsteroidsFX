import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.enemy.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemy.EnemyPlugin;

module Enemy {
    requires javafx.graphics;
    requires Common;
    requires CommonEnemy;
    requires CommonWeapon;

    uses dk.sdu.mmmi.cbse.common.weapon.WeaponSPI;

    provides IGamePluginService with EnemyPlugin;
    provides IEntityProcessingService with EnemyControlSystem;
}