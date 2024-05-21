package dk.sdu.mmmi.cbse.common.weapon;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Weapon extends Entity {
    private double fireRate;
    private double fireCD;

    private boolean isFiring;

    private final Entity owner;

    public Weapon(Entity owner) {
        this.owner = owner;
    }

    public Entity getOwner() {
        return owner;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }

    public double getFireCD() {
        return fireCD;
    }

    public void setFireCD(double fireCD) {
        this.fireCD = fireCD;
    }

    public void setisFiring(boolean isFiring) {
        this.isFiring = isFiring;
    }

    public boolean getIsFiring() {
        return isFiring;
    }


    public boolean canShoot() {
        return (fireCD >= fireRate);
    }




}
