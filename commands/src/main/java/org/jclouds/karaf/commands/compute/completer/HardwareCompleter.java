/*
 * Copyright (C) 2011, the original authors
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.karaf.commands.compute.completer;

import org.jclouds.compute.ComputeService;
import org.jclouds.compute.domain.Hardware;
import org.jclouds.domain.Location;

import java.util.Set;

public class HardwareCompleter extends ComputeCompleterSupport {

    public void init() {
        cache = cacheProvider.getProviderCacheForType("hardware");
    }

    @Override
    public void updateOnAdded(ComputeService computeService) {
        if (computeService != null) {
            Set<? extends Hardware> hardwares = computeService.listHardwareProfiles();
            if (hardwares != null) {
                for (Hardware hardware : hardwares) {
                    cache.put(computeService.getContext().getProviderSpecificContext().getId(), hardware.getId());
                }
            }
        }
    }
}
