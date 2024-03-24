#ifndef CALC_MODEL_H_

#define CALC_MODEL_H_

#include <vector>

#include "calculator.h"
#include "converter.h"
#include "locale.h"
#include "parser.h"
#include "stack_wrapper.h"

#define DOTS_COUNT 100
namespace s21 {
class CalcModel {
 public:
  CalcModel();
  ~CalcModel() = default;

  void ParseString();
  void ConvertStringToPolish();
  void Calculate();

  void Reset();

  void FindDotsToPlot(double x_begin, double x_end, double y_begin,
                      double y_end);
  bool IsGraphDataCorrect();

  void SetInputString(const std::string& str);
  void SetX(double value);

  const std::string& GetResultString();
  const std::string& GetPlotStatusString();

  const std::vector<double>& GetXDotsVector();
  const std::vector<double>& GetYDotsVector();

  double GetResultOfCalculation();

  std::stack<Data> GetConvertedString();

  StackWrapper GetConvertedStringWrapper() {
    StackWrapper wrapper(converted_input_);
    return wrapper;
  }

 private:
  Parser parser_;
  Converter converter_;
  Calculator calculator_;

  std::string input_;

  std::string result_string_;
  std::string plot_status_;

  std::stack<Data> converted_input_;

  std::vector<double> x_dots_;
  std::vector<double> y_dots_;

  double result_;
  double x_;

  bool is_parsed_;
  bool is_converted_;
  bool is_calculated_;

  bool is_plot_error_;
  bool is_dots_calculated_;
};
}  // namespace s21
#endif
