package etc

import java.util.*

fun main() {
    val binaryTree = BinaryTree()
    binaryTree.root = TreeNode(1)
    binaryTree.root!!.left = TreeNode(2)
    binaryTree.root!!.right = TreeNode(3)
    binaryTree.root!!.left!!.left = TreeNode(4)
    binaryTree.root!!.left!!.right = TreeNode(5)

    binaryTree.orderingByLevel()
}

data class TreeNode<T>(var data: T, var left: TreeNode<T>? = null, var right: TreeNode<T>? = null)

class BinaryTree {
    var root: TreeNode<Int>? = null

    fun orderingByLevel() {
        val queue: Queue<TreeNode<Int>> = LinkedList()

        queue.add(root)

        while (queue.isNotEmpty()) {
            val polledNode = queue.poll()
            print("${polledNode.data} ")

            /* 왼쪽 노드 offer */
            if (polledNode.left != null) {
                queue.offer(polledNode.left)
            }

            /* 오른쪽 노드 offer */
            if (polledNode.right != null) {
                queue.offer(polledNode.right)
            }
        }
    }
}