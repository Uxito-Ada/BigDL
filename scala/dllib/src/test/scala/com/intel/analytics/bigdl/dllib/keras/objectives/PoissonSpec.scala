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

package com.intel.analytics.bigdl.dllib.keras.objectives

import com.intel.analytics.bigdl.dllib.keras.layers.KerasBaseSpec

class PoissonSpec extends KerasBaseSpec{

  "PoissonCriterion" should "be ok" in {
    val kerasCode =
      """
        |input_tensor = Input(shape=[3])
        |target_tensor = Input(shape=[3])
        |loss = poisson(target_tensor, input_tensor)
        |input = np.random.uniform(0, 1, [2, 3])
        |Y = np.random.uniform(0, 1, [2, 3])
      """.stripMargin
    val kld = Poisson[Float]()
    checkOutputAndGradForLoss(kld, kerasCode)
  }
}
