package oncall

import oncall.controller.OnCallController
import oncall.model.MonthGenerator
import oncall.model.Week
import oncall.view.InputView

fun main() {
    val inputView = InputView()
    val onCallController = OnCallController(inputView)

    onCallController.start()
}
