package kakao.blind2020

// // 카카오 2020 신규 아이디 추천
// https://programmers.co.kr/learn/courses/30/lessons/72410

fun main() {
    var id = "...!@bat#*..y.abcdefghijklm"
    println(Solution1().solution(id))
}

class Solution1 {
    fun solution(new_id: String): String {
        var id = new_id

        // 1단계
        id = id.toLowerCase()

        // 2단계
        id = id.replace("[^a-z\\d\\-._]".toRegex(), "")

        // 3단계
        id = id.replace("[.]{2,}".toRegex(), ".")

        // 4단계
        id = id.replace("^[.]|[.]$".toRegex(), "")

        // 5단계
        if (id.isBlank()) id = "a"

        // 6단계
        if (id.length >= 16) id = id.substring(0, 15)
        while (id.isNotBlank() && id.last() == '.') {
            id = id.dropLast(1)
        }

        // 7단계
        if (id.length <= 2) {
            while (id.length < 3) {
                id += id.last()
            }
        }

        return id
    }
}