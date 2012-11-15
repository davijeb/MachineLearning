package ScalaWorld

import _root_.gui.{ActorAvatar, Stage, SWGUI, AbstractUI}
import enumerations.{RequestEnum, Orientation, LandmarkEnumerations, OutcomeEnum}
import structural.behaviours.Behaviour
import structural.{ Block, Grid, Animated}

class SWEnvironment(ui: AbstractUI)

object SWEnvironment {

  val gui = SWGUI

  def move(): OutcomeEnum.Value = {

    // tell the avatar to move
    gui.ui.up()

    OutcomeEnum.SUCCESS

  }
}
