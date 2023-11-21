package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

// Inspired by "https://docs.oracle.com/javase/8/docs/api/" and "https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html"
// UI for the bar chart
public class ChartPanelUI extends JPanel {
    private double[] values;

    private String[] names;

    private String title;

    // MODIFIES: this
    // EFFECTS: create the bar chart GUI
    public ChartPanelUI(double[] v, String[] n, String t) {
        names = n;
        values = v;
        title = t;
    }

    // MODIFIES: this
    // EFFECTS: paint the bar charts
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null || values.length == 0) {
            return;
        }
        double minValue = 0;
        double maxValue = 0;
        for (int i = 0; i < values.length; i++) {
            if (minValue > values[i]) {
                minValue = values[i];
            }
            if (maxValue < values[i]) {
                maxValue = values[i];
            }
        }

        Dimension d = getSize();
        int clientWidth = d.width;
        int clientHeight = d.height;
        int barWidth = clientWidth / values.length;

        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

        int titleWidth = titleFontMetrics.stringWidth(title);
        int y = titleFontMetrics.getAscent();
        int x = (clientWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, x, y);

        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        if (maxValue == minValue) {
            return;
        }
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        y = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);

        drawGraph(barWidth,top,scale,maxValue,g,labelFontMetrics,x,y);
    }

    // MODIFIES: this
    // EFFECTS: draw the graph of the bars
    private void drawGraph(int barWidth,int top,double scale, double maxValue, Graphics g, FontMetrics labelFontMetrics,
                           int x,int y) {
        for (int i = 0; i < values.length; i++) {
            int valueX = i * barWidth + 1;
            int valueY = top;
            int height = (int) (values[i] * scale);
            if (values[i] >= 0) {
                valueY += (int) ((maxValue - values[i]) * scale);
            } else {
                valueY += (int) (maxValue * scale);
                height = -height;
            }

            g.setColor(Color.red);
            g.fillRect(valueX, valueY, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueX, valueY, barWidth - 2, height);
            int labelWidth = labelFontMetrics.stringWidth(names[i]);
            x = i * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(names[i], x, y);
        }

    }
}

