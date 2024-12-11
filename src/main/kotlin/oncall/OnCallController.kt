package oncall

class OnCallController(
    private val inputView: InputView,
) {

    fun start() {
        val assignmentMonth = inputView.readOnCallMonth()
        val workersOfWeekdays = Workers.from(workers = inputView.readOnCallWeekdays())
        val workersOfHolidays = Workers.from(workers = inputView.readOnCallHolidays())
    }
}