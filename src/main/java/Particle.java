import processing.core.PVector;

public class Particle {

    public PVector coord;
    public PVector absoluteCoord;

    public float charge;
    public float mass;

    public Particle(PVector coord, float charge, float mass){
        this.coord = coord;
        absoluteCoord = new PVector(coord.x, coord.y, coord.z);
        this.charge = charge;
        this.mass = mass;
    }

    public static float sqDist(Particle part1, Particle part2) {
        float dx = part1.absoluteCoord.x - part2.absoluteCoord.x;
        float dy = part1.absoluteCoord.y - part2.absoluteCoord.y;
        float dz = part1.absoluteCoord.z - part2.absoluteCoord.z;
        return (dx * dx + dy * dy + dz * dz);
    }

    @Override
    public String toString() {
        return "Particle{" +
                "coord=" + coord +
                ", absoluteCoord=" + absoluteCoord +
                ", charge=" + charge +
                ", mass=" + mass +
                '}';
    }
}
