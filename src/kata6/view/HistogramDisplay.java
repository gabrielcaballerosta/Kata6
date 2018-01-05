package kata6.view;

import java.awt.Dimension;
import javax.swing.JPanel;
import kata6.model.Histogram;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public final class HistogramDisplay<T> extends ApplicationFrame {
    private final Histogram<T> histogram;
    private final String labelEjeX;
    
    public HistogramDisplay(Histogram<T> histogram, String name) {
        super("HISTOGRAMA");
        this.histogram = histogram;
        this.labelEjeX = name;
        setContentPane(createPanel());
        pack();
    }
    
    private JPanel createPanel() {
        ChartPanel panel = new ChartPanel(createChart(createDataset()));
        panel.setPreferredSize(new Dimension(500,400));
        return panel;
    }
    
    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart jfc = 
                ChartFactory.createBarChart(
                "JFreeChart",
                labelEjeX,
                "Numero",
                dataset, 
                PlotOrientation.VERTICAL,
                false,
                rootPaneCheckingEnabled,
                rootPaneCheckingEnabled);
        return jfc;
    }
    
    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (T key : histogram.keySet()) {
            dataset.addValue(histogram.get(key),"", (Comparable) key);
        }
        return dataset;
    }
    
    public void excute() {
        setVisible(true);
    }   
}
