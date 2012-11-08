package ScalaWorld.messaging

import collection.immutable.{HashMap, Queue}

import ScalaWorld.enumerations._
import collection.mutable

object RequestDispatcher {

  val map: Map[RequestEnum.Value, RequestExecutor] = HashMap(RequestEnum.DEFAULT -> new RequestExecutor())

  def offer(request: SWRequest)   {
    map.get(request.myaction).get.offer(request)
  }
}


