/*
 * Copyright 2020-2022 Typelevel
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

package cats.effect.std

import cats.effect.kernel._
import cats.syntax.all._

private[std] trait MapRefCompanionPlatform {

  def inScalaConcurrentTrieMap[G[_]: Sync, F[_]: Sync, K, V]: G[MapRef[F, K, Option[V]]] =
    Sync[G]
      .delay(scala.collection.concurrent.TrieMap.empty[K, V])
      .map(MapRef.fromScalaConcurrentMap[F, K, V](_))

  def ofScalaConcurrentTrieMap[F[_]: Sync, K, V]: F[MapRef[F, K, Option[V]]] =
    Sync[F]
      .delay(scala.collection.concurrent.TrieMap.empty[K, V])
      .map(MapRef.fromScalaConcurrentMap[F, K, V](_))

}
