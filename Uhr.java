import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;



/**
 * Created by ${perdu} on ${16.06.16}.
 */
 public class Uhr extends JFrame {


    private static final int N = 60;
    private final ClockListener cl = new ClockListener();  //Intitalisierung eines Clocklistener Objects
    private final Timer t = new Timer(1000, cl);  // 1000 wegen der Nanotime des Systems
    private final JTextField tf = new JTextField(5);     // 5 hat das feld
    public boolean restart;
    public boolean stop;
    public boolean start;
    public String X;
    int integer = 0;


    public void start() {
        t.start();
        start = true;
    }

    public void stop() {
               t.stop();
        stop = true;
    }
    public void restart() {
        t.stop();
        System.out.println("44");
        t.start();
        restart = true;
    }



    private class ClockListener implements ActionListener {     // ein Clocklistener der einen Actionlistener hat


        private int minutes;
        private int seconds;
        private String minute;
        private String second;


        @Override               // das Actionenvent muss von uns definiert werden
        public void actionPerformed(ActionEvent e) {

            NumberFormat formatter = new DecimalFormat("00");
            if (seconds == N) {
                seconds = 0;
                minutes++;
            }

            if (minutes == N) {
                minutes = 0;

            }

            minute = formatter.format(minutes);
            second = formatter.format(seconds);
            tf.setText(String.valueOf(minute + ":" + second));
            seconds++;




        }
    }


    public void setStart(boolean start) {
        this.start = start;
    }

   public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;}


    public boolean isStart() {
        return start;
    }

    public boolean isRestart() {
        return restart;
    }

    public void setRestart(boolean restart) {
        this.restart = restart;
    }

    public Timer getT() {       // da der start immer bei 0 ist :)
        return t;
    }

    public JTextField getTf() {  // zum ausgeben auf den Button
        return tf;
    }

    public ClockListener getCl() {
        return cl;
    }

    public Uhr() {          // innere Klasse
        t.setInitialDelay(0);

        JPanel panel = new JPanel();
        tf.setHorizontalAlignment(JTextField.RIGHT);
        tf.setEditable(false);
        panel.add(tf);
        this.setSize(30,220);

        this.getContentPane().setLayout(new java.awt.FlowLayout());
        this.getContentPane().add(tf);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocation(1400,600);
        this.setVisible(true);

    }
}










