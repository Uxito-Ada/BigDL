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

import com.intel.analytics.bigdl.dllib.nn.{HardShrink => BHardShrink}
import com.intel.analytics.bigdl.dllib.keras.layers.{HardShrink => ZHardShrink}
import com.intel.analytics.bigdl.dllib.tensor.Tensor
import com.intel.analytics.bigdl.dllib.utils.Shape
import com.intel.analytics.bigdl.dllib.keras.ZooSpecHelper
import com.intel.analytics.bigdl.dllib.keras.serializer.ModuleSerializationTest


class HardShrinkSpec extends ZooSpecHelper {

  "HardShrink 3D Zoo" should "be the same as BigDL" in {
    val blayer = BHardShrink[Float]()
    val zlayer = ZHardShrink[Float](inputShape = Shape(3, 4))
    zlayer.build(Shape(-1, 3, 4))
    zlayer.getOutputShape().toSingle().toArray should be (Array(-1, 3, 4))
    val input = Tensor[Float](Array(2, 3, 4)).rand()
    compareOutputAndGradInput(blayer, zlayer, input)
  }

  "HardShrink 4D Zoo" should "be the same as BigDL" in {
    val blayer = BHardShrink[Float](0.8)
    val zlayer = ZHardShrink[Float](0.8, inputShape = Shape(4, 8, 8))
    zlayer.build(Shape(-1, 4, 8, 8))
    zlayer.getOutputShape().toSingle().toArray should be (Array(-1, 4, 8, 8))
    val input = Tensor[Float](Array(3, 4, 8, 8)).rand()
    compareOutputAndGradInput(blayer, zlayer, input)
  }

}

class HardShrinkSerialTest extends ModuleSerializationTest {
  override def test(): Unit = {
    val layer = HardShrink[Float](inputShape = Shape(4, 8))
    layer.build(Shape(2, 4, 8))
    val input = Tensor[Float](2, 4, 8).rand()
    runSerializationTest(layer, input)
  }
}
