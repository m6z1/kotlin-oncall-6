package oncall

class OnCallController(
    private val inputView: InputView,
) {

    fun start() {
        val assignmentMonth = inputView.readOnCallMonth()
        val workersOfWeekdays = inputView.readOnCallWeekdays()
        val workersOfHolidays = inputView.readOnCallHolidays()

        var count = 0
        workersOfHolidays.forEach { worker1 ->
            workersOfWeekdays.forEach {
                if (it.getWorkerNickName() == worker1.getWorkerNickName()) count++
            }
        }
        if (count == workersOfHolidays.size && workersOfHolidays.size == workersOfWeekdays.size) {
            // TODO: 이어서
        }
    }
}