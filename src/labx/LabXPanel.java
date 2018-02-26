package labx;
import javax.script.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


/**
 * Created by mikha on 22.02.2018.
 */
public class LabXPanel extends JPanel {
    int num=0;
    LabXPanel(int num){
        this.num=num;
    }
    TimerTask tt = new TimerTask() {

        @Override
        public void run() {
            // тут наш код
        }
    };

    public class engineThread extends Thread{
        public void run(){

             sw = new StringWriter();
            b.put("sw",sw);
            engine.getContext().setWriter(sw);
            try {
                engine.eval(finproj,b);
                engine.getContext().getWriter().flush();
                paintComponent(WIZARD.ide.get(num).labXPanel.getGraphics());
            } catch (ScriptException e) {
                e.printStackTrace();
                WIZARD.ide.get(num).pane.setText(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }

            out=sw.toString();

        }
    }
    class repaintThread extends Thread{
        LabXPanel panel;
        repaintThread(LabXPanel l2){
            panel=l2;
        }
        public void run(){
          panel.paintComponent(panel.getGraphics());
        }
    }
    String predcode;
    String maincode;
String finproj;
  String out;
  engineThread t;
  repaintThread t2;

    StringWriter sw = new StringWriter();
    ScriptEngine engine= new ScriptEngineManager().getEngineByName("js");
    Bindings b = new SimpleBindings();
    void process(String s) throws ScriptException, IOException {
        s=Definizer.Define(s);

        paintComponent(this.getGraphics());
        finproj=Definizer.finprog;
        System.out.println(finproj);
        engine.getContext().setWriter(sw);
        b= new SimpleBindings();

        b.put("TUNIT", TimeUnit.MILLISECONDS);
        b.put("pane",WIZARD.ide.get(num).pane);
        WIZARD.ide.get(num).start=true;
        b.put("END",WIZARD.ide.get(num).start);
        b.put("ObjMap",Definizer.ObjMap);
        b.put("lab",WIZARD.ide.get(num).labXPanel);


        t = new engineThread();
        t.start();

        t2= new repaintThread(this);
        t2.start();

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Color c = g.getColor();
        g.setColor(Color.white);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(c);
        for (int i=0;i<Definizer.ObjMap.values().size();i++){
          Definizer.object o = (Definizer.object) Definizer.ObjMap.values().toArray()[i];
          g.draw3DRect(o.x,o.y,100,100,true);
        }

    }
}
