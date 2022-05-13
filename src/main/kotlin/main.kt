fun main() {
    countCommission(CardType.Maestro, 75_500, 6_550, 1_000)
}

fun countCommission(
    cardType: CardType=CardType.VkPay,
    totalAmountOfMonth: Int = 0,
    totalAmountOfDay: Int = 0,
    amount: Int
) {
    when (cardType) {
        CardType.Mastercard,CardType.Maestro -> countCommissionMastercardMaestro(amount, totalAmountOfMonth, totalAmountOfDay)
        CardType.Visa,CardType.Mir -> countCommissionVisaMir(amount, totalAmountOfMonth, totalAmountOfDay)
        CardType.VkPay -> countCommissionVkPay(totalAmountOfMonth, totalAmountOfDay)
    }
}

fun countCommissionMastercardMaestro(
    amount: Int,
    totalAmountOfMonth: Int = 0,
    totalAmountOfDay: Int = 0,
) {
    when {
        totalAmountOfDay > 150_000 -> println("В операции отказано. Превышен дневной лимит по карте")
        totalAmountOfMonth > 600_000 -> println("В операции отказано. Превышен месячный лимит по карте")
        totalAmountOfMonth > 75_000 -> {
            println("Сумма перевода составляет: $amount руб.")
            println("Комиссия за перевод составит: ${(amount * 0.006 + 20.00)*100} коп.")
        }
        else -> println("Комиссия за перевод отсутствует")
    }
}

fun countCommissionVisaMir(
    amount: Int,
    totalAmountOfMonth: Int = 0,
    totalAmountOfDay: Int = 0,
) {
    when {
        totalAmountOfDay > 150_000 -> println("В операции отказано.  Превышен дневной лимит по карте")
        totalAmountOfMonth > 600_000 -> println("В операции отказано. Превышен месячный лимит по карте")
        amount * 0.0075 < 35 -> println("Комиссия за перевод составит 35 руб.")
        else -> println{
            println("Сумма перевода составляет: $amount руб.")
            println("Комиссия за перевод составит ${(amount * 0.0075)*100} коп.")
        }
    }
}

fun countCommissionVkPay(
    totalAmountOfMonth: Int = 0,
    totalAmountOfDay: Int = 0,
) {
    when {
        totalAmountOfDay > 15_000 -> println("В операции отказано. Превышен дневной лимит по карте")
        totalAmountOfMonth > 40_000 -> println("В операции отказано. Превышен месячный лимит по карте")
        else -> println("Комиссия за перевод отсутствует")
    }
}
enum class CardType {
    Mastercard, Maestro, Visa, Mir, VkPay
}