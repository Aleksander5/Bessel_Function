package besselapplication;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class Controller {

    public Button drawButton;
    public Button higherOrderButton;
    public Button clearButton;
    public Label actualOrderLabel;
    public Label commentLabel;
    public TextField enterOrderTextField;
    public StackPane chartPane;

    private double insertedOrder = 0;
    private double actualOrder = 0;

    public void drawChart(ActionEvent actionEvent){
        prepareAppWindow();

        try {
            insertedOrder = Integer.parseInt(enterOrderTextField.getText());

            if(insertedOrder < 0){
                commentLabel.setText("Podana wartość musi być nieujemna");
            }
            else {
                actualOrder = insertedOrder;
                actualOrderLabel.setText(String.format("%.0f", insertedOrder));

                SwingNode chartNode = ChartDrawer.createSwingNodeChart(insertedOrder);
                chartPane.getChildren().add(chartNode);
            }
        }
        catch (NumberFormatException ex) {
            commentLabel.setText("Podana wartość nie jest liczbą lub nie jest liczbą całkowitą");
        }
    }

    public void drawChartSequence(ActionEvent actionEvent) {
        prepareAppWindow();

        actualOrder++;

        actualOrderLabel.setText(String.format("%.0f", actualOrder));

        SwingNode chartNode = ChartDrawer.createSwingNodeChart(insertedOrder, actualOrder);
        chartPane.getChildren().add(chartNode);
    }

    public void clearChart(ActionEvent actionEvent) {
        chartPane.getChildren().clear();
        enterOrderTextField.setText("");
        actualOrderLabel.setText("");
        commentLabel.setText("");
        actualOrder = 0;
    }

    private void prepareAppWindow(){
        chartPane.getChildren().clear();
        commentLabel.setWrapText(true);
        commentLabel.setText("");
    }

}
