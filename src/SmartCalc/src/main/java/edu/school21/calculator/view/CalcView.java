package edu.school21.calculator.view;

import edu.school21.calculator.model.CalcModel;
import edu.school21.calculator.model.Vector;
import edu.school21.calculator.presenter.CalcPresenter;
import edu.school21.calculator.serializer.Expression;
import edu.school21.calculator.serializer.ExpressionHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class CalcView implements Initializable {
    private final short MAX_EXPRESSION_LENGTH = 255;
    private final int X_AXIS_MIN = -1000000;
    private final int X_AXIS_MAX = 1000000;
    private final int Y_AXIS_MIN = -1000000;
    private final int Y_AXIS_MAX = 1000000;

    private CalcPresenter presenter;
    private List<Button> simpleButtonsList;
    private List<Button> functionsButtonsList;
    private UnaryOperator<TextFormatter.Change> spinnersFormatter;
    private StringConverter<Double> spinnersConverter;
    private EventHandler<WindowEvent> closeEventHandler;
    private Expression selectedExpression;
    private Stage infoStage;

    @FXML
    private Button buttonNum1;
    @FXML
    private Button buttonNum2;
    @FXML
    private Button buttonNum3;
    @FXML
    private Button buttonNum4;
    @FXML
    private Button buttonNum5;
    @FXML
    private Button buttonNum6;
    @FXML
    private Button buttonNum7;
    @FXML
    private Button buttonNum8;
    @FXML
    private Button buttonNum9;
    @FXML
    private Button buttonNum0;
    @FXML
    private Button buttonDot;
    @FXML
    private Button buttonE;
    @FXML
    private Button buttonX;
    @FXML
    private Button buttonLeftBracket;
    @FXML
    private Button buttonRightBracket;
    @FXML
    private Button buttonDiv;
    @FXML
    private Button buttonMod;
    @FXML
    private Button buttonMul;
    @FXML
    private Button buttonMinus;
    @FXML
    private Button buttonPlus;
    @FXML
    private Button buttonPow;
    @FXML
    private Button buttonSin;
    @FXML
    private Button buttonCos;
    @FXML
    private Button buttonTan;
    @FXML
    private Button buttonAsin;
    @FXML
    private Button buttonAcos;
    @FXML
    private Button buttonAtan;
    @FXML
    private Button buttonLog;
    @FXML
    private Button buttonLn;
    @FXML
    private Button buttonSqrt;
    @FXML
    private Button buttonClear;
    @FXML
    private Button buttonCalculate;
    @FXML
    private Button buttonPlot;
    @FXML
    private Button buttonClearHistory;
    @FXML
    private TextField expressionField;
    @FXML
    private TextField resultField;
    @FXML
    private Spinner<Double> xValueSpinner;
    @FXML
    private Spinner<Double> xMinSpinner;
    @FXML
    private Spinner<Double> xMaxSpinner;
    @FXML
    private Spinner<Double> yMinSpinner;
    @FXML
    private Spinner<Double> yMaxSpinner;
    @FXML
    private LineChart<Number, Number> functionGraph;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private TableView<Expression> historyTable;
    @FXML
    private TableColumn<Expression, String> expressionColumn;
    @FXML
    private TableColumn<Expression, Double> xColumn;
    @FXML
    private MenuItem aboutItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        presenter = new CalcPresenter(new CalcModel(), this);
        closeEventHandler = event -> presenter.saveHistory();
        addListenerToExpressionField();
        setHandlers();
        setUpSpinners();
        setUpHistoryTable();
        prepareInfoStage();
    }

    public EventHandler<WindowEvent> getCloseEventHandler() {
        return closeEventHandler;
    }

    public void clearAllFields() {
        expressionField.clear();
        resultField.clear();
        functionGraph.getData().clear();
    }

    public void setResultField(String data) {
        resultField.setText(data);
    }

    public String getExpression() {
        return expressionField.getText();
    }

    public void setExpression(String data) {
        expressionField.setText(data);
    }

    public Double getXValue() {
        return xValueSpinner.getValue();
    }

    public void setXValue(Double xValue) {
        xValueSpinner.getValueFactory().setValue(xValue);
    }

    public Double getAxisXMax() {
        return xMaxSpinner.getValue();
    }

    public Double getAxisXMin() {
        return xMinSpinner.getValue();
    }

    public Double getAxisYMax() {
        return yMaxSpinner.getValue();
    }

    public Double getAxisYMin() {
        return yMinSpinner.getValue();
    }

    public Expression getSelectedExpression() {
        return selectedExpression;
    }

    public void setGraphScope(double xMin, double xMax, double yMin, double yMax) {
        xAxis.setLowerBound(xMin);
        xAxis.setUpperBound(xMax);
        yAxis.setLowerBound(yMin);
        yAxis.setUpperBound(yMax);
    }

    public void showErrorAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void createGraph(Vector.DoubleVector xAxis, Vector.DoubleVector yAxis) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        functionGraph.getData().clear();
        ObservableList<XYChart.Data<Number, Number>> graphData =
                FXCollections.observableArrayList();
        for (int i = 0; i < xAxis.size(); i++) {
            graphData.add(new XYChart.Data<>(xAxis.get(i), yAxis.get(i)));
        }
        series.setData(graphData);
        functionGraph.getData().add(series);
    }

    public void addExpressionRecord(Expression expression) {
        historyTable.getItems().add(expression);
    }

    public void loadHistory(ExpressionHistory history) {
        ObservableList<Expression> data =
                FXCollections.observableArrayList(history.getExpressions());
        historyTable.setItems(data);
    }

    public void clearHistoryTable() {
        historyTable.getItems().clear();
    }

    public void showInfoStage() {
        infoStage.show();
    }

    private void setHandlers() {
        simpleButtonsList = new LinkedList<>(Arrays.asList(buttonNum0, buttonNum1,
                buttonNum2, buttonNum3, buttonNum4, buttonNum5, buttonNum6,
                buttonNum7, buttonNum8, buttonNum9, buttonDot, buttonX,
                buttonE, buttonDiv, buttonMod, buttonMul, buttonPlus,
                buttonMinus, buttonLeftBracket, buttonRightBracket, buttonPow));
        for (Button currentButton : simpleButtonsList) {
            currentButton.setOnAction(event ->
                    presenter.addSymbol(currentButton.getText()));
        }
        functionsButtonsList = new LinkedList<>(Arrays.asList(buttonSin,
                buttonCos, buttonTan, buttonAsin, buttonAcos, buttonAtan,
                buttonLog, buttonLn, buttonSqrt));
        for (Button currentButton : functionsButtonsList) {
            currentButton.setOnAction(event ->
                    presenter.addFunction(currentButton.getText()));
        }
        buttonClear.setOnAction(event -> presenter.clearAll());
        buttonCalculate.setOnAction(event -> presenter.calculate());
        buttonPlot.setOnAction(event -> presenter.plotGraph());
        buttonClearHistory.setOnAction(event -> presenter.clearHistory());
        aboutItem.setOnAction(event -> presenter.showInfo());
    }

    private void setUpSpinners() {
        createDoubleFormatterAndConverter();
        setUpSpinner(xValueSpinner, -Double.MAX_VALUE, Double.MAX_VALUE, 0.0, 1.0);
        setUpSpinner(xMinSpinner, X_AXIS_MIN, X_AXIS_MAX, 0.0, 1.0);
        setUpSpinner(xMaxSpinner, X_AXIS_MIN, X_AXIS_MAX, 0.0, 1.0);
        setUpSpinner(yMinSpinner, Y_AXIS_MIN, Y_AXIS_MAX, 0.0, 1.0);
        setUpSpinner(yMaxSpinner, Y_AXIS_MIN, Y_AXIS_MAX, 0.0, 1.0);
    }

    private void setUpSpinner(Spinner<Double> spinner, double min,
                              double max, double defaultValue, double step) {
        SpinnerValueFactory<Double> spinnerFactory = new SpinnerValueFactory
                .DoubleSpinnerValueFactory(min, max, defaultValue, step);
        spinner.setValueFactory(spinnerFactory);
        spinnerFactory.setConverter(spinnersConverter);
        spinner.getEditor()
                .setTextFormatter(new TextFormatter<>(spinnersConverter,
                        defaultValue, spinnersFormatter));
        spinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue) {
                        String textValue = spinner.getEditor().getText();
                        Double value = spinner.getValueFactory()
                                .getConverter().fromString(textValue);
                        spinner.getValueFactory().setValue(value);
                    }
                }
        );
    }

    private void createDoubleFormatterAndConverter() {
        spinnersFormatter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^-?\\d+\\.\\d+$")) {
                return change;
            }
            return null;
        };
        spinnersConverter = new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                return object.toString();
            }

            @Override
            public Double fromString(String string) {
                if (string.isEmpty() || ".".equals(string) || "-".equals(string) || "-.".equals(string)) {
                    return 0.0;
                } else {
                    return new Double(string);
                }
            }
        };
    }

    private void addListenerToExpressionField() {
        expressionField.lengthProperty().addListener((observableValue, number, t1) -> {
            if (expressionField.getText().length() > MAX_EXPRESSION_LENGTH) {
                expressionField.setText(expressionField.getText(0, MAX_EXPRESSION_LENGTH));
            }
        });
    }

    private void setUpHistoryTable() {
        expressionColumn.setCellValueFactory(new PropertyValueFactory<>("expressionText"));
        xColumn.setCellValueFactory(new PropertyValueFactory<>("xValue"));
        TableView.TableViewSelectionModel<Expression> selectionModel
                = historyTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((observableValue, expression, newVal) -> {
            if (newVal != null) {
                selectedExpression = newVal;
                presenter.setSelectedExpression();
            }
        });
    }

    private void prepareInfoStage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/info.fxml"));
            Parent root = loader.load();
            infoStage = new Stage();
            infoStage.setTitle("Info about application");
            infoStage.setScene(new Scene(root));
        } catch (IOException ignored) {

        }
    }
}
