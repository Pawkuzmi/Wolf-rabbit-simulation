package mmm2;

import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jfree.chart.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author PawełKuźmicki
 */
public class Chart {
    
    String title;
    XYSeriesCollection data;
    
    public Chart(XYSeries wolves, XYSeries rabbits){
        
        data = new XYSeriesCollection();
        
        data.addSeries(wolves);
        data.addSeries(rabbits);
        
        title = "Model populacji wilków i zajęcy";
        
    }
    
    public void createChart(JLabel chartLabel, String xAxis, String yAxis, String fileName){
        JFreeChart chart = ChartFactory.createXYLineChart(title, xAxis, yAxis, data);
        File file = new File(fileName);
        file.deleteOnExit();
        
        try {
            ChartUtilities.saveChartAsJPEG(file, chart, chartLabel.getWidth(), chartLabel.getHeight());
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog (null, "Nie można stworzyć wykresu", "Błąd", JOptionPane.WARNING_MESSAGE);
        }
    }
}
