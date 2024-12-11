package oncall

import camp.nextstep.edu.missionutils.Console

class InputView {

    fun readOnCallMonth(): AssignmentMonth {
        println("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        val readLine = Console.readLine().split(",")
        require(readLine.size == 2) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        val month = readLine[0].toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
        require(month in 1..12) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        val startWeekOfMonth = readLine[1]
        require(Week.isValidWeek(startWeekOfMonth)) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }

        return AssignmentMonth(month, startWeekOfMonth)
    }
}