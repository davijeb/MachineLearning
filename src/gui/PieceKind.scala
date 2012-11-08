package gui

sealed trait PieceKind
case object AnimKind extends PieceKind
case object FixedKind extends PieceKind