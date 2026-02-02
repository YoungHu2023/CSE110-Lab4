package edu.ucsd.spendingtracker.view.charts;
import java.util.Map;

import edu.ucsd.spendingtracker.model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;

public class PieChartProvider implements IChartProvider { 
    @Override
    public Node createChart (Map<Category, Double> data) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        PieChart chart = new PieChart(pieChartData);
        data.forEach((cat, sum) -> {
            chart.getData().add(new PieChart.Data(cat.name(), sum));
        });
        chart.setLegendVisible(false);
        
        for (PieChart.Data entry: chart.getData()) { 
            String color = Category.valueOf(entry.getName()).color;
            Node bar = entry.getNode();
            if (bar != null) {
                bar.setStyle("-fx-pie-color: " + color + ";");
            }
        }
        return chart;
    }
    @Override
    public String getDisplayName() {
        return "Pie Chart";
    }
}