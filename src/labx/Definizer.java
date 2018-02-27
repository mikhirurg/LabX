package labx;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mikha on 23.02.2018.
 */
public class Definizer {
  static  String   objpattrn ="var[ ]+[a-zA-Z0-9]+[ ]*[:]+[ ]*object[ ]*[(][ ]*[+-]?([0-9]*[.])?[0-9]+[ ]*[,][ ]*[+-]?([0-9]*[.])?[0-9]+[ ]*[,][ ]*[+-]?([0-9]*[.])?[0-9]+[ ]*[,][ ]*[+-]?([0-9]*[.])?[0-9]+[ ]*[,][ ]*[+-]?([0-9]*[.])?[0-9]+[ ]*[,][ ]*[+-]?([0-9]*[.])?[0-9]+[ ]*[,][ ]*[+-]?([0-9]*[.])?[0-9]+[ ]*[,][ ]*([\"][a-z]+[\"])[ ]*[)][;]";
   static String forcepattrn  = "var[ ]+[a-zA-Z0-9]+[ ]*[:]+[ ]*force[ ]*[(][ ]*[+-]?([0-9]*[.])?[0-9]+[ ]*[,][ ]*[+-]?([0-9]*[.])?[0-9]+[ ]*[)][;]";
   static String vectpattrn = "var[ ]+[a-zA-Z0-9]+[ ]*[:]+[ ]*vector[ ]*[(][ ]*([0-9])+[ ]*[,][ ]*([0-9])+[ ]*[,][ ]*([0-9])+[ ]*[)][;]";
   static String forseandobj = "setforce[(]([ ]*)[a-zA-Z0-9]+([ ]*)[,]([ ]*)[a-zA-Z0-9]+([ ]*)[)][;]";
    public static class object{
        double m;
        double x;
        double y;
        double z;
        double vx;
        double vy;
        double vz;
        double ax;
        double ay;
        double az;
        ArrayList<force> forces;
        String color;
        object(double m1,double x1,double y1,double vx1,double vy1,double ax1,double ay1,String color1){
            set(m1, x1, y1, vx1, vy1,  ax1, ay1,color1);
            forces= new ArrayList<>();
        }

        public void set(double m1,double x1,double y1,double vx1,double vy1,double ax1,double ay1,String color1) {
            m=m1;
            x=x1;
            y=y1;
           // z=z1;
            vx=vx1;
            vy=vy1;
           // vz=vz1;
            ax=ax1;
            ay=ay1;
          //  az=az1;
            color=color1;
        }
    }
    public static class force{
        double fx;
        double fy;
        force(double fx1,double fy1){
            fx=fx1;
            fy=fy1;
        }
    }
    public static class vector{
        double vx;
        double vy;
        vector(double vx1,double vy1){
            vx=vx1;
            vy=vy1;
        }
    }
 public   static HashMap<String,object> ObjMap = new HashMap<>();
   public static HashMap<String,force> ForceMap = new HashMap<>();
   public static HashMap<String,vector> VectMap = new HashMap<>();
   public static String finprog;
   public static String Define(String s){
        String str=s;
        boolean b=false;
       String ss="";


        while (!b){
            b=true;
            Pattern r = Pattern.compile(objpattrn);
            int start=str.indexOf("var");
            int end = str.indexOf(';');
            if (start!=-1 && end!=-1) {
                 ss = str.substring(start, end + 1);
              Matcher  ma = r.matcher(ss);
                if (ma.matches()) {
                    str = str.replace(ss, " ");
                    str=str.replace("\n","");
                    ss = ss.replace(",", " ");
                    ss = ss.replace(";", " ");
                    ss = ss.replace("(", " ");
                    ss = ss.replace(")", " ");
                    ss = ss.replace(":", " ");
                    ss = ss.replace("var", " ");
                    ss = ss.replace("object", " ");
                    //ss=ss.replace("\""," ");
                    StringTokenizer tok = new StringTokenizer(ss);
                    String name = tok.nextToken();
                    double m = Double.valueOf(tok.nextToken());
                    double x = Double.valueOf(tok.nextToken());
                    double y = Double.valueOf(tok.nextToken());
                    //int z = Integer.valueOf(tok.nextToken());
                    double vx = Double.valueOf(tok.nextToken());
                    double vy = Double.valueOf(tok.nextToken());
                   // int vz = Integer.valueOf(tok.nextToken());
                    double ax =Double.valueOf(tok.nextToken());
                    double ay =Double.valueOf(tok.nextToken());
                   // int az = Integer.valueOf(tok.nextToken());
                    String color = tok.nextToken();
                    object ob1 = new object(m, x, y, vx, vy,  ax, ay,color);
                    ObjMap.put(name, ob1);
                    b = false;

                }
            }
            r = Pattern.compile(forcepattrn);
            start=str.indexOf("var");
            end = str.indexOf(';',start);
            if (start!=-1 && end!=-1) {
                ss = str.substring(start,end + 1);
             Matcher   ma = r.matcher(ss);
                if (ma.matches()){
                    str =str.replace(ss," ");
                    str=str.replace("\n","");
                    ss=ss.replace(","," ");
                    ss=ss.replace(";"," ");
                    ss=ss.replace("("," ");
                    ss=ss.replace(")"," ");
                    ss=ss.replace(":"," ");
                    ss=ss.replace("var"," ");
                    ss=ss.replace("force"," ");
                    StringTokenizer tok = new StringTokenizer(ss);
                    String name=tok.nextToken();
                    double fx=Double.valueOf(tok.nextToken());
                    double fy=Double.valueOf(tok.nextToken());
                    force for1= new force(fx,fy);
                    ForceMap.put(name,for1);
                    b=false;

                }
            }

             r = Pattern.compile(vectpattrn);
            start=str.indexOf("var");
            end = str.indexOf(';',start);
            if (start!=-1 && end!=-1) {
                ss = str.substring(start,end + 1);
              Matcher  ma = r.matcher(ss);
                if (ma.matches()) {
                    str = str.replace(ss, " ");
                    str=str.replace("\n","");
                    ss = ss.replace(",", " ");
                    ss = ss.replace(";", " ");
                    ss = ss.replace("(", " ");
                    ss = ss.replace(")", " ");
                    ss = ss.replace(":", " ");
                    ss = ss.replace("var", " ");
                    ss = ss.replace("vector", " ");
                    StringTokenizer tok = new StringTokenizer(ss);
                    String name = tok.nextToken();
                    Double vx = Double.valueOf(tok.nextToken());
                    Double vy = Double.valueOf(tok.nextToken());
                    vector vect1 = new vector(vx, vy);
                    VectMap.put(name, vect1);
                    b = false;

                }
            }

            r=Pattern.compile(forseandobj);
            start =str.indexOf("setforce");
             end= str.indexOf(";",start);


            if (start!=-1 && end!=-1){
                ss =str.substring(start,end+1);
                Matcher ma = r.matcher(ss);
                if (ma.matches()) {
                    str=str.replace(ss," ");
                    ss=ss.replace("setforce"," ");
                    ss=ss.replace("("," ");
                    ss=ss.replace(")"," ");
                    ss=ss.replace(";"," ");
                    ss=ss.replace(","," ");
                    StringTokenizer tok = new StringTokenizer(ss);
                    String object = tok.nextToken();
                    String force = tok.nextToken();
                    ObjMap.get(object).forces.add(ForceMap.get(force));
                    b = false;
                }
            }
        }

        String js="";
       for (int i=0;i<ForceMap.values().size();i++){
           force f = (force) ForceMap.values().toArray()[i];
           String name = (String) ForceMap.keySet().toArray()[i];
           String s1="var "+ name +"= new Object; "+name+".fx="+f.fx+"; "+name+".fy="+f.fy+"; ";
           js+="\n"+s1;
       }
        for (int i=0;i<ObjMap.values().size();i++){
            object o = (object) ObjMap.values().toArray()[i];
            String name = (String) ObjMap.keySet().toArray()[i];
            String s1="var "+ name +"= new Object; "+name+".m="+o.m+"; "+name+".x="+o.x+"; "+name+".y="+o.y+"; "+name+".vx="+o.vx+"; "+name+".vy="+o.vy+"; "+name+".ax="+o.ax+"; "+name+".ay="+o.ay+"; "+name+".color="+o.color+"; ";
            js+="\n"+s1;
            double fx=0;
            double fy=0;
            double fz=0;
            for (int j=0;j<o.forces.size();j++){
                fx+=o.forces.get(j).fx;
                fy+=o.forces.get(j).fy;
            }
            String s2=name+".ax="+name+".ax+"+fx/o.m+"; "+name+".ay="+name+".ay+"+fy/o.m+"; ";
            js+="\n"+s2;
        }

        js+= "var _d = new Date();\n" +
                "var _time = _d.getTime();";
        finprog=js+ "while (Running.get()) {"+
                "TIME = _dt;";
       //StringWriter.getBuffer().toString()

       finprog+="TUNIT.sleep(100); sw.flush(); pane.setText(sw.getBuffer()); pane.revalidate();";

       String js2="_d = new Date();\n" +
               "var _dt = (_d.getTime() - _time)/1000.0;\n" +
               "var _dt205 = _dt*_dt/0.5;\n" +
               "_time = _d.getTime();\n"+str;
       for (int i=0;i<ObjMap.values().size();i++){
           object o = (object) ObjMap.values().toArray()[i];
           String name = (String) ObjMap.keySet().toArray()[i];

           js2 += name + ".x += " + name + ".vx*_dt +" + name + ".ax*_dt205;\n";
           js2 += name + ".y += " + name + ".vy*_dt +" + name + ".ay*_dt205;\n";
          /* js2 += name + ".z += " + name + ".vz*_dt +" + name + ".az*_dt205;\n";*/
           js2 += name + ".vx += " + name + ".ax*_dt;\n";
           js2 += name + ".vy += " + name + ".ay*_dt;\n";
          /* js2 += name + ".vz += " + name + ".az*_dt;\n";*/
           String s1="ObjMap.get(\""+name+"\").set("+name+".m, " + name + ".x,"+name+".y,"+name+".vx,"+name+".vy," + name + ".ax, "+name+".ay," +name+".color); ";
           js2+="\n"+s1;
       }
       finprog+=js2+" lab.updatePanel();}";

        return str;
    }
}
