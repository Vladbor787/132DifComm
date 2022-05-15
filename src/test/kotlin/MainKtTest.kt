import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calcCommission_MirCommissionLow() {
        val transferAmount = 500 * ruble
        val cardType= "Мир"

        val commission = calculationCommission( cardType,transferAmount)

        assertEquals(35 * ruble, commission)
    }

    @Test
    fun calcCommission_MirCommissionHigh() {
        val transferAmount = 50_000 * ruble
        val cardType= "Мир"

        val commission = calculationCommission(cardType,transferAmount)

        assertEquals(375 * ruble, commission)
    }

    @Test
    fun calcCommission_VkPay() {
        val transferAmount = 50_000 * ruble
        val cardType= "VK Pay"

        val commission = calculationCommission(cardType,transferAmount)

        assertEquals(0, commission)
    }

    @Test
    fun calcCommission_MastercardHigh() {
        val transferAmount = 50_000 * ruble
        val cardType= "MasterCard"

        val commission = calculationCommission(cardType,transferAmount,totalMonth = 95_000 * ruble)

        assertEquals(320 * ruble, commission)
    }

    @Test
    fun calcCommission_MastercardLow() {
        val transferAmount = 50_000 * ruble
        val cardType = "MasterCard"

        val commission = calculationCommission(cardType,transferAmount, totalMonth = 5_000 * ruble)

        assertEquals(0 * ruble, commission)
    }

    @Test
    fun calculationCommission() {
        val cardType = "Visa"
        val previousMonthAmount = 6500 * ruble
        val currentTransferAmount = 100 * ruble
        val commission = calculationCommission(cardType,previousMonthAmount,currentTransferAmount )
        assertEquals(4875, commission)
    }

    @Test
    fun shouldCheckMonthCardLimitMasterCard() {
        val currentTransferAmount = 50_000_00
        val cardType = "MasterCard"
        val totalMonth = 650_000_00
        val checkLim = checkCardLimit(currentTransferAmount,cardType,totalMonth)

        assertEquals(2, checkLim)
    }
    @Test
    fun shouldCheckDayCardLimitMasterCard() {
        val currentTransferAmount = 150_001_00
        val cardType = "Maestro"
        val totalMonth = 0
        val checkLim = checkCardLimit(currentTransferAmount,cardType,totalMonth)

        assertEquals(1, checkLim)
    }
    @Test
    fun shouldCheckNoCardLimitFound() {
        val currentTransferAmount = 15_001_00
        val cardType = "MasterCard"
        val totalMonth = 0
        val checkLim = checkCardLimit(currentTransferAmount,cardType,totalMonth)

        assertEquals(0, checkLim)
    }
    @Test
    fun shouldCheckDayCardLimitVkpay() {
        val currentTransferAmount = 15_001_00
        val cardType = "VK Pay"
        val totalMonth = 0
        val checkLim = checkCardLimit(currentTransferAmount,cardType,totalMonth)

        assertEquals(3, checkLim)
    }
    @Test
    fun shouldCheckMonthCardLimitVkpay() {
        val currentTransferAmount = 10_001_00
        val cardType = "VK Pay"
        val totalMonth = 30_000_00
        val checkLim = checkCardLimit(currentTransferAmount,cardType,totalMonth)

        assertEquals(4, checkLim)
    }
}
