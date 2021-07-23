package programmers

/**
 * 프로그래머스 - 후보키
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42890
 */

fun main() {
    val answer = Test().solution(
        arrayOf(
            arrayOf("100","ryan","music","2"),
            arrayOf("200","apeach","math","2"),
            arrayOf("300","tube","computer","3"),
            arrayOf("400","con","computer","4"),
            arrayOf("500","muzi","music","3"),
            arrayOf("600","apeach","music","2")
        )
    )
    println(answer)
}

class Test {

    val attributesSet = mutableSetOf<MutableList<Int>>()

    fun solution(relation: Array<Array<String>>): Int {

        val visited = BooleanArray(relation[0].size)
        for (i in 1..relation[0].size) {
            setAttributeCombination(visited, 0, i)
        }

        val attributeList = attributesSet.toMutableList()

        val candidateKeys = mutableListOf<MutableList<Int>>()

        for (i in attributeList.indices) {
            val set = hashSetOf<String>()
            val keys = attributeList[i]

            for (j in relation.indices) {
                var r = ""
                for (k in keys.indices) {
                    r += relation[j][keys[k]]
                }
                set.add(r)
            }

            if (set.size == relation.size) {
                var flag = true
                for (j in candidateKeys.indices) {
                    if (keys.containsAll(candidateKeys[j])) {
                        flag = false
                        break
                    }
                }
                if (flag) {
                    candidateKeys.add(keys)
                }
            }
        }

        return candidateKeys.size
    }

    fun setAttributeCombination(visited: BooleanArray, start: Int, r: Int) {
        if (r == 0) {
            val combinedAttributes = mutableListOf<Int>()
            for (i in visited.indices) {
                if (visited[i]) combinedAttributes.add(i)
            }
            attributesSet.add(combinedAttributes)
            return
        } else {
            for (i in start until visited.size) {
                visited[i] = true
                setAttributeCombination(visited, i + 1, r - 1)
                visited[i] = false
            }
        }
    }
}