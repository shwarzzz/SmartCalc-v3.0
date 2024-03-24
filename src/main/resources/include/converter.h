#ifndef CONVERTER_H_

#define CONVERTER_H_

#include <iostream>
#include <stack>
#include <stdexcept>

#include "data.h"

namespace s21 {
class Converter {
 public:
  using stack = std::stack<s21::Data>;

  Converter() = default;
  ~Converter() = default;

  std::stack<s21::Data> ConvertToPolish(stack& input);

 private:
  static void ReverseStack(stack& src);
  void MergeStack(stack& dst, stack& src);
  void CheckPriority(s21::Data& elem, stack& support, stack& result);
};
}  // namespace s21

#endif