package oncall

import oncall.controller.OnCallController
import oncall.view.InputView

fun main() {
    val inputView = InputView()
    val onCallController = OnCallController(inputView)

    onCallController.start()
}
