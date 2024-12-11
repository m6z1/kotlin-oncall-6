package oncall

class OnCallController(
    private val inputView: InputView,
) {

    fun start() {
        val assignmentMonth = inputView.readOnCallMonth()
    }
}