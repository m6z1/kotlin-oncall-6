package oncall

fun main() {
    val inputView = InputView()
    val onCallController = OnCallController(inputView)

    onCallController.start()
}
