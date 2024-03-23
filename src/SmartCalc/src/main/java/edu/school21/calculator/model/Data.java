package edu.school21.calculator.model;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = "data.h")
@Namespace("s21")
public class Data extends Pointer {
    static {
        Loader.load();
    }

    public Data() {
        allocate();
    }

    public Data(@ByVal double value, @ByVal int priority, @Cast("s21::OperationType") int type) {
        allocate(value, priority, type);
    }

    public native void allocate();

    public native void allocate(@ByVal double value, @ByVal int priority, @Cast("s21::OperationType") int type);

    @Const
    @NoException
    public native double GetValue();

    @Const
    @NoException
    public native int GetPriority();

    @Const
    @NoException
    public native @Cast("s21::OperationType") int GetType();

    public OperationType getOperationType() {
        int nativeValue = GetType();
        for (OperationType type : OperationType.values()) {
            if (type.value() == nativeValue) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + nativeValue);
    }

    public enum OperationType {
        kUnknown(0),
        kNumber(1),
        kX(2),
        kBinaryPlus(3),
        kBinaryMinus(4),
        kUnaryPlus(5),
        kUnaryMinus(6),
        kLeftBracket(7),
        kRightBracket(8),
        kMult(9),
        kDiv(10),
        kMod(11),
        kSin(12),
        kCos(13),
        kTan(14),
        kAsin(15),
        kAcos(16),
        kAtan(17),
        kSqrt(18),
        kPow(19),
        kLog(20),
        kLn(21);
        private final int value;

        OperationType(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }
}
