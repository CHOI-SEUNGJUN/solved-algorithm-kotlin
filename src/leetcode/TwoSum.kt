package leetcode

// TwoSum
// https://leetcode.com/problems/two-sum/

fun main() {
    val sums = TwoSumSolution().twoSum(intArrayOf(2, 7, 11, 15), 9)
    println(sums.contentToString())
}

class TwoSumSolution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            if (map.containsKey(target - nums[i])) {
                return intArrayOf(map[target - nums[i]]!!, i)
            }
            map[nums[i]] = i
        }
        return intArrayOf()
    }
}