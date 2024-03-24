#ifndef PARSER_H_

#define PARSER_H_
#include <cmath>
#include <iostream>
#include <stack>
#include <stdexcept>

#include "data.h"
#include "input_error.h"
namespace s21 {
class Parser {
 public:
  Parser() {}
  ~Parser() {}
  std::stack<s21::Data> ParseString(const std::string& str);

 private:
  s21::Data ConvertNumberToString(const std::string& str, size_t& index);
  std::string CheckNumber(const std::string& str, size_t& index,
                          size_t max_dot_count);

  s21::Data CheckAlgebraicSigns(const std::string& str, size_t index);
  s21::Data CheckMatchingBracket(const std::string& str, size_t index);

  s21::Data CheckMathLexeme(const std::string& str, size_t& index);
  s21::Data CheckTrigonometricLexeme(const std::string& str, size_t& index);

  s21::Data CheckModLexeme(const std::string& str, size_t& index);
  s21::Data CheckLexemeEquality(const std::string& str, size_t& index,
                                OperationType type);

  double ConvertStringToDouble(const std::string& str);

  bool IsDigit(char c);
  bool IsSymbolCorrect(char c);
  bool IsBinaryOperatorsConditions(const std::string& str, size_t index);
};
}  // namespace s21
#endif
