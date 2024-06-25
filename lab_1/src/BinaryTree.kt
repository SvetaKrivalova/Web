
class Node(
    var key: Int,
    var left: Node? = null,
    var right: Node? = null) {

    //Значение = значению текущего узла -> конец поиска.
    //Больше - право, меньше - лево.
    fun find(value: Int): Node? = when {
        this.key > value -> left?.find(value)
        this.key < value -> right?.find(value)
        else -> this
    }

    //Если значение отсутствует = вставить в узел, который имеет свободную "щель".
    //Присутствует = ничего делать не надо.
    fun insert(value: Int) {
        if (value > this.key) {
            if (this.right == null) {
                this.right = Node(value)
            } else {
                this.right?.insert(value)
            }
        } else if (value < this.key) {
            if (this.left == null) {
                this.left = Node(value)
            } else {
                this.left?.insert(value)
            }
        }
    }

    //Скан дерева. Классификация по кол-ву ненулевых дочерних узлов.
    fun delete(value: Int) {
        when {
            value > key -> scan(value, this.right, this)
            value < key -> scan(value, this.left, this)
            else -> removeNode(this, null)
        }
    }

    private fun scan(value: Int, node: Node?, parent: Node?) {
        if (node == null) {
            System.out.println("value" + value + "is null")
            return
        }
        when {
            value > node.key -> scan(value, node.right, node)
            value < node.key -> scan(value, node.left, node)
            value == node.key -> removeNode(node, parent)
        }
    }

    private fun removeNode(node: Node, parent: Node?) {
        node.left?.let {
            leftChild ->
            run {
                node.right?.let {
                    removeTwoChildNode(node)
                } ?: removeSingleChildNode(node, leftChild)
            }
        } ?: run {
            node.right?.let {
                rightChild -> removeSingleChildNode(node, rightChild)
            } ?: removeNoChildNode(node, parent)
        }
    }

    //Если узел является корневым, мы не можем его устранить.
    //В противном случае достаточно установить null соответствующего потомка родителя.
    private fun removeNoChildNode(node: Node, parent: Node?) {
        if (parent == null) {
            throw IllegalStateException("Can not remove the root node without child nodes")
        }
        if (node == parent.left) {
            parent.left = null
        } else if (node == parent.right) {
            parent.right = null
        }
    }

    //Достаточно «переместить» единственный дочерний узел в узел, который мы удаляем.
    private fun removeSingleChildNode(parent: Node, child: Node) {
        parent.key = child.key
        parent.left = child.left
        parent.right = child.right
    }

    //Мы должны найти узел, который должен заменить узел, который мы хотим удалить.
    //Выбрать узел в левом поддереве с наибольшим ключом.
    private fun removeTwoChildNode(node: Node) {
        val leftChild = node.left!!
        leftChild.right?.let {
            val maxParent = findParentOfMaxChild(leftChild)
            maxParent?.right?.let {
                node.key = it.key
                maxParent.right = null
            } ?: throw IllegalStateException("Node with max child must have the right child!")
        } ?: run {
            node.key = leftChild.key
            node.left = leftChild.left
        }
    }

    private fun findParentOfMaxChild(node: Node) : Node? {
        return node.right?.let {
            rightChild ->
            rightChild.right?.let {
                findParentOfMaxChild(rightChild)
            } ?: node
        }
    }

    //Предварительный заказ (посещение родительского узла, затем левого потомка, затем правого потомка)
    fun visit(): Array<Int> {
        val a = left?.visit() ?: emptyArray()
        val b = right?.visit() ?: emptyArray()
        return a + arrayOf(key) + b
    }

    override fun toString(): String {
        return buildString {
            append(this@Node.key.toString())
            append("\nleft ")
            append(left?.toString().orEmpty())
            append("\nrigth ")
            append(right?.toString().orEmpty())
            append('\n')
        }
    }
}

