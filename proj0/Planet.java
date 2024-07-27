
public class Planet {
    public double xxPos;

    public double yyPos;

    public double xxVel;

    public double yyVel;

    public double mass;

    public String imgFileName;

    private static final double G = 6.67e-11;


    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return G * this.mass * p.mass / Math.pow(distance, 2);
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        return force * dx / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        return force * dy / distance;
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double netForce = 0;
        for (Planet p : ps) {
            if (!this.equals(p)) {
                netForce += calcForceExertedByX(p);
            }
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double netForce = 0;
        for (Planet p : ps) {
            if (!this.equals(p)) {
                netForce += calcForceExertedByY(p);
            }
        }
        return netForce;
    }

    public void update(double dt, double forceX, double forceY) {
        double ax = forceX / this.mass;
        double ay = forceY / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}