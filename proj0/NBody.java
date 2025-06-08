public class NBody {

    private int planetNum = 0;
    private double radius = 0;
    public static String imageToDraw = "./images/starfield.jpg";

    public static double readRadius(String filename)
    {
        In in = new In(filename);
        int num = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename)
    {
        In in = new In(filename);
        int planetnum = in.readInt();
        Planet[] allPlanets = new Planet[planetnum];
        double radius = in.readDouble();
        for (int i = 0; i < planetnum; i++) {
            double xP   = in.readDouble();
            double yP   = in.readDouble();
            double xV   = in.readDouble();
            double yV   = in.readDouble();
            double mass = in.readDouble();
            String img  = in.readString();
            allPlanets[i] = new Planet(xP, yP, xV, yV, mass, img);
        }
        return allPlanets;
    }
    private static void drawBackground(double radius)
    {
        StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
		StdDraw.clear();

		/* Stamps three copies of advice.png in a triangular pattern. */
		StdDraw.picture(0, radius, imageToDraw);
        StdDraw.picture(0, -radius, imageToDraw);
        StdDraw.picture(radius, 0, imageToDraw);
        StdDraw.picture(-radius, 0, imageToDraw);
		
        /* Shows the drawing to the screen */
		StdDraw.show();
    }

    public static void main(String[] args)
    {
        /* step1: read file to get planet and radius */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius   = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        /* step2: draw background */
        drawBackground(radius);

        /* step3: draw planet */
        for (int i = 0; i < allPlanets.length; i++) {
            allPlanets[i].draw();
        }
        StdDraw.show();

        /* animation */
        StdDraw.enableDoubleBuffering();
        double time = 0;
        double xforce = 0;
        double yforce = 0;
        while (time < T) {
            drawBackground(radius);
            for (int i = 0; i < allPlanets.length; i++) {
                xforce = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yforce = allPlanets[i].calcNetForceExertedByY(allPlanets);
                allPlanets[i].update(dt, xforce, yforce);
                allPlanets[i].draw();
            }
            time += dt;
            StdOut.printf("%d\n", allPlanets.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < allPlanets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                              allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                              allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}
