package kakao

// 카카오 2020 괄호 변환 문제
// https://programmers.co.kr/learn/courses/30/lessons/60058

fun main() {
    println("answer: ${SSolution().solution("()))((()")}")
}

class SSolution {
    fun solution(p: String): String {
        return correctBracket(p)
    }

    fun correctBracket(p: String): String {
        if (p.isBlank()) return ""

        val sp = strSplit(p)
        var u = sp.first
        var v = sp.second

        if (u.isCorrected()) {
            // 3
            v = correctBracket(v)
            return u+v
        } else {
            // 4
            var char = ""
            char += "("
            char += correctBracket(v)
            char += ")"
            u = u.drop(1)
            u = u.dropLast(1)
            u = u.reverseBracket()
            char += u

            return char
        }
    }

    fun String.isCorrected(): Boolean {
        // 닫힌 갯수가 열린 갯수보다 많아지면 (()()( )()
        var openedCnt = 0
        var closedCnt = 0
        var isCorrected = true
        run {
            this.forEach { char ->
                if ( char == '(') {
                    openedCnt++
                } else {
                    closedCnt++
                }

                if (openedCnt < closedCnt) {
                    isCorrected = false
                    return@run
                }
            }
        }

        return isCorrected
    }

    fun String.reverseBracket(): String {
        var reversed = ""

        for (elem in this) {
            when (elem) {
                '(' -> reversed += ')'
                ')' -> reversed += '('
                else -> ""
            }
        }

        return reversed
    }

    fun strSplit(str: String): Pair<String, String> {
        var openedCnt = 0
        var closedCnt = 0

        var splitPosition = 0

        run {
            str.forEachIndexed { idx, s ->
                if (s == '(') {
                    openedCnt++
                } else {
                    closedCnt++
                }

                if (openedCnt != 0 && openedCnt == closedCnt) {
                    splitPosition = idx
                    return@run
                }
            }
        }

        val u = str.substring(0, splitPosition + 1)
        val v = str.replaceFirst(u, "")

        return u to v
    }
}