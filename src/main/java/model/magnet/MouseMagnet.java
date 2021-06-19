package model.magnet;

import processing.core.PVector;
import view.GraphicObject;
import processing.core.PApplet;

public class MouseMagnet extends Magnet{

    PApplet sketch;

    public MouseMagnet(Particle[] particle, PApplet sketch, GraphicObject graphic) {
        super(particle, graphic);
        this.sketch = sketch;
    }

    public void run(){
        setCoord(sketch.mouseX, sketch.mouseY, getCoord().z);
        setSpeed(0,0,0);
        //setAngle(PVector.add(getAngle(), getVelocity()));
        rotate(getVelocity());
    }
}
