package programmers

/**
 * 프로그래머스 - N으로 표현
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42895
 */

fun main() {
    println(N으로_표현().solution(5, 12))
}

class N으로_표현 {
    fun solution(N: Int, number: Int): Int {
        if (N == number) {
            return 1
        }
        val setArray: Array<MutableSet<Int>> = Array(9) { mutableSetOf() }
        setArray[1].add(N)

        for (i in 2..8) {
            var nn = ""
            repeat(i) {
                nn += N
            }

            setArray[i].add(nn.toInt())
            if (nn.toInt() == number) {
                return i
            }

            for (k in 1 until i) {
                val nowIterator: Iterator<Int> = setArray[k].iterator()
                var nextIterator: Iterator<Int> = setArray[i - k].iterator()
                while (nowIterator.hasNext()) {
                    val n1 = nowIterator.next()
                    val tempSet = mutableSetOf<Int>()

                    while (nextIterator.hasNext()) {
                        val n2 = nextIterator.next()
                        tempSet.add(n1 + n2)
                        tempSet.add(n1 - n2)
                        tempSet.add(n1 * n2)
                        if (n2 != 0) {
                            tempSet.add(n1 / n2)
                        }
                    }

                    if (tempSet.contains(number)) {
                        return i
                    }

                    setArray[i].addAll(tempSet)
                    nextIterator = setArray[i - k].iterator()
                }
            }
        }

        return -1
    }
}