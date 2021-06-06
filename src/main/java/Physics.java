import processing.core.PVector;

import java.util.ArrayList;

public class Physics {
    //TODO Вынести константы в отдельный класс; класс унаследовать/статически импортировать
    public static final float KULON = (float) 5;
    public static final float minRadiusOfParticle = 5;
    public static final float rotationSlowdown = (float) 0.999;

    public static void update(ArrayList<Magnet> magnets){
        for(Magnet mag : magnets) {
            mag.run();
            mag.setVelocity(PVector.mult(mag.getVelocity(), rotationSlowdown));
        }
        for(Magnet mag1 : magnets) {
            for(Magnet mag2 : magnets) {
                gravity(mag1, mag2);
                if(mag1 == mag2)break;
            }
        }
    }

    public static void gravity(Magnet mag1, Magnet mag2){
        for(Particle part1 : mag1.getParticle()){
            for(Particle part2 : mag2.getParticle()){
                PVector force = ForceGravity(part1, part2);
                forceDisplay(mag1, force, part1);
                force.mult(-1);
                forceDisplay(mag2, force, part2);
            }
        }
    }

    public static void forceDisplay(Magnet mag, PVector force, Particle part){
        mag.setSpeed( PVector.add(mag.getSpeed(), PVector.div(force, mag.getMass()) ) );

        if(mag.getMomentOfInertia() == 0) return;

        PVector Moment = force.cross(PVector.add(part.absoluteCoord, PVector.mult(mag.getCoord(),-1)));
        mag.setVelocity( PVector.add(mag.getVelocity(), Moment.div(-mag.getMomentOfInertia())) );
    }

    public static PVector ForceGravity(Particle part1, Particle part2){
        PVector vector = new PVector();
        vector.set(part2.absoluteCoord).mult(-1).add(part1.absoluteCoord);
        float F = KULON * part1.charge * part2.charge / Math.max(vector.magSq(), minRadiusOfParticle);
        vector.normalize();
        vector.mult(F);
        return vector;
    }

}