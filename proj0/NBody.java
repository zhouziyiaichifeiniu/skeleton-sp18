public class NBody {
    @SuppressWarnings("all")
    public static double readRadius(String filename) {
        In in = new In(filename);
        int numPlanet = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    @SuppressWarnings("all")
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numPlanet = in.readInt();
        Planet[] planets = new Planet[numPlanet];
        double radius = in.readDouble();
        for (int i = 0; i < numPlanet; i++) {
            planets[i] = new Planet(in.readDouble(),
                    in.readDouble(),
                    in.readDouble(),
                    in.readDouble(),
                    in.readDouble(),
                    in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        String backgroundImage = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);

        StdDraw.enableDoubleBuffering();
        for (double time = 0; time < T; time += dt) {
            double xForces[] = new double[planets.length];
            double yForces[] = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, backgroundImage);

            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}