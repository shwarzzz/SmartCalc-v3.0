#ifndef INPUT_ERROR_H_
#include <stdexcept>

#define INPUT_ERROR_H_

namespace s21 {
class InputError : public std::logic_error {
 public:
  explicit InputError(const char* message) : std::logic_error(message) {}
};
}  // namespace s21

#endif