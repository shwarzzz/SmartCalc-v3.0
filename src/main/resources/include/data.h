#ifndef DATA_H_

#define DATA_H_
#include <iostream>
#include <map>

namespace s21 {

enum OperationType {
  kUnknown = 0,
  kNumber = 1,
  kX = 2,
  kBinaryPlus = 3,
  kBinaryMinus = 4,
  kUnaryPlus = 5,
  kUnaryMinus = 6,
  kLeftBracket = 7,
  kRightBracket = 8,
  kMult = 9,
  kDiv = 10,
  kMod = 11,
  kSin = 12,
  kCos = 13,
  kTan = 14,
  kAsin = 15,
  kAcos = 16,
  kAtan = 17,
  kSqrt = 18,
  kPow = 19,
  kLog = 20,
  kLn = 21
};

static const std::map<char, OperationType> one_char_lexeme{
    {'+', kBinaryPlus},   {'-', kBinaryMinus}, {'(', kLeftBracket},
    {')', kRightBracket}, {'*', kMult},        {'/', kDiv},
    {'^', kPow}};

static const std::map<OperationType, std::string> function_lexeme{
    {kMod, "mod"},    {kSqrt, "sqrt("}, {kLn, "ln("},   {kLog, "log("},
    {kSin, "sin("},   {kCos, "cos("},   {kTan, "tan("}, {kAsin, "asin("},
    {kAcos, "acos("}, {kAtan, "atan("}};

class Data {
 public:
  Data() : value_(0), priority_(0), type_(kUnknown) {}

  Data(double value, int priority, OperationType type)
      : value_(value), priority_(priority), type_(type) {}

  Data(const Data& other)
      : value_(other.value_), priority_(other.priority_), type_(other.type_) {}

  Data& operator=(const Data& other) {
    if (this != &other) {
      value_ = other.value_;
      priority_ = other.priority_;
      type_ = other.type_;
    }
    return *this;
  }

  Data& operator=(Data&& other) {
    if (this != &other) {
      value_ = other.value_;
      priority_ = other.priority_;
      type_ = other.type_;
      other.value_ = 0;
      other.priority_ = 0;
      other.type_ = kUnknown;
    }
    return *this;
  }

  bool operator==(const Data& other) {
    return value_ == other.value_ && type_ == other.type_ &&
           priority_ == other.priority_;
  }

  ~Data() = default;

  double GetValue() const noexcept { return value_; }
  int GetPriority() const noexcept { return priority_; }
  OperationType GetType() const noexcept { return type_; }

  Data& SetValue(double value) noexcept {
    value_ = value;
    return *this;
  }

  Data& SetPriority(int priority) noexcept {
    priority_ = priority;
    return *this;
  }

  Data& SetType(OperationType type) noexcept {
    type_ = type;
    return *this;
  }

 private:
  double value_;
  int priority_;
  OperationType type_;
};
}  // namespace s21
#endif