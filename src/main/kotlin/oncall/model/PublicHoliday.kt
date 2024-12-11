package oncall.model

enum class PublicHoliday(private val title: String, val month: Int, val dayOfMonth: Int) {
    NEW_YEAR("신정", 1, 1),
    SAMILJEOL("삼일절", 3, 1),
    CHILDREN_DAY("어린이날", 5, 5),
    MEMORIAL_DAY("현충일", 6, 6),
    LIBERATION_DAY("광복절", 8, 15),
    NATIONAL_FOUNDATION_DAY("개천절", 10, 3),
    HANGEUL_DAY("한글날", 10, 9),
    CHRISTMAS("성탄절", 12, 25);

    companion object {

        fun from(month: Int): PublicHoliday? {
            return PublicHoliday.entries.find {
                it.month == month
            }
        }
    }
}