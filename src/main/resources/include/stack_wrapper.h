#ifndef STACK_WRAPPER_H_

#define STACK_WRAPPER_H_

#include <stack>

#include "data.h"

class StackWrapper {
 public:
  StackWrapper() = default;

  StackWrapper(std::stack<s21::Data>& stack) { this->stack = stack; }
  ~StackWrapper() = default;

  void push(const s21::Data& data) { stack.push(data); }

  void pop() { stack.pop(); }

  s21::Data top() { return stack.top(); }

  bool empty() { return stack.empty(); }

 private:
  std::stack<s21::Data> stack;
};

#endif