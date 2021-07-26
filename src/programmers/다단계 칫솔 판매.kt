package programmers

/**
 * 프로그래머스 - 다단계 칫솔 판매
 *
 * https://programmers.co.kr/learn/courses/30/lessons/77486
 */

fun main() {
    val answer = MultiLevelSellerSolution().solution(
        enroll = arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"),
        referral = arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"),
        seller = arrayOf("young", "john", "tod", "emily", "mary"),
        amount = intArrayOf(12, 4, 2, 5, 10))
    println(answer.joinToString(", "))
    // answer : 360, 958, 108, 0, 450, 18, 180, 1080
}

class MultiLevelSellerSolution {
    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        // @value levelMap 상하관계 설정 - 하위 to 상위
        // @value enroll name to Index
        val levelMap = hashMapOf<String, String>()
        val indexMap = hashMapOf<String, Int>()
        for (i in referral.indices) {
            val enrollSeller = enroll[i]
            indexMap[enrollSeller] = i
            val superior = referral[i]
            if (superior == "-") continue

            levelMap[enrollSeller] = superior
        }

        val profitList = enroll
            .map { enrollSeller -> Seller(name = enrollSeller) }
            .toMutableList()

        for (i in seller.indices) {
            var currentSeller = seller[i]
            var currentProfit = amount[i] * UNIT_PRICE

            while (true) {
                val idx = indexMap[currentSeller]!!
                val shouldDistributeProfit = (currentProfit * 0.1).toInt()
                val profitAfterDistributing = currentProfit - shouldDistributeProfit

                if (shouldDistributeProfit < 1) {
                    profitList[idx].profit += currentProfit
                    break
                } else {
                    profitList[idx].profit += profitAfterDistributing
                }

                if (levelMap[currentSeller].isNullOrBlank()) {
                    break
                } else {
                    currentProfit = shouldDistributeProfit
                    currentSeller = levelMap[currentSeller]!!
                }
            }
        }
        return profitList
            .map { it.profit }
            .toIntArray()
    }

    data class Seller(
        val name: String,
        var profit: Int = 0
    )

    private companion object {
        const val UNIT_PRICE = 100
    }
}