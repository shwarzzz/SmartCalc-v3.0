package edu.school21.calculator.presenter;

import edu.school21.calculator.model.CalcModel;
import edu.school21.calculator.serializer.Expression;
import edu.school21.calculator.serializer.ExpressionHistory;
import edu.school21.calculator.serializer.HistorySerializer;
import edu.school21.calculator.view.CalcView;

import java.io.File;

public class CalcPresenter {
    private final CalcModel model;
    private final CalcView view;
    private HistorySerializer serializer;
    private ExpressionHistory history;

    public CalcPresenter(CalcModel model, CalcView view) {
        this.model = model;
        this.view = view;
        loadHistory();
    }

    public void addSymbol(String buttonText) {
        view.setExpression(view.getExpression() + buttonText);
    }

    public void addFunction(String buttonText) {
        view.setExpression(view.getExpression() + buttonText + "(");
    }

    public void clearAll() {
        view.clearAllFields();
    }

    public void calculate() {
        double xValue = view.getXValue();
        String expressionText = view.getExpression();
        Expression newExpression = new Expression(expressionText, xValue);
        model.SetX(xValue);
        model.SetInputString(expressionText);
        model.Calculate();
        view.setResultField(model.GetResultString());
        history.addData(newExpression);
        view.addExpressionRecord(newExpression);
    }

    public void plotGraph() {
        double xMin = view.getAxisXMin();
        double xMax = view.getAxisXMax();
        double yMin = view.getAxisYMin();
        double yMax = view.getAxisYMax();
        model.SetInputString(view.getExpression());
        model.SetX(view.getXValue());
        model.FindDotsToPlot(xMin, xMax, yMin, yMax);
        if (model.IsGraphDataCorrect()) {
            view.setGraphScope(xMin, xMax, yMin, yMax);
            view.createGraph(model.GetXDotsVector(), model.GetYDotsVector());
            return;
        }
        view.showErrorAlert("Graph Error", model.GetPlotStatusString());
    }

    public void saveHistory() {
        serializer.saveHistory(history);
    }

    public void clearHistory() {
        serializer.clearHistory();
        history.clear();
        view.clearHistoryTable();
    }

    public void setSelectedExpression() {
        Expression selected = view.getSelectedExpression();
        view.setExpression(selected.getExpressionText());
        view.setXValue(selected.getXValue());
    }

    public void showInfo() {
        view.showInfoStage();
    }

    private void loadHistory() {
        File historyFile = new File("./history.ser");
        serializer = new HistorySerializer(historyFile.getAbsolutePath());
        history = serializer.getExpressionHistory();
        view.loadHistory(history);
    }
}