package ScalaWorld

import enumerations.{RequestEnum, Orientation, LandmarkEnumerations, OutcomeEnum}
import structural.{SWActor, Block, Grid, Animated}

class SWEnvironment

object SWEnvironment {

  val actor1 = new SWActor
  val actor1Home = new Block(1,0,0,0, actor1, Orientation.North, grid)

  val blocks:List[Block] = List(
    new Block(0,0,100,100, null, null, grid),
    new Block(0,1,100,100, null, null, grid),
    new Block(0,2,100,100, null, null, grid),
    actor1Home,
    new Block(1,1,0,0, null, null, grid),
    new Block(1,2,100,100, null, null, grid),
    new Block(2,0,100,100, null, null, grid),
    new Block(2,1,100,100, null, null, grid),
    new Block(2,2,100,100, null, null, grid))

  val grid: Grid = new Grid("Basic grid", 9, 9, 0, this, blocks)

  val population = Map[SWActor,Block](actor1 -> actor1Home)

  def move(act: RequestEnum.Value, obj: Animated): OutcomeEnum.Value = {

    val home = population.get(actor1).get
    val orientation = home.orientaion

    val destination = 8//next(home.x, home.y, orientation, act)

//    if (destination.solidity > 0) {
//      println(OutcomeEnum.FAILURE)
//      OutcomeEnum.FAILURE
//    } else {
//      println(OutcomeEnum.SUCCESS)
//      OutcomeEnum.SUCCESS
//    }

      OutcomeEnum.SUCCESS
  }
}
