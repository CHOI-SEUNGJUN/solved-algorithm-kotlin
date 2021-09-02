package programmers

/**
 * 프로그래머스 - 매칭 점수
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42893
 */

import java.util.regex.Pattern

fun main() {
    val answer = MatchScoreSolution().solution("Muzi",
    arrayOf("<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>")
    )
    println(answer)
}


class MatchScoreSolution {

    fun solution(word: String, pages: Array<String>): Int {
        val loweredWord = word.toLowerCase()

        val basicScoreMap = hashMapOf<String, Double>()
        val linkScoreMap = hashMapOf<String, Double>()

        val linkList = mutableListOf<String>()

        for (i in pages.indices) {
            val page = pages[i].toLowerCase()

            // 1. URL 추출
            val pageName = Pattern.compile("<meta property=\"og:url\" content=\\S+/>")
            val pageNameMatcher = pageName.matcher(page.split("</head>")[0])

            var url = ""
            if (pageNameMatcher.find()) {
                url = pageNameMatcher.group().substring("<meta property=\"og:url\" content=\"".length)
            }
            url = url.substring(0, url.indexOf("\""))
            linkList.add(url)

            // 2. Word 추출 (Basic Score)
            var wordForMatching = page
            wordForMatching = wordForMatching
                .substring(wordForMatching.indexOf("<body>"))
                .replace("[0-9]".toRegex(), " ")

            val wordMatcher = Pattern.compile("\\b$loweredWord\\b").matcher(wordForMatching)

            var basicScore = 0
            while (wordMatcher.find()) {
                wordMatcher.group()
                basicScore++
            }
            basicScoreMap[url] = basicScore.toDouble()


            // 3. Link 추출 (Reference)
            wordForMatching = pages[i]
            wordForMatching = wordForMatching
                .substring(wordForMatching.indexOf("<body>"))
            val linkMatcher = Pattern.compile("<a href=\\S+>").matcher(wordForMatching)

            val matchLinkList = mutableListOf<String>()

            while (linkMatcher.find()) {
                var matchedSubString = linkMatcher.group()
                matchedSubString = matchedSubString.substring("<a href=\"".length)
                matchedSubString = matchedSubString.substring(0, matchedSubString.indexOf("\""))

                matchLinkList.add(matchedSubString)
            }

            // 4. 링크 점수 산정

            val refCnt = matchLinkList.size

            for (j in matchLinkList.indices) {
                linkScoreMap[matchLinkList[j]] = linkScoreMap.getOrDefault(matchLinkList[j], 0.0) + basicScore / refCnt.toDouble()
            }
        }

        // 매칭 점수 Search
        val matchScoreMap = hashMapOf<String, Double>()
        var maxScore = 0.0
        basicScoreMap.forEach { (key, _) ->
            var value = 0.0

            if (linkScoreMap.containsKey(key)) {
                value = linkScoreMap[key]!!
            }

            if (basicScoreMap.containsKey(key)) {
                value += basicScoreMap[key]!!
            }
            matchScoreMap[key] = value
            if (value > maxScore) {
                maxScore = value
            }
        }

        // 최소 index Search
        var minIndex = 0
        for ((index, link) in linkList.withIndex()) {
            if (matchScoreMap[link] == maxScore) {
                minIndex = index
                break
            }
        }
        return minIndex
    }
}