package ScalaWorld.messaging

import collection.immutable.Queue
import java.util.concurrent.{Executors, ExecutorService}

class RequestExecutor {

  var pool: ExecutorService = Executors.newFixedThreadPool(2)

  def offer(request: SWRequest){
    pool.submit(request)
  }
}
