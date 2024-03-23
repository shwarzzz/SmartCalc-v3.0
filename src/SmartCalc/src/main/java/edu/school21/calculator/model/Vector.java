package edu.school21.calculator.model;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "<vector>")
public class Vector {
    @Name("std::vector<double>")
    public static class DoubleVector extends Pointer {
        static {
            Loader.load();
        }

        public DoubleVector() {
            allocate();
        }

        private native void allocate();

        @Name("operator=")
        public native @ByRef DoubleVector put(@ByRef DoubleVector x);

        @Name("operator[]")
        public native double get(long n);

        public native long size();

        public native @Cast("bool") boolean empty();
    }
}
