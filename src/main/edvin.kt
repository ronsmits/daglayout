class Vertex(text: String) : Pane() {
    companion object {
        val textHorizontalPadding = 25.0
        val textVerticalPadding = 15.0
    }

    val textNode = Text(text)
    val bottomPort = Circle(4.0)

    init {
        style = "-fx-background-color: pink; -fx-border-color: black"
        bottomPort.style = "-fx-fill: red"
        children.addAll(textNode, bottomPort)
    }

    override fun layoutChildren() {
        println("called");
        val textLb = textNode.layoutBounds
        val textX = (width / 2) - (textLb.width / 2)
        val textY = (height / 2) - (textLb.height / 2)
        textNode.resizeRelocate(textX, textY, textLb.width, textLb.height)

        val bpLb = bottomPort.layoutBounds
        val bpX = (width / 2) - (bpLb.width / 2)
        val bpY = height - (bpLb.height / 2)
        bottomPort.resizeRelocate(bpX, bpY, bpLb.width, bpLb.height)
    }

    override fun computePrefWidth(height: Double): Double {
        return textNode.prefWidth(height) + (textHorizontalPadding * 2)
    }

    override fun computePrefHeight(width: Double): Double {
        return textNode.prefHeight(width) + (textVerticalPadding * 2)
    }

    override fun computeMaxWidth(height: Double): Double {
        return computePrefWidth(height)
    }

    override fun computeMaxHeight(width: Double): Double {
        return computePrefHeight(width)
    }

    override fun computeMinHeight(width: Double): Double {
        return computePrefHeight(width)
    }

    override fun computeMinWidth(height: Double): Double {
        return computePrefWidth(height)
    }
}
