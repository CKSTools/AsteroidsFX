package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double heading;
    private int rotationRate;
    private int health;
    private int speed;



    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public void setRotationRate(int rotationRate) {
        this.rotationRate = rotationRate;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    private double rotation = 0;
    private float radius;

    public void setForwardRate(int forwardRate) {
        this.forwardRate = forwardRate;
    }

    private int forwardRate;
            

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getHeading() {
        return heading;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
        
    public float getRadius() {
        return this.radius;
    }

//    public void heading(long delta, boolean right) {
//        double rate =  (double) (this.forwardRate * delta) /1000000000;
//        if(right) {
//            this.heading += rate;
//        }
//        else this.heading -= rate;
//    }


    public void forward(long delta) {
        double rate =  (double) (this.forwardRate * delta) /1000000000;
        this.x += Math.cos(Math.toRadians(heading)) * rate;
        this.y += Math.sin(Math.toRadians(heading)) * rate;
    }

    public void rotate(double delta, boolean right) {
        double value = (double) (this.rotationRate * delta) /1000000000;
        if(right) {
            this.rotation += value;
        }
        else this.rotation -= value;
    }
}
