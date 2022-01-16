public class NBody {
    public static double readRadius(String path){
    	In in = new In(path);
    	in.readInt();
    	return in.readDouble();
    }
    public static Planet[] readPlanets(String path){
    	In in = new In(path);
    	int num = in.readInt();
    	Planet[] planets = new Planet[num];
    	in.readDouble();
    	for(int i = 0;i<num;i++){
    		double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planets[i] = p;
    	}
    	return planets;
    	}
    	public static void main(String[] args){
    		double T = Double.parseDouble(args[0]);
    		double dt = Double.parseDouble(args[1]);
    		String filename = args[2];
    		
    	}
}
