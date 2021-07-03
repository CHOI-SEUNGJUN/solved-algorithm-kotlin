package programmers

/**
 * 프로그래머스 - 오픈채팅방
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42888
 */

fun main() {
    val answer = OpenChatSolution().solution(
        arrayOf("Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234",
            "Enter uid1234 Prodo","Change uid4567 Ryan")
    )
    println(answer.joinToString("\n"))
}

class OpenChatSolution {
    fun solution(record: Array<String>): Array<String> {
        return convertMessageByUserId(record)
    }

    private fun convertMessageByUserId(record: Array<String>): Array<String> {
        val hashMap = hashMapOf<String, String>()

        record.forEach { user ->
            val info = user.split(" ")
            val command = info[0]

            if (command == "Enter" || command == "Change") {
                val uuid = info[1]
                val nickname = info[2]

                hashMap[uuid] = nickname
            }
        }

        val messageList = mutableListOf<String>()
        record.forEach { user ->
            val (command, uuid, _) = user.split(" ")

            when (command) {
                "Enter" -> {
                    messageList.add("${hashMap[uuid]}님이 들어왔습니다.")
                }
                "Leave" -> {
                    messageList.add("${hashMap[uuid]}님이 나갔습니다.")
                }
            }
        }

        return messageList.toTypedArray()
    }
}