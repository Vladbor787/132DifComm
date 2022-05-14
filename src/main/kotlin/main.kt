const val MASTERCARD_MAESTRO_MONTH = 75_000_00
const val MASTERCARD_MAESTRO_COMMISSION = 0.006
const val MASTERCARD_MAESTRO_ADD_COMMISSION = 20_00
const val VISA_MIR_COMMISSION = 0.0075
const val VISA_MIR_MIN_COMMISSION = 35_00
const val NO_COMMISSION = 0
const val MONTH_CARD_LIMIT_EXCEPT_VKPAY = 600_000_00
const val MONTH_CARD_LIMIT_VKPAY = 40_000_00
const val DAY_CARD_LIMIT_EXCEPT_VKPAY = 150_000_00
const val DAY_CARD_LIMIT_VKPAY = 15_000_00
const val ruble = 100


fun main() {
    val cardType = "Visa"
    val previousMonthAmount = 650100 * ruble
    val currentTransferAmount = 100 * ruble
    val totalDay = 0
    if (checkCardLimit(currentTransferAmount, cardType, previousMonthAmount, totalDay) == 0) {
        val commission = calculationCommission(cardType,previousMonthAmount,currentTransferAmount )
        println("Комиссия составит: $commission коп.")
    }else {
        println("Операция не возможна.Превышен лимит по карте")
    }
}
fun calculationCommission(cardType: String = "VK Pay", transferAmount: Int,totalMonth: Int = 0): Int {
    return when (cardType) {
        "MasterCard", "Maestro" -> {
            if (totalMonth < MASTERCARD_MAESTRO_MONTH) NO_COMMISSION else
                (transferAmount * MASTERCARD_MAESTRO_COMMISSION + MASTERCARD_MAESTRO_ADD_COMMISSION).toInt()
        }
        "Visa", "Мир" -> {
            val commission = transferAmount * VISA_MIR_COMMISSION
            if (commission < VISA_MIR_MIN_COMMISSION) VISA_MIR_MIN_COMMISSION else commission.toInt()
        }
        else -> NO_COMMISSION
    }
}

fun checkCardLimit(currentTransferAmount: Int, cardType: String, totalPreviousMonth: Int = 0, totalDay: Int = 0): Int {
    return when {
        (totalDay+currentTransferAmount > DAY_CARD_LIMIT_EXCEPT_VKPAY) -> -1
        (totalPreviousMonth+currentTransferAmount > MONTH_CARD_LIMIT_EXCEPT_VKPAY) -> -2
        ((cardType == "VK Pay") && (currentTransferAmount > DAY_CARD_LIMIT_VKPAY)) -> -3
        ((cardType == "VK Pay") && (totalPreviousMonth+currentTransferAmount > MONTH_CARD_LIMIT_VKPAY)) -> -4
        else -> 0
    }
}