public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
    double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;         
              }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    } 
    public double calcDistance(Planet p){
        double xdis = this.xxPos - p.xxPos;
        double ydis = this.yyPos - p.yyPos;
        return Math.sqrt(xdis*xdis + ydis*ydis);
    }
    public double calcForceExertedBy(Planet p){
        double r = this.calcDistance(p);
        return (G*this.mass*p.mass)/Math.pow(r,2);
    }  
    public double calcForceExertedByX(Planet p){
        double f = this.calcForceExertedBy(p);
        double dis = this.calcDistance(p);
        double xf =  f*(p.xxPos - this.xxPos)/dis;
        return xf;
    }
    public double calcForceExertedByY(Planet p){
        double f = this.calcForceExertedBy(p);
        double dis = this.calcDistance(p);
        double yf =  f*(p.yyPos - this.yyPos)/dis;
        return yf;
    }
    public double calcNetForceExertedByX(Planet[] p){
        double sum = 0;
        for (Planet x : p){
            if(this.equals(x))
                continue;
            sum += this.calcForceExertedByX(x);
        }
        return sum;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double sum = 0;
        for (Planet x : p){
            if(this.equals(x))
                continue;
            sum += this.calcForceExertedByY(x);
        }
        return sum;
    }
    public void update(double dt, double fX,double fY){
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        this.xxVel += ax*dt;
        this.yyVel += ay*dt;
        this.xxPos += xxVel*dt;
        this.yyPos += yyVel*dt;
    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}
