package ScalaWorld.misc


object ProcessStore extends App {

  val order =     Order()
  val goodOrder = GoodOrder(order)
  val badOrder  = BadOrder(order)

  def creditCheck (order: Order) : MaybeOrder = {
    GoodOrder(order)
  }

  def stockCheck (order: Order) : MaybeOrder = {
    GoodOrder(order)
  }

  def process (order: Order): Order = {
    println("Order successfully placesd")
    order
  }

  def save (order: Order): Order = {
    println("Order saved to system")
    order
  }

   //goodOrder ~> {creditCheck}   ~> {stockCheck}   -> process -> save
  // badOrder  ~> { creditCheck } ~> { stockCheck } -> process -> save

  for ( o1 <- goodOrder;
        o2 <- creditCheck (o1);
        o3 <- stockCheck  (o2)
  ) yield process (o3)

}
