import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun shouldNotCountCommission_more150000() {
        val amount = 1_000
        val totalAmountOfMonth = 0
        val totalAmountOfDay = 200_000
        val expected = "В операции отказано. Превышен лимит суммы за день"

        val result = countCommissionMastercardMaestro(
            amount = amount,
            totalAmountOfMonth = totalAmountOfMonth,
            totalAmountOfDay = totalAmountOfDay
        )
        assertEquals(expected, result)
    }
    @Test
    fun shouldNotCommissionMastercardMaestro_more600000() {
        val amount = 1_000
        val totalAmountOfMonth = 700_000
        val totalAmountOfDay = 10_000
        val expected = "В операции отказано. Превышен лимит суммы за месяц"

        val result = countCommissionMastercardMaestro(
            amount = amount,
            totalAmountOfMonth = totalAmountOfMonth,
            totalAmountOfDay = totalAmountOfDay
        )
        assertEquals(expected, result)
    }

    @Test
    fun shouldNotCommissionMastercardMaestro_less75000() {
        val amount = 1_000
        val totalAmountOfMonth = 0
        val totalAmountOfDay = 0
        val expected = "Комиссия за перевод отсутствует"

        val result = countCommissionMastercardMaestro(
            amount = amount,
            totalAmountOfMonth = totalAmountOfMonth,
            totalAmountOfDay = totalAmountOfDay
        )
        assertEquals(expected, result)
    }

    @Test
    fun countCommissionMastercardMaestro_75000_150000() {
        val amount = 1_000
        val totalAmountOfMonth = 80_000
        val totalAmountOfDay = 0
        val expected = "Комиссия за перевод составит 26.0 руб."

        val result = countCommissionMastercardMaestro(
            amount = amount,
            totalAmountOfMonth = totalAmountOfMonth,
            totalAmountOfDay = totalAmountOfDay
        )
        assertEquals(expected, result)
    }

    @Test
    fun countCommissionVisaMir_more150000() {
        val amount = 1_000
        val totalAmountOfMonth = 0
        val totalAmountOfDay = 200_000
        val expected = "В операции отказано. Превышен лимит суммы за день"

        val result = countCommissionVisaMir(
            amount = amount,
            totalAmountOfMonth = totalAmountOfMonth,
            totalAmountOfDay = totalAmountOfDay
        )
        assertEquals(expected, result)
    }

    @Test
    fun shouldNotCountCommissionVisaMir_more600000() {
        val amount = 1_000
        val totalAmountOfMonth = 700_000
        val totalAmountOfDay = 10_000
        val expected = "В операции отказано. Превышен месячный лимит по карте"

        val result = countCommissionVisaMir(
            amount = amount,
            totalAmountOfMonth = totalAmountOfMonth,
            totalAmountOfDay = totalAmountOfDay
        )
        assertEquals(expected, result)
    }

    @Test
    fun countCommissionVisaMir_more4700() {
        val amount = 5_000
        val totalAmountOfMonth = 0
        val totalAmountOfDay = 0
        val expected = "Комиссия за перевод составит 37.5 руб."

        val result = countCommissionVisaMir(
            amount = amount,
            totalAmountOfMonth = totalAmountOfMonth,
            totalAmountOfDay = totalAmountOfDay
        )
        assertEquals(expected, result)
    }

    @Test
    fun countCommissionVisaMir_less4700() {
        val amount = 1_000
        val totalAmountOfMonth = 0
        val totalAmountOfDay = 0
        val expected = "Комиссия за перевод составит 35 руб."

        val result = countCommissionVisaMir(
            amount = amount,
            totalAmountOfMonth = totalAmountOfMonth,
            totalAmountOfDay = totalAmountOfDay
        )
        assertEquals(expected, result)
    }

    @Test
    fun shouldNotCountCommissionVkPay_more15000() {
        val amount = 1_000
        val totalAmountOfMonth = 0
        val totalAmountOfDay = 16_000
        val expected = "В операции отказано. Превышен дневной лимит по карте"

        val result = countCommissionVkPay(
            amount = amount,
            totalAmountOfMonth = totalAmountOfMonth,
            totalAmountOfDay = totalAmountOfDay
        )
        assertEquals(expected, result)
    }

    @Test
    fun countCommissionVkPay_more40000() {
        val amount = 1_000
        val totalAmountOfMonth = 50_000
        val totalAmountOfDay = 0
        val expected = "В операции отказано. Превышен месячный лимит по карте"

        val result = countCommissionVkPay(
            amount = amount,
            totalAmountOfMonth = totalAmountOfMonth,
            totalAmountOfDay = totalAmountOfDay
        )
        assertEquals(expected, result)
    }
}