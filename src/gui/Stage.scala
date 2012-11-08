package gui

class Stage(size: (Int, Int)) {

  // Start at 0,0 (bottom left)
  private[this] def dropOffPos = (0.0,0.0)

  // Create an avatar to be inserted into the board at that position
  private[this] var currentAvatar = ActorAvatar(dropOffPos, AnimKind)

  // Initialise the blocks with a single entry at the origin and to this we
  // add the initial avatar - the +: means prepend to the sequence
  private[this] var blocks = Block((0, 0), AnimKind) +: currentAvatar.current

  // Define a game view to hold onto the blocks, stage size and the
  def view: GameView = GameView(blocks, size, currentAvatar.current)

  // 4-axis functions
  def moveUp()    = moveBy(0.0, 1.0)
  def moveRight() = moveBy(1.0, 0.0)
  def moveDown()  = moveBy(0.0, -1.0)
  def moveLeft()  = moveBy(-1.0, 0.0)

  private[this] def moveBy(delta: (Double, Double)): this.type = {

    // set the avatar next position
    val moved = currentAvatar.moveBy(delta)

    // optimistically think the move will be okay
    var ok = true

    // check the blocks to see that there is no object in the way
    blocks.foreach(
      f => if(
        (f.pos._1 == moved.pos._1) && (f.pos._2 == moved.pos._2) && f.kind == FixedKind
      ) ok = false)

    if (ok) {
      // unload the piece from the blocks and add the moved piece to the blocks
      val unloaded = unload(currentAvatar, blocks)
      blocks = load(moved, unloaded)

      // set the current piece to be the moved piece
      currentAvatar = moved
    }
    this
  }

  /**
   * Take the piece out of the block matrix
   * @param p the piece
   * @param bs the blocks
   * @return the blocks
   */
  private[this] def unload(p: ActorAvatar, bs: Seq[Block]): Seq[Block] = {
    // builds a new collection by applying a function to all elements in the list
    val currentPoss = p.current map {_.pos}
    // selects all the elements from a list which do not satisfy the predicate
    bs filterNot { currentPoss contains _.pos  }

  }

  /**
   * Load the piece into the block martix
   * @param p the piece
   * @param bs the blocks
   * @return the blocks
   */
  private[this] def load(p: ActorAvatar, bs: Seq[Block]): Seq[Block] =
    bs ++ p.current

  /**
   * Render a piece at a set location. This will be a structural element
   * that will define boundaries such as walls, doors and windows
   *
   * @param point the x,y coordinates
   */
  def renderBlock(point: (Double, Double)) {

    val px = math.floor(  point._1 / SWGUI.blockSize)
    val py = size._2 - 1 - math.floor(point._2 / SWGUI.blockSize)

    val piece = ActorAvatar((px,py), FixedKind)
    val unloaded = unload(piece, blocks)
    val moved = piece.moveBy(px,py)
    blocks = load(piece, unloaded)
  }
}