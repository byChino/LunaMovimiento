import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Area;
import java.awt.GradientPaint;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Luna extends JFrame {
    private int x1 = 130, x2 = 150, ancho = 100, largo = 100;
    private JPanel panel;

    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Luna frame = new Luna();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // clase JFrame
    public Luna() {
        // JFrame o lieanzo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Luna Tranlacion y Escalacion");
        setSize(300, 400);
        setLocationRelativeTo(null);
        agregarPanel();
    }// fin de la clase JFrame

    public void agregarPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
        panel.setFocusable(true);
        eventosDelTeclado();
    }

    // clase de eventos de las flechas del teclado
    private void eventosDelTeclado() {
        KeyListener eventoTeclado = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Movemos la luna hacia el lado izquierdo y disminuimos los valores que tiene
                // x1 y x2
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    x1 -= 10;
                    x2 -= 10;

                }
                // Movemos la luna hacia el lado derecho y aumentados los valores que tiene x1 y
                // x2
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    x1 += 10;
                    x2 += 10;
                }
                // Aumnetamos el tamaño de la luna y aumentados los valores que tiene ancho y
                // largo
                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    ancho += 10;
                    largo += 10;
                }
                // Disminuimos el tamaño de la luna y restamos los valores que tiene ancho y
                // largo
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    ancho -= 10;
                    largo -= 10;
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        panel.addKeyListener(eventoTeclado);

    }// fin de la clase de eventos

    // creamos la luna y un fondo con degradado de azul a rosa
    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        // Crear un gradiente para el fondo
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, getWidth(), 0, new Color(128, 0, 128));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar la luna con elipses
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.YELLOW);

        Ellipse2D e1 = new Ellipse2D.Double(x1, getHeight() / 2 - 50, ancho, largo);

        Ellipse2D e2 = new Ellipse2D.Double(x2, getHeight() / 2 - 50, ancho, largo);
        // creamos las areas de las elipses
        Area a1 = new Area(e1);
        Area a2 = new Area(e2);
        a1.subtract(a2); // sustraemos una parte de las elipses para tener la forma de una luna
        g2.fill(a1);
        repaint();// Repitamos el dibujo con los nuevos datos
        g2.dispose();
    }

}