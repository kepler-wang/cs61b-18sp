public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    private final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(
                (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return G * this.mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double r = this.calcDistance(p);
        double f = this.calcForceExertedBy(p);
        return f * (p.xxPos - this.xxPos) / r;
    }

    public double calcForceExertedByY(Planet p) {
        double r = this.calcDistance(p);
        double f = this.calcForceExertedBy(p);
        return f * (p.yyPos - this.yyPos) / r;
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double nf = 0;
        for (Planet p : ps) {
            if (p.equals(this)) {
                continue;
            }
            nf = nf + this.calcForceExertedByX(p);
        }
        return nf;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double nf = 0;
        for (Planet p : ps) {
            if (p.equals(this)) {
                continue;
            }
            nf = nf + this.calcForceExertedByY(p);
        }
        return nf;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + this.imgFileName);
    }
}