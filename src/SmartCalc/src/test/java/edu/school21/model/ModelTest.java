package edu.school21.model;


import edu.school21.calculator.model.CalcModel;
import edu.school21.calculator.model.Data;
import edu.school21.calculator.model.StackWrapper;
import edu.school21.helper.DataHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class ModelTest {
    static CalcModel model;
    private final double EPSILON = 0.0000001;
    private final String WRONG_INPUT_MESSAGE = "Error: Wrong input!";
    private final String DIV_BY_ZERO_MESSAGE = "Error: Div by zero!";
    private final String CALCULATION_ERROR_MESSAGE = "Error: Calculation error!";

    @BeforeAll
    static void initModel() {
        model = new CalcModel();
        DataHelper.initWrappers();
    }

    @AfterAll
    static void closeModel() {
        model.close();
        DataHelper.closeWrappers();
    }

    //FIXME: Inject test for stack
    @ParameterizedTest(name = "Test {index} with expression {0}")
    @CsvFileSource(resources = "/correctData.csv")
    void calculateCorrectExpressions(String expression, double xValue, double result) {
        model.SetInputString(expression);
        model.SetX(xValue);
        model.Calculate();
        double calculatedValue = model.GetResultOfCalculation();
        Assertions.assertTrue(Math.abs(calculatedValue - result) < EPSILON);
    }

    @ParameterizedTest(name = "Test {index} with incorrect expression {0}")
    @CsvFileSource(resources = "/incorrectExpressions.csv")
    void calculateIncorrectExpression(String expression, double xValue) {
        model.SetInputString(expression);
        model.SetX(xValue);
        model.Calculate();
        Assertions.assertEquals(model.GetResultString(), WRONG_INPUT_MESSAGE);
    }

    @ParameterizedTest(name = "Test {index} - expression {0} with calculation error")
    @CsvFileSource(resources = "/calculationError.csv")
    void calculateCalculationError(String expression, double xValue) {
        model.SetInputString(expression);
        model.SetX(xValue);
        model.Calculate();
        Assertions.assertEquals(model.GetResultString(), CALCULATION_ERROR_MESSAGE);
    }


    @ParameterizedTest(name = "Test {index} - expression {0} with division by zero")
    @CsvFileSource(resources = "/divisionByZero.csv")
    void calculateDivisionByZero(String expression, double xValue) {
        model.SetInputString(expression);
        model.SetX(xValue);
        model.Calculate();
        Assertions.assertEquals(model.GetResultString(), DIV_BY_ZERO_MESSAGE);
    }

    @ParameterizedTest(name = "Test {index} - expression {0} converted to Polish")
    @CsvSource({"cos(12)/sqrt(9)-atan(x)*(-sin(-x))+23*ln(5.23)^2, 0",
            "tan(asin(acos(0.98)))+log(2)+ln(0.5), 1"})
    void convertToPolish(String expression, int wrapperNumber) {
        model.SetInputString(expression);
        model.SetX(0);
        model.Calculate();
        StackWrapper convertedWrapper = model.GetConvertedStringWrapper();
        StackWrapper checkWrapper = DataHelper.wrappers[wrapperNumber];
        while (!convertedWrapper.empty()) {
            Data actual = convertedWrapper.top();
            Data expected = checkWrapper.top();
            Assertions.assertEquals(actual.GetValue(), expected.GetValue());
            Assertions.assertEquals(actual.GetPriority(), expected.GetPriority());
            Assertions.assertEquals(actual.getOperationType(), expected.getOperationType());
            convertedWrapper.pop();
            checkWrapper.pop();
        }
    }
}
