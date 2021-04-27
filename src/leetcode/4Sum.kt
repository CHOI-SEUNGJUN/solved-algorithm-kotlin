package leetcode

// 4Sum
// https://leetcode.com/problems/4sum/


fun main() {
    `4SumSolution`().fourSum(intArrayOf(1,0,-1,0,-2,2), 0)
}

class `4SumSolution` {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {

        if (nums.size < 4) return listOf()

        nums.sort()

        val sumList = arrayListOf<List<Int>>()
        for (i in 0..nums.size - 4) {
            if (nums[i] * 4 > target) break

            if (i > 0 && nums[i] == nums[i - 1]) continue

            for (j in (i + 1)..nums.size - 3) {
                var low = j + 1
                var high = nums.size - 1
                val forTarget = target - nums[i] - nums[j]

                while (low < high) {
                    if (nums[low] + nums[high] == forTarget) {
                        sumList.add(listOf(nums[i], nums[j], nums[low], nums[high]))
                        low++
                        while (low < high && nums[low] == nums[low - 1]) {
                            low++
                        }
                        high--
                        while (low < high && nums[high] == nums[high + 1]) {
                            high--
                        }
                    } else if (forTarget > nums[low] + nums[high]) {
                        low++
                    } else {
                        high--
                    }
                }
            }
        }

        return sumList.distinct()
    }
}