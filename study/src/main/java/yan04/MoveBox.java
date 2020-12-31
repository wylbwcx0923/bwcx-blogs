package yan04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveBox extends JFrame implements KeyListener, ActionListener {
    Label label = new Label("单击一个积木，然后移动它");
    Button b[] = new Button[40];
    int x[] = new int[40];
    int y[] = new int[40];
    int k1 = 70, k2 = 70, k3 = 70, k4 = 70;
    String s = "";

    public MoveBox() {
        super("Move Box");
        setLayout(null);
        add(label);
        label.setBounds(0, 0, 150, 30);
        for (int i = 0; i < 40; i++) {
            b[i] = new Button(String.valueOf(i));
            if (i % 3 == 0) b[i].setBackground(Color.red);
            if (i % 3 == 1) b[i].setBackground(Color.blue);
            if (i % 3 == 2) b[i].setBackground(Color.yellow);
            b[i].addKeyListener(this);
            b[i].addActionListener(this);
            add(b[i]);
            if (i % 4 == 0) {
                b[i].setBounds(k1, 40, 30, 30);
                k1 = k1 + 31;
            } else if (i % 4 == 1) {
                b[i].setBounds(k1, 71, 30, 30);
                k2 = k2 + 31;
            } else if (i % 4 == 2) {
                b[i].setBounds(k1, 102, 30, 30);
                k2 = k3 + 31;
            } else if (i % 4 == 3) {
                b[i].setBounds(k1, 133, 30, 30);
                k2 = k4 + 31;
            }
        }
        for (int i = 0; i < 40; i++) {
            x[i] = b[i].getBounds().x;
            y[i] = b[i].getBounds().y;
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 40; i++) {
            if (e.getSource() == b[i])
                s = b[i].getLabel();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            for (int i = 0; i <= 39; i++) {
                if (s.equals(String.valueOf(i))) {
                    y[i] = y[i] - 2;
                    if (y[i] <= 0) y[i] = 0;
                    b[i].setLocation(x[i], y[i]);
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            for (int i = 0; i <= 39; i++) {
                if (s.equals(String.valueOf(i))) {
                    y[i] = y[i] + 2;
                    if (y[i] >= 280) y[i] = 280;
                    b[i].setLocation(x[i], y[i]);
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            for (int i = 0; i <= 39; i++) {
                if (s.equals(String.valueOf(i))) {
                    x[i] = x[i] - 2;
                    if (x[i] <= 0) x[i] = 0;
                    b[i].setLocation(x[i], y[i]);
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    public static void main(String args[]) {
        new MoveBox();
    }

}
