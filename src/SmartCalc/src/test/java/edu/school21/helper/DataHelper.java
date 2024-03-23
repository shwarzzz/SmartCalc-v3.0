package edu.school21.helper;

import edu.school21.calculator.model.Data;
import edu.school21.calculator.model.StackWrapper;

public class DataHelper {
    public static final int TEST_COUNT = 2;
    public static final StackWrapper[] wrappers = new StackWrapper[TEST_COUNT];

    public static void initWrappers() {
        StackWrapper firstStack = new StackWrapper();
        firstStack.push(new Data(0, 1, Data.OperationType.kBinaryPlus.value()));
        firstStack.push(new Data(0, 2, Data.OperationType.kMult.value()));
        firstStack.push(new Data(0, 3, Data.OperationType.kPow.value()));
        firstStack.push(new Data(2, 0, Data.OperationType.kNumber.value()));
        firstStack.push(new Data(0, 4, Data.OperationType.kLn.value()));
        firstStack.push(new Data(5.23, 0, Data.OperationType.kNumber.value()));
        firstStack.push(new Data(23, 0, Data.OperationType.kNumber.value()));
        firstStack.push(new Data(0, 1, Data.OperationType.kBinaryMinus.value()));
        firstStack.push(new Data(0, 2, Data.OperationType.kMult.value()));
        firstStack.push(new Data(0, 1, Data.OperationType.kUnaryMinus.value()));
        firstStack.push(new Data(0, 4, Data.OperationType.kSin.value()));
        firstStack.push(new Data(0, 1, Data.OperationType.kUnaryMinus.value()));
        firstStack.push(new Data(0, 0, Data.OperationType.kX.value()));
        firstStack.push(new Data(0, 4, Data.OperationType.kAtan.value()));
        firstStack.push(new Data(0, 0, Data.OperationType.kX.value()));
        firstStack.push(new Data(0, 2, Data.OperationType.kDiv.value()));
        firstStack.push(new Data(0, 4, Data.OperationType.kSqrt.value()));
        firstStack.push(new Data(9, 0, Data.OperationType.kNumber.value()));
        firstStack.push(new Data(0, 4, Data.OperationType.kCos.value()));
        firstStack.push(new Data(12, 0, Data.OperationType.kNumber.value()));
        wrappers[0] = firstStack;

        StackWrapper secondStack = new StackWrapper();
        secondStack.push(new Data(0, 1, Data.OperationType.kBinaryPlus.value()));
        secondStack.push(new Data(0, 4, Data.OperationType.kLn.value()));
        secondStack.push(new Data(0.5, 0, Data.OperationType.kNumber.value()));
        secondStack.push(new Data(0, 1, Data.OperationType.kBinaryPlus.value()));
        secondStack.push(new Data(0, 4, Data.OperationType.kLog.value()));
        secondStack.push(new Data(2, 0, Data.OperationType.kNumber.value()));
        secondStack.push(new Data(0, 4, Data.OperationType.kTan.value()));
        secondStack.push(new Data(0, 4, Data.OperationType.kAsin.value()));
        secondStack.push(new Data(0, 4, Data.OperationType.kAcos.value()));
        secondStack.push(new Data(0.98, 0, Data.OperationType.kNumber.value()));

        wrappers[1] = secondStack;
    }

    public static void closeWrappers() {
        for (StackWrapper wrapper : wrappers) {
            if (wrapper != null) {
                wrapper.close();
            }
        }
    }
}