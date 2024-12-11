package oncall.model

enum class Week(private val title: String) {
    SUNDAY("일"),
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토");

    companion object {

        fun from(title: String): Week {
            return Week.entries.find {
                it.title == title
            } ?: throw IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
        }

        fun isValidWeek(title: String): Boolean {
            return Week.entries.any {
                it.title == title
            }
        }
    }
}