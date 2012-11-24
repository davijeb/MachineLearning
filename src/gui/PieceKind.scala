package gui

import java.awt.{Color => AWTColor}

sealed trait PieceKind {

  /**
   * Do we actually want to render this block - empty space wont need rendering
   * @return true is we wish to render
   */
  def render: Boolean = {
    if (this == AnimKind)       true
    else if (this == FixedKind) true
    else if (this == EmptyKind) false
    else                        false
  }

  /**
   * What is the colour of this block
   * @return the AWTColour
   */
  def colour: AWTColor = {
    if (this == AnimKind)       new AWTColor(210, 255, 255)
    else if (this == FixedKind) new AWTColor(200, 99, 99)
    else                        new AWTColor(0, 0, 0)
  }
}

case object AnimKind   extends PieceKind
case object FixedKind  extends PieceKind
case object EmptyKind  extends PieceKind

