package edu.school21.calculator.model;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = {"stack_wrapper.h"})
public class StackWrapper extends Pointer {
    static {
        Loader.load();
    }

    public StackWrapper() {
        allocate();
    }

    private native void allocate();

    public native boolean empty();

    public native @ByVal Data top();

    public native void push(@Const @ByRef Data data);

    public native void pop();
}
