package oncall

import oncall.controller.OnCallController
import oncall.view.InputView
import oncall.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val onCallController = OnCallController(inputView, outputView)

    onCallController.start()
}
