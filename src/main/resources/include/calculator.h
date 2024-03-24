#ifndef CALCULATOR_H_

#define CALCULATOR_H_

#include <array>
#include <cmath>
#include <iostream>
#include <stack>
#include <stdexcept>

#include "calculation_error.h"
#include "data.h"

#define MAX_NUMB_COUNT 255

namespace s21 {
class Calculator {
 public:
  using array = std::array<double, MAX_NUMB_COUNT>;

  Calculator() = default;
  ~Calculator() = default;

  double PerformCalculation(const std::stack<s21::Data>& input, double x);

 private:
  bool DoSimpleArithmetic(s21::Data& elem, array& numb, int& index);
  void DoHardArithmetic(s21::Data& elem, array& numb, int& index);
};

}  // namespace s21

#endif
