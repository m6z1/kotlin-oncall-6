package oncall.model

import java.time.LocalDate

enum class PublicHoliday(private val title: String, val date: LocalDate) {
    NEW_YEAR("신정", LocalDate.of(2025, 1, 1)),
    SAMILJEOL("삼일절", LocalDate.of(2025, 3, 1)),
    CHILDREN_DAY("어린이날", LocalDate.of(2025, 5, 5)),
    MEMORIAL_DAY("현충일", LocalDate.of(2025, 6, 6)),
    LIBERATION_DAY("광복절", LocalDate.of(2025, 8, 15)),
    NATIONAL_FOUNDATION_DAY("개천절", LocalDate.of(2025, 10, 3)),
    HANGEUL_DAY("한글날", LocalDate.of(2025, 10, 9)),
    CHRISTMAS("성탄절", LocalDate.of(2025, 12, 25)),
}