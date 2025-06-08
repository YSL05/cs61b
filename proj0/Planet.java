public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    private double mass;
    private String imgFileName;
    private static double gravitationalConst = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img)
    {
        this.xxPos       = xP;
        this.yyPos       = yP;
        this.xxVel       = xV;
        this.yyVel       = yV;
        this.mass        = m;
        this.imgFileName = img;
    }

    public Planet(Planet p)
    {
        this.xxPos       = p.xxPos;
        this.yyPos       = p.yyPos;
        this.xxVel       = p.xxVel;
        this.yyVel       = p.yyVel;
        this.mass        = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p)
    {
        return Math.sqrt(Math.pow((this.xxPos - p.xxPos), 2) + Math.pow((this.yyPos - p.yyPos), 2));
    }

    public double calcForceExertedBy(Planet p)
    {
        double distance = calcDistance(p);
        return gravitationalConst * this.mass * p.mass / Math.pow(distance, 2);
    }

    public double calcForceExertedByX(Planet p)
    {
        double distance = calcDistance(p);
        double totalForce = calcForceExertedBy(p);
        return totalForce * (p.xxPos - this.xxPos) / distance;
    }

    public double calcForceExertedByY(Planet p)
    {
        double distance = calcDistance(p);
        double totalForce = calcForceExertedBy(p);
        return totalForce * (p.yyPos - this.yyPos) / distance;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets)
    {
        double netForceX = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (this.equals(allPlanets[i])) {
                continue;
            }
            netForceX += calcForceExertedByX(allPlanets[i]);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets)
    {
        double netForceY = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (this.equals(allPlanets[i])) {
                continue;
            }
            netForceY += calcForceExertedByY(allPlanets[i]);
        }
        return netForceY;
    }

    public void update(double time, double Fx, double Fy)
    {
        double ax   = Fx / this.mass;
        double ay   = Fy / this.mass;
        this.xxVel += time * ax;
        this.yyVel += time * ay;
        this.xxPos += this.xxVel * time;
        this.yyPos += this.yyVel * time;
    }

    public void draw()
    {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }


}
