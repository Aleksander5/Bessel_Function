package besselapplication;

import javafx.embed.swing.SwingNode;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.List;

public class ChartDrawer {

    private static XYDataset createDataset(double order){

        List<Double> functionValues = BesselCalculation.calculateBesselFunctionValues(order);

        XYSeries besselFunctionFlow = new XYSeries(String.format("Rząd: " + order));
        XYSeries xAxis = new XYSeries("OX");

        for(int i=0; i<functionValues.size(); i++) {
                xAxis.add(i*0.2, 0);
                besselFunctionFlow.add(i * 0.2, functionValues.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(xAxis);
        dataset.addSeries(besselFunctionFlow);

        return dataset;
    }

    private static XYDataset createSequenceDataset(double startingOrder, double endingOrder){

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries xAxis = new XYSeries("OX");
        xAxis.add(0,0);
        xAxis.add(40, 0);
        dataset.addSeries(xAxis);

        for(double i=startingOrder; i<=endingOrder; i++){
            List<Double> functionValues = BesselCalculation.calculateBesselFunctionValues(i);
            XYSeries besselFunctionFlow = new XYSeries(String.format("Rząd: " + i));

            for(int j=0; j<functionValues.size(); j++)
                besselFunctionFlow.add(j * 0.2, functionValues.get(j));

            dataset.addSeries(besselFunctionFlow);
        }

        return dataset;
    }

    private static JFreeChart createChart(XYDataset dataset){
        JFreeChart chart = ChartFactory.createXYLineChart("Przebieg funkcji Bessela",
                                                        "X", "Jn(X)",
                                                            dataset, PlotOrientation.VERTICAL
                                                        ,true, true, false);
        chart.setBackgroundPaint(Color.LIGHT_GRAY);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);

        return chart;
    }

    public static SwingNode createSwingNodeChart(double order){

        JFreeChart chart = createChart(createDataset(order));

        SwingNode swingNodeChart = new SwingNode();
        swingNodeChart.setContent(new ChartPanel(chart));

        return swingNodeChart;
    }

    public static SwingNode createSwingNodeChart(double startingOrder, double endingOrder){

        JFreeChart chart = createChart(createSequenceDataset(startingOrder, endingOrder));

        SwingNode swingNodeChart = new SwingNode();
        swingNodeChart.setContent(new ChartPanel(chart));

        return swingNodeChart;
    }
}
