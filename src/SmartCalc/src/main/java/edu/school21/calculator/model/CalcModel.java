package edu.school21.calculator.model;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = "calc_model.h", link = "model")
@Namespace("s21")
public class CalcModel extends Pointer {
    static {
        Loader.load();
    }

    public CalcModel() {
        allocate();
    }

    public native void allocate();

    public native void SetInputString(@Const @StdString @ByRef String str);

    public native void SetX(double value);

    public native void Calculate();

    public native double GetResultOfCalculation();

    public native @ByVal StackWrapper GetConvertedStringWrapper();

    public native void FindDotsToPlot(@ByVal double x_begin, @ByVal double x_end,
                                      @ByVal double y_begin, @ByVal double y_end);

    public native boolean IsGraphDataCorrect();

    public native @Const
    @ByRef Vector.DoubleVector GetXDotsVector();

    public native @Const
    @ByRef Vector.DoubleVector GetYDotsVector();

    @Const
    @StdString
    @ByRef
    public native String GetResultString();

    @Const
    @StdString
    @ByRef
    public native String GetPlotStatusString();
}