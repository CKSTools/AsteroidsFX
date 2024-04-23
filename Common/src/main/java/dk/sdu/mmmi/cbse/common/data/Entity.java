package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
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

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
        
    public float getRadius() {
        return this.radius;
    }

    public void rotation(long delta, boolean right) {
        double rate =  (double) (this.forwardRate * delta) /1000000000;
        if(right) {
            this.rotation += rate;
        }
        else this.rotation -= rate;
    }


    public void forward(long delta) {
        double rate =  (double) (this.forwardRate * delta) /1000000000;
        this.x += Math.cos(Math.toRadians(rotation)) * rate;
        this.y += Math.sin(Math.toRadians(rotation)) * rate;
    }
}
