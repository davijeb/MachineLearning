package ScalaWorld.structural.behaviours

import gui.ActorAvatar

/**
 * Not in the true biblical sense but this behaviour will cause Sleep, Death, Illness etc
 *
 * @param name the behaviour name
 * @param classification the behaviour classification
 */
abstract class GodBehaviour(name: String, classification: BehaviourClassificationEnum.Value, avatar: ActorAvatar) extends Behaviour ( name, classification, avatar) {

}
