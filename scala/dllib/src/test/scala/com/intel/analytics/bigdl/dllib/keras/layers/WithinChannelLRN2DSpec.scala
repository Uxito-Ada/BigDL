/*
 * Copyright 2016 The BigDL Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intel.analytics.bigdl.dllib.keras.layers

import com.intel.analytics.bigdl.dllib.nn.SpatialWithinChannelLRN
import com.intel.analytics.bigdl.dllib.tensor.Tensor
import com.intel.analytics.bigdl.dllib.utils.Shape
import com.intel.analytics.bigdl.dllib.keras.ZooSpecHelper
import com.intel.analytics.bigdl.dllib.keras.serializer.ModuleSerializationTest


class WithinChannelLRN2DSpec extends ZooSpecHelper {

  "WithinChannelLRN2D Zoo" should "be the same as BigDL" in {
    val blayer = SpatialWithinChannelLRN[Float](3, 0.01, 0.5)
    val zlayer = WithinChannelLRN2D[Float](3, 0.01, 0.5, inputShape = Shape(2, 3, 4))
    zlayer.build(Shape(-1, 2, 3, 4))
    zlayer.getOutputShape().toSingle().toArray should be (Array(-1, 2, 3, 4))
    val input = Tensor[Float](Array(2, 2, 3, 4)).rand()
    compareOutputAndGradInput(blayer, zlayer, input)
  }

  "WithinChannelLRN2D Zoo Default value" should "be the same as BigDL" in {
    val blayer = SpatialWithinChannelLRN[Float]()
    val zlayer = WithinChannelLRN2D[Float](inputShape = Shape(4, 8, 8))
    zlayer.build(Shape(-1, 4, 8, 8))
    zlayer.getOutputShape().toSingle().toArray should be (Array(-1, 4, 8, 8))
    val input = Tensor[Float](Array(3, 4, 8, 8)).rand()
    compareOutputAndGradInput(blayer, zlayer, input)
  }

}

class WithinChannelLRN2DSerialTest extends ModuleSerializationTest {
  override def test(): Unit = {
    val layer = WithinChannelLRN2D[Float](inputShape = Shape(4, 8, 8))
    layer.build(Shape(2, 4, 8, 8))
    val input = Tensor[Float](2, 4, 8, 8).rand()
    runSerializationTest(layer, input)
  }
}
