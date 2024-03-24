#ifndef CALCULATION_ERROR_H_
#include <stdexcept>

#define CALCULATION_ERROR_H_

namespace s21 {
class CalculationError : public std::logic_error {
 public:
  explicit CalculationError(const char* message) : std::logic_error(message) {}
};
}  // namespace s21

#endif